import { ITutorType } from 'app/shared/model/tutor-type.model';
import { Gender } from 'app/shared/model/enumerations/gender.model';

export interface ITutor {
  id?: number;
  lastName?: string;
  firstName?: string;
  arabicLastName?: string;
  arabicFirstName?: string;
  nin?: string;
  gender?: Gender;
  address?: string;
  phoneNumber?: string;
  email?: string;
  passowrd?: string;
  tutorType?: ITutorType;
}

export class Tutor implements ITutor {
  constructor(
    public id?: number,
    public lastName?: string,
    public firstName?: string,
    public arabicLastName?: string,
    public arabicFirstName?: string,
    public nin?: string,
    public gender?: Gender,
    public address?: string,
    public phoneNumber?: string,
    public email?: string,
    public passowrd?: string,
    public tutorType?: ITutorType
  ) {}
}
