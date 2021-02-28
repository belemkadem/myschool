import { Moment } from 'moment';
import { ITutor } from 'app/shared/model/tutor.model';
import { IGradeClass } from 'app/shared/model/grade-class.model';
import { BloodGroup } from 'app/shared/model/enumerations/blood-group.model';
import { Gender } from 'app/shared/model/enumerations/gender.model';

export interface IStudent {
  id?: number;
  lastName?: string;
  firstName?: string;
  arabicLastName?: string;
  arabicFirstName?: string;
  dateOfBirth?: Moment;
  bloodGroup?: BloodGroup;
  classroom?: string;
  gender?: Gender;
  address?: string;
  email?: string;
  picture?: string;
  schoolOfOrigin?: string;
  password?: string;
  nationality?: string;
  tutor1?: ITutor;
  tutor2?: ITutor;
  gradeClass?: IGradeClass;
}

export class Student implements IStudent {
  constructor(
    public id?: number,
    public lastName?: string,
    public firstName?: string,
    public arabicLastName?: string,
    public arabicFirstName?: string,
    public dateOfBirth?: Moment,
    public bloodGroup?: BloodGroup,
    public classroom?: string,
    public gender?: Gender,
    public address?: string,
    public email?: string,
    public picture?: string,
    public schoolOfOrigin?: string,
    public password?: string,
    public nationality?: string,
    public tutor1?: ITutor,
    public tutor2?: ITutor,
    public gradeClass?: IGradeClass
  ) {}
}
