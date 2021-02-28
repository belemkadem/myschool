import { IDepartment } from 'app/shared/model/department.model';

export interface IGrade {
  id?: number;
  name?: string;
  department?: IDepartment;
}

export class Grade implements IGrade {
  constructor(public id?: number, public name?: string, public department?: IDepartment) {}
}
