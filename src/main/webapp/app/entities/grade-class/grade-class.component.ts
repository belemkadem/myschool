import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IGradeClass } from 'app/shared/model/grade-class.model';
import { GradeClassService } from './grade-class.service';
import { GradeClassDeleteDialogComponent } from './grade-class-delete-dialog.component';

@Component({
  selector: 'jhi-grade-class',
  templateUrl: './grade-class.component.html',
})
export class GradeClassComponent implements OnInit, OnDestroy {
  gradeClasses?: IGradeClass[];
  eventSubscriber?: Subscription;

  constructor(protected gradeClassService: GradeClassService, protected eventManager: JhiEventManager, protected modalService: NgbModal) {}

  loadAll(): void {
    this.gradeClassService.query().subscribe((res: HttpResponse<IGradeClass[]>) => (this.gradeClasses = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInGradeClasses();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IGradeClass): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInGradeClasses(): void {
    this.eventSubscriber = this.eventManager.subscribe('gradeClassListModification', () => this.loadAll());
  }

  delete(gradeClass: IGradeClass): void {
    const modalRef = this.modalService.open(GradeClassDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.gradeClass = gradeClass;
  }
}
