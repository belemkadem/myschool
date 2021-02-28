import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IGradeClass, GradeClass } from 'app/shared/model/grade-class.model';
import { GradeClassService } from './grade-class.service';
import { IGrade } from 'app/shared/model/grade.model';
import { GradeService } from 'app/entities/grade/grade.service';

@Component({
  selector: 'jhi-grade-class-update',
  templateUrl: './grade-class-update.component.html',
})
export class GradeClassUpdateComponent implements OnInit {
  isSaving = false;
  grades: IGrade[] = [];

  editForm = this.fb.group({
    id: [],
    name: [],
    grade: [],
  });

  constructor(
    protected gradeClassService: GradeClassService,
    protected gradeService: GradeService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ gradeClass }) => {
      this.updateForm(gradeClass);

      this.gradeService.query().subscribe((res: HttpResponse<IGrade[]>) => (this.grades = res.body || []));
    });
  }

  updateForm(gradeClass: IGradeClass): void {
    this.editForm.patchValue({
      id: gradeClass.id,
      name: gradeClass.name,
      grade: gradeClass.grade,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const gradeClass = this.createFromForm();
    if (gradeClass.id !== undefined) {
      this.subscribeToSaveResponse(this.gradeClassService.update(gradeClass));
    } else {
      this.subscribeToSaveResponse(this.gradeClassService.create(gradeClass));
    }
  }

  private createFromForm(): IGradeClass {
    return {
      ...new GradeClass(),
      id: this.editForm.get(['id'])!.value,
      name: this.editForm.get(['name'])!.value,
      grade: this.editForm.get(['grade'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IGradeClass>>): void {
    result.subscribe(
      () => this.onSaveSuccess(),
      () => this.onSaveError()
    );
  }

  protected onSaveSuccess(): void {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError(): void {
    this.isSaving = false;
  }

  trackById(index: number, item: IGrade): any {
    return item.id;
  }
}
