import { ISubject } from 'app/shared/model/subject.model';
import { IGeneralTimeTable } from 'app/shared/model/general-time-table.model';
import { DayOfWeek } from 'app/shared/model/enumerations/day-of-week.model';

export interface IGeneralTimeTableElement {
  id?: number;
  dayOfWeek?: DayOfWeek;
  houreFrom?: number;
  minuteFrom?: number;
  houreTo?: number;
  minuteTo?: number;
  subject?: ISubject;
  generalTimeTable?: IGeneralTimeTable;
}

export class GeneralTimeTableElement implements IGeneralTimeTableElement {
  constructor(
    public id?: number,
    public dayOfWeek?: DayOfWeek,
    public houreFrom?: number,
    public minuteFrom?: number,
    public houreTo?: number,
    public minuteTo?: number,
    public subject?: ISubject,
    public generalTimeTable?: IGeneralTimeTable
  ) {}
}
