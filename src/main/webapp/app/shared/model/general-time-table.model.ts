import { IGeneralTimeTableElement } from 'app/shared/model/general-time-table-element.model';
import { IGradeClass } from 'app/shared/model/grade-class.model';

export interface IGeneralTimeTable {
  id?: number;
  elements?: IGeneralTimeTableElement[];
  gradeClass?: IGradeClass;
}

export class GeneralTimeTable implements IGeneralTimeTable {
  constructor(public id?: number, public elements?: IGeneralTimeTableElement[], public gradeClass?: IGradeClass) {}
}
