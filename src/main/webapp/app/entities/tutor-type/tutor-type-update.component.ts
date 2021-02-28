import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { ITutorType, TutorType } from 'app/shared/model/tutor-type.model';
import { TutorTypeService } from './tutor-type.service';

@Component({
  selector: 'jhi-tutor-type-update',
  templateUrl: './tutor-type-update.component.html',
})
export class TutorTypeUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    name: [],
  });

  constructor(protected tutorTypeService: TutorTypeService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ tutorType }) => {
      this.updateForm(tutorType);
    });
  }

  updateForm(tutorType: ITutorType): void {
    this.editForm.patchValue({
      id: tutorType.id,
      name: tutorType.name,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const tutorType = this.createFromForm();
    if (tutorType.id !== undefined) {
      this.subscribeToSaveResponse(this.tutorTypeService.update(tutorType));
    } else {
      this.subscribeToSaveResponse(this.tutorTypeService.create(tutorType));
    }
  }

  private createFromForm(): ITutorType {
    return {
      ...new TutorType(),
      id: this.editForm.get(['id'])!.value,
      name: this.editForm.get(['name'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ITutorType>>): void {
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
}
