import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IGeneralTimeTable } from 'app/shared/model/general-time-table.model';

@Component({
  selector: 'jhi-general-time-table-detail',
  templateUrl: './general-time-table-detail.component.html',
})
export class GeneralTimeTableDetailComponent implements OnInit {
  generalTimeTable: IGeneralTimeTable | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ generalTimeTable }) => (this.generalTimeTable = generalTimeTable));
  }

  previousState(): void {
    window.history.back();
  }
}
