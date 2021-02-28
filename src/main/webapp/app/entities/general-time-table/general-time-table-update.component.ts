import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IGeneralTimeTable, GeneralTimeTable } from 'app/shared/model/general-time-table.model';
import { GeneralTimeTableService } from './general-time-table.service';
import { IGradeClass } from 'app/shared/model/grade-class.model';
import { GradeClassService } from 'app/entities/grade-class/grade-class.service';

@Component({
  selector: 'jhi-general-time-table-update',
  templateUrl: './general-time-table-update.component.html',
})
export class GeneralTimeTableUpdateComponent implements OnInit {
  isSaving = false;
  gradeclasses: IGradeClass[] = [];

  editForm = this.fb.group({
    id: [],
    gradeClass: [],
  });

  constructor(
    protected generalTimeTableService: GeneralTimeTableService,
    protected gradeClassService: GradeClassService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ generalTimeTable }) => {
      this.updateForm(generalTimeTable);

      this.gradeClassService.query().subscribe((res: HttpResponse<IGradeClass[]>) => (this.gradeclasses = res.body || []));
    });
  }

  updateForm(generalTimeTable: IGeneralTimeTable): void {
    this.editForm.patchValue({
      id: generalTimeTable.id,
      gradeClass: generalTimeTable.gradeClass,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const generalTimeTable = this.createFromForm();
    if (generalTimeTable.id !== undefined) {
      this.subscribeToSaveResponse(this.generalTimeTableService.update(generalTimeTable));
    } else {
      this.subscribeToSaveResponse(this.generalTimeTableService.create(generalTimeTable));
    }
  }

  private createFromForm(): IGeneralTimeTable {
    return {
      ...new GeneralTimeTable(),
      id: this.editForm.get(['id'])!.value,
      gradeClass: this.editForm.get(['gradeClass'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IGeneralTimeTable>>): void {
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

  trackById(index: number, item: IGradeClass): any {
    return item.id;
  }
}
