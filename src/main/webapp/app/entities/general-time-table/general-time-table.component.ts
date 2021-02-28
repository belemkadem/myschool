import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IGeneralTimeTable } from 'app/shared/model/general-time-table.model';
import { GeneralTimeTableService } from './general-time-table.service';
import { GeneralTimeTableDeleteDialogComponent } from './general-time-table-delete-dialog.component';

@Component({
  selector: 'jhi-general-time-table',
  templateUrl: './general-time-table.component.html',
})
export class GeneralTimeTableComponent implements OnInit, OnDestroy {
  generalTimeTables?: IGeneralTimeTable[];
  eventSubscriber?: Subscription;

  constructor(
    protected generalTimeTableService: GeneralTimeTableService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.generalTimeTableService.query().subscribe((res: HttpResponse<IGeneralTimeTable[]>) => (this.generalTimeTables = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInGeneralTimeTables();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IGeneralTimeTable): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInGeneralTimeTables(): void {
    this.eventSubscriber = this.eventManager.subscribe('generalTimeTableListModification', () => this.loadAll());
  }

  delete(generalTimeTable: IGeneralTimeTable): void {
    const modalRef = this.modalService.open(GeneralTimeTableDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.generalTimeTable = generalTimeTable;
  }
}
