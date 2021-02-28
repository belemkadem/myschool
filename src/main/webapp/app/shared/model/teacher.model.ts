import { Moment } from 'moment';
import { BloodGroup } from 'app/shared/model/enumerations/blood-group.model';
import { Gender } from 'app/shared/model/enumerations/gender.model';

export interface ITeacher {
  id?: number;
  lastName?: string;
  firstName?: string;
  arabicLastName?: string;
  arabicFirstName?: string;
  dateOfBirth?: Moment;
  bloodGroup?: BloodGroup;
  gender?: Gender;
  address?: string;
  email?: string;
  picture?: string;
  password?: string;
  nationality?: string;
}

export class Teacher implements ITeacher {
  constructor(
    public id?: number,
    public lastName?: string,
    public firstName?: string,
    public arabicLastName?: string,
    public arabicFirstName?: string,
    public dateOfBirth?: Moment,
    public bloodGroup?: BloodGroup,
    public gender?: Gender,
    public address?: string,
    public email?: string,
    public picture?: string,
    public password?: string,
    public nationality?: string
  ) {}
}
