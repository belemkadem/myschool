import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IGeneralTimeTableElement } from 'app/shared/model/general-time-table-element.model';
import { GeneralTimeTableElementService } from './general-time-table-element.service';
import { GeneralTimeTableElementDeleteDialogComponent } from './general-time-table-element-delete-dialog.component';

@Component({
  selector: 'jhi-general-time-table-element',
  templateUrl: './general-time-table-element.component.html',
})
export class GeneralTimeTableElementComponent implements OnInit, OnDestroy {
  generalTimeTableElements?: IGeneralTimeTableElement[];
  eventSubscriber?: Subscription;

  constructor(
    protected generalTimeTableElementService: GeneralTimeTableElementService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.generalTimeTableElementService
      .query()
      .subscribe((res: HttpResponse<IGeneralTimeTableElement[]>) => (this.generalTimeTableElements = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInGeneralTimeTableElements();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IGeneralTimeTableElement): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInGeneralTimeTableElements(): void {
    this.eventSubscriber = this.eventManager.subscribe('generalTimeTableElementListModification', () => this.loadAll());
  }

  delete(generalTimeTableElement: IGeneralTimeTableElement): void {
    const modalRef = this.modalService.open(GeneralTimeTableElementDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.generalTimeTableElement = generalTimeTableElement;
  }
}
