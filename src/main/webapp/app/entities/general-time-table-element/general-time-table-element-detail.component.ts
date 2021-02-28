import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IGeneralTimeTableElement } from 'app/shared/model/general-time-table-element.model';

@Component({
  selector: 'jhi-general-time-table-element-detail',
  templateUrl: './general-time-table-element-detail.component.html',
})
export class GeneralTimeTableElementDetailComponent implements OnInit {
  generalTimeTableElement: IGeneralTimeTableElement | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ generalTimeTableElement }) => (this.generalTimeTableElement = generalTimeTableElement));
  }

  previousState(): void {
    window.history.back();
  }
}
