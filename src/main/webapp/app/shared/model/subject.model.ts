import { IGrade } from 'app/shared/model/grade.model';

export interface ISubject {
  id?: number;
  name?: string;
  grade?: IGrade;
}

export class Subject implements ISubject {
  constructor(public id?: number, public name?: string, public grade?: IGrade) {}
}
