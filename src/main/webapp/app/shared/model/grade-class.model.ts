import { IGrade } from 'app/shared/model/grade.model';

export interface IGradeClass {
  id?: number;
  name?: string;
  grade?: IGrade;
}

export class GradeClass implements IGradeClass {
  constructor(public id?: number, public name?: string, public grade?: IGrade) {}
}
