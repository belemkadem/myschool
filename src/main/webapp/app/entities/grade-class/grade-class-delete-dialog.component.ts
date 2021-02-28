import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IGradeClass } from 'app/shared/model/grade-class.model';
import { GradeClassService } from './grade-class.service';

@Component({
  templateUrl: './grade-class-delete-dialog.component.html',
})
export class GradeClassDeleteDialogComponent {
  gradeClass?: IGradeClass;

  constructor(
    protected gradeClassService: GradeClassService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.gradeClassService.delete(id).subscribe(() => {
      this.eventManager.broadcast('gradeClassListModification');
      this.activeModal.close();
    });
  }
}
