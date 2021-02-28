export interface ITutorType {
  id?: number;
  name?: string;
}

export class TutorType implements ITutorType {
  constructor(public id?: number, public name?: string) {}
}
