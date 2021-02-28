import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { ISubject, Subject } from 'app/shared/model/subject.model';
import { SubjectService } from './subject.service';
import { IGrade } from 'app/shared/model/grade.model';
import { GradeService } from 'app/entities/grade/grade.service';

@Component({
  selector: 'jhi-subject-update',
  templateUrl: './subject-update.component.html',
})
export class SubjectUpdateComponent implements OnInit {
  isSaving = false;
  grades: IGrade[] = [];

  editForm = this.fb.group({
    id: [],
    name: [],
    grade: [],
  });

  constructor(
    protected subjectService: SubjectService,
    protected gradeService: GradeService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ subject }) => {
      this.updateForm(subject);

      this.gradeService.query().subscribe((res: HttpResponse<IGrade[]>) => (this.grades = res.body || []));
    });
  }

  updateForm(subject: ISubject): void {
    this.editForm.patchValue({
      id: subject.id,
      name: subject.name,
      grade: subject.grade,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const subject = this.createFromForm();
    if (subject.id !== undefined) {
      this.subscribeToSaveResponse(this.subjectService.update(subject));
    } else {
      this.subscribeToSaveResponse(this.subjectService.create(subject));
    }
  }

  private createFromForm(): ISubject {
    return {
      ...new Subject(),
      id: this.editForm.get(['id'])!.value,
      name: this.editForm.get(['name'])!.value,
      grade: this.editForm.get(['grade'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ISubject>>): void {
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
