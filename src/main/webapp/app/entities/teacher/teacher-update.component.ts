import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { ITeacher, Teacher } from 'app/shared/model/teacher.model';
import { TeacherService } from './teacher.service';

@Component({
  selector: 'jhi-teacher-update',
  templateUrl: './teacher-update.component.html',
})
export class TeacherUpdateComponent implements OnInit {
  isSaving = false;
  dateOfBirthDp: any;

  editForm = this.fb.group({
    id: [],
    designation: [],
    speciality: [],
    lastName: [],
    firstName: [],
    arabicLastName: [],
    arabicFirstName: [],
    dateOfBirth: [],
    bloodGroup: [],
    gender: [],
    address: [],
    email: [],
    picture: [],
    password: [],
    nationality: [],
  });

  constructor(protected teacherService: TeacherService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ teacher }) => {
      this.updateForm(teacher);
    });
  }

  updateForm(teacher: ITeacher): void {
    this.editForm.patchValue({
      id: teacher.id,
      designation: teacher.designation,
      speciality: teacher.speciality,
      lastName: teacher.lastName,
      firstName: teacher.firstName,
      arabicLastName: teacher.arabicLastName,
      arabicFirstName: teacher.arabicFirstName,
      dateOfBirth: teacher.dateOfBirth,
      bloodGroup: teacher.bloodGroup,
      gender: teacher.gender,
      address: teacher.address,
      email: teacher.email,
      picture: teacher.picture,
      password: teacher.password,
      nationality: teacher.nationality,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const teacher = this.createFromForm();
    if (teacher.id !== undefined) {
      this.subscribeToSaveResponse(this.teacherService.update(teacher));
    } else {
      this.subscribeToSaveResponse(this.teacherService.create(teacher));
    }
  }

  private createFromForm(): ITeacher {
    return {
      ...new Teacher(),
      id: this.editForm.get(['id'])!.value,
      designation: this.editForm.get(['designation'])!.value,
      speciality: this.editForm.get(['speciality'])!.value,
      lastName: this.editForm.get(['lastName'])!.value,
      firstName: this.editForm.get(['firstName'])!.value,
      arabicLastName: this.editForm.get(['arabicLastName'])!.value,
      arabicFirstName: this.editForm.get(['arabicFirstName'])!.value,
      dateOfBirth: this.editForm.get(['dateOfBirth'])!.value,
      bloodGroup: this.editForm.get(['bloodGroup'])!.value,
      gender: this.editForm.get(['gender'])!.value,
      address: this.editForm.get(['address'])!.value,
      email: this.editForm.get(['email'])!.value,
      picture: this.editForm.get(['picture'])!.value,
      password: this.editForm.get(['password'])!.value,
      nationality: this.editForm.get(['nationality'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ITeacher>>): void {
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
