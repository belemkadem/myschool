import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IGeneralTimeTableElement, GeneralTimeTableElement } from 'app/shared/model/general-time-table-element.model';
import { GeneralTimeTableElementService } from './general-time-table-element.service';
import { ISubject } from 'app/shared/model/subject.model';
import { SubjectService } from 'app/entities/subject/subject.service';
import { IGeneralTimeTable } from 'app/shared/model/general-time-table.model';
import { GeneralTimeTableService } from 'app/entities/general-time-table/general-time-table.service';

type SelectableEntity = ISubject | IGeneralTimeTable;

@Component({
  selector: 'jhi-general-time-table-element-update',
  templateUrl: './general-time-table-element-update.component.html',
})
export class GeneralTimeTableElementUpdateComponent implements OnInit {
  isSaving = false;
  subjects: ISubject[] = [];
  generaltimetables: IGeneralTimeTable[] = [];

  editForm = this.fb.group({
    id: [],
    dayOfWeek: [],
    houreFrom: [null, [Validators.min(0), Validators.max(23)]],
    minuteFrom: [null, [Validators.min(0), Validators.max(59)]],
    houreTo: [null, [Validators.min(0), Validators.max(23)]],
    minuteTo: [null, [Validators.min(0), Validators.max(59)]],
    subject: [],
    generalTimeTable: [],
  });

  constructor(
    protected generalTimeTableElementService: GeneralTimeTableElementService,
    protected subjectService: SubjectService,
    protected generalTimeTableService: GeneralTimeTableService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ generalTimeTableElement }) => {
      this.updateForm(generalTimeTableElement);

      this.subjectService.query().subscribe((res: HttpResponse<ISubject[]>) => (this.subjects = res.body || []));

      this.generalTimeTableService.query().subscribe((res: HttpResponse<IGeneralTimeTable[]>) => (this.generaltimetables = res.body || []));
    });
  }

  updateForm(generalTimeTableElement: IGeneralTimeTableElement): void {
    this.editForm.patchValue({
      id: generalTimeTableElement.id,
      dayOfWeek: generalTimeTableElement.dayOfWeek,
      houreFrom: generalTimeTableElement.houreFrom,
      minuteFrom: generalTimeTableElement.minuteFrom,
      houreTo: generalTimeTableElement.houreTo,
      minuteTo: generalTimeTableElement.minuteTo,
      subject: generalTimeTableElement.subject,
      generalTimeTable: generalTimeTableElement.generalTimeTable,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const generalTimeTableElement = this.createFromForm();
    if (generalTimeTableElement.id !== undefined) {
      this.subscribeToSaveResponse(this.generalTimeTableElementService.update(generalTimeTableElement));
    } else {
      this.subscribeToSaveResponse(this.generalTimeTableElementService.create(generalTimeTableElement));
    }
  }

  private createFromForm(): IGeneralTimeTableElement {
    return {
      ...new GeneralTimeTableElement(),
      id: this.editForm.get(['id'])!.value,
      dayOfWeek: this.editForm.get(['dayOfWeek'])!.value,
      houreFrom: this.editForm.get(['houreFrom'])!.value,
      minuteFrom: this.editForm.get(['minuteFrom'])!.value,
      houreTo: this.editForm.get(['houreTo'])!.value,
      minuteTo: this.editForm.get(['minuteTo'])!.value,
      subject: this.editForm.get(['subject'])!.value,
      generalTimeTable: this.editForm.get(['generalTimeTable'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IGeneralTimeTableElement>>): void {
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

  trackById(index: number, item: SelectableEntity): any {
    return item.id;
  }
}
