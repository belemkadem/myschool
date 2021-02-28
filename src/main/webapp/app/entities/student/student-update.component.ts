import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IStudent, Student } from 'app/shared/model/student.model';
import { StudentService } from './student.service';
import { ITutor } from 'app/shared/model/tutor.model';
import { TutorService } from 'app/entities/tutor/tutor.service';
import { IGradeClass } from 'app/shared/model/grade-class.model';
import { GradeClassService } from 'app/entities/grade-class/grade-class.service';

type SelectableEntity = ITutor | IGradeClass;

@Component({
  selector: 'jhi-student-update',
  templateUrl: './student-update.component.html',
})
export class StudentUpdateComponent implements OnInit {
  isSaving = false;
  tutors: ITutor[] = [];
  gradeclasses: IGradeClass[] = [];
  dateOfBirthDp: any;

  editForm = this.fb.group({
    id: [],
    lastName: [],
    firstName: [],
    arabicLastName: [],
    arabicFirstName: [],
    dateOfBirth: [],
    bloodGroup: [],
    classroom: [],
    gender: [],
    address: [],
    email: [],
    picture: [],
    schoolOfOrigin: [],
    password: [],
    nationality: [],
    tutor1: [],
    tutor2: [],
    gradeClass: [],
  });

  constructor(
    protected studentService: StudentService,
    protected tutorService: TutorService,
    protected gradeClassService: GradeClassService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ student }) => {
      this.updateForm(student);

      this.tutorService.query().subscribe((res: HttpResponse<ITutor[]>) => (this.tutors = res.body || []));

      this.gradeClassService.query().subscribe((res: HttpResponse<IGradeClass[]>) => (this.gradeclasses = res.body || []));
    });
  }

  updateForm(student: IStudent): void {
    this.editForm.patchValue({
      id: student.id,
      lastName: student.lastName,
      firstName: student.firstName,
      arabicLastName: student.arabicLastName,
      arabicFirstName: student.arabicFirstName,
      dateOfBirth: student.dateOfBirth,
      bloodGroup: student.bloodGroup,
      classroom: student.classroom,
      gender: student.gender,
      address: student.address,
      email: student.email,
      picture: student.picture,
      schoolOfOrigin: student.schoolOfOrigin,
      password: student.password,
      nationality: student.nationality,
      tutor1: student.tutor1,
      tutor2: student.tutor2,
      gradeClass: student.gradeClass,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const student = this.createFromForm();
    if (student.id !== undefined) {
      this.subscribeToSaveResponse(this.studentService.update(student));
    } else {
      this.subscribeToSaveResponse(this.studentService.create(student));
    }
  }

  private createFromForm(): IStudent {
    return {
      ...new Student(),
      id: this.editForm.get(['id'])!.value,
      lastName: this.editForm.get(['lastName'])!.value,
      firstName: this.editForm.get(['firstName'])!.value,
      arabicLastName: this.editForm.get(['arabicLastName'])!.value,
      arabicFirstName: this.editForm.get(['arabicFirstName'])!.value,
      dateOfBirth: this.editForm.get(['dateOfBirth'])!.value,
      bloodGroup: this.editForm.get(['bloodGroup'])!.value,
      classroom: this.editForm.get(['classroom'])!.value,
      gender: this.editForm.get(['gender'])!.value,
      address: this.editForm.get(['address'])!.value,
      email: this.editForm.get(['email'])!.value,
      picture: this.editForm.get(['picture'])!.value,
      schoolOfOrigin: this.editForm.get(['schoolOfOrigin'])!.value,
      password: this.editForm.get(['password'])!.value,
      nationality: this.editForm.get(['nationality'])!.value,
      tutor1: this.editForm.get(['tutor1'])!.value,
      tutor2: this.editForm.get(['tutor2'])!.value,
      gradeClass: this.editForm.get(['gradeClass'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IStudent>>): void {
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
