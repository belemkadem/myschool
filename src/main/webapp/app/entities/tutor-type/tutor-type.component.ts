import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { ITutorType } from 'app/shared/model/tutor-type.model';
import { TutorTypeService } from './tutor-type.service';
import { TutorTypeDeleteDialogComponent } from './tutor-type-delete-dialog.component';

@Component({
  selector: 'jhi-tutor-type',
  templateUrl: './tutor-type.component.html',
})
export class TutorTypeComponent implements OnInit, OnDestroy {
  tutorTypes?: ITutorType[];
  eventSubscriber?: Subscription;

  constructor(protected tutorTypeService: TutorTypeService, protected eventManager: JhiEventManager, protected modalService: NgbModal) {}

  loadAll(): void {
    this.tutorTypeService.query().subscribe((res: HttpResponse<ITutorType[]>) => (this.tutorTypes = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInTutorTypes();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: ITutorType): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInTutorTypes(): void {
    this.eventSubscriber = this.eventManager.subscribe('tutorTypeListModification', () => this.loadAll());
  }

  delete(tutorType: ITutorType): void {
    const modalRef = this.modalService.open(TutorTypeDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.tutorType = tutorType;
  }
}
