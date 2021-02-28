import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { ITutor, Tutor } from 'app/shared/model/tutor.model';
import { TutorService } from './tutor.service';
import { ITutorType } from 'app/shared/model/tutor-type.model';
import { TutorTypeService } from 'app/entities/tutor-type/tutor-type.service';

@Component({
  selector: 'jhi-tutor-update',
  templateUrl: './tutor-update.component.html',
})
export class TutorUpdateComponent implements OnInit {
  isSaving = false;
  tutortypes: ITutorType[] = [];

  editForm = this.fb.group({
    id: [],
    lastName: [],
    firstName: [],
    arabicLastName: [],
    arabicFirstName: [],
    nin: [],
    gender: [],
    address: [],
    phoneNumber: [],
    email: [],
    passowrd: [],
    tutorType: [],
  });

  constructor(
    protected tutorService: TutorService,
    protected tutorTypeService: TutorTypeService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ tutor }) => {
      this.updateForm(tutor);

      this.tutorTypeService.query().subscribe((res: HttpResponse<ITutorType[]>) => (this.tutortypes = res.body || []));
    });
  }

  updateForm(tutor: ITutor): void {
    this.editForm.patchValue({
      id: tutor.id,
      lastName: tutor.lastName,
      firstName: tutor.firstName,
      arabicLastName: tutor.arabicLastName,
      arabicFirstName: tutor.arabicFirstName,
      nin: tutor.nin,
      gender: tutor.gender,
      address: tutor.address,
      phoneNumber: tutor.phoneNumber,
      email: tutor.email,
      passowrd: tutor.passowrd,
      tutorType: tutor.tutorType,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const tutor = this.createFromForm();
    if (tutor.id !== undefined) {
      this.subscribeToSaveResponse(this.tutorService.update(tutor));
    } else {
      this.subscribeToSaveResponse(this.tutorService.create(tutor));
    }
  }

  private createFromForm(): ITutor {
    return {
      ...new Tutor(),
      id: this.editForm.get(['id'])!.value,
      lastName: this.editForm.get(['lastName'])!.value,
      firstName: this.editForm.get(['firstName'])!.value,
      arabicLastName: this.editForm.get(['arabicLastName'])!.value,
      arabicFirstName: this.editForm.get(['arabicFirstName'])!.value,
      nin: this.editForm.get(['nin'])!.value,
      gender: this.editForm.get(['gender'])!.value,
      address: this.editForm.get(['address'])!.value,
      phoneNumber: this.editForm.get(['phoneNumber'])!.value,
      email: this.editForm.get(['email'])!.value,
      passowrd: this.editForm.get(['passowrd'])!.value,
      tutorType: this.editForm.get(['tutorType'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ITutor>>): void {
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

  trackById(index: number, item: ITutorType): any {
    return item.id;
  }
}
