import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ITutorType } from 'app/shared/model/tutor-type.model';
import { TutorTypeService } from './tutor-type.service';

@Component({
  templateUrl: './tutor-type-delete-dialog.component.html',
})
export class TutorTypeDeleteDialogComponent {
  tutorType?: ITutorType;

  constructor(protected tutorTypeService: TutorTypeService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.tutorTypeService.delete(id).subscribe(() => {
      this.eventManager.broadcast('tutorTypeListModification');
      this.activeModal.close();
    });
  }
}
