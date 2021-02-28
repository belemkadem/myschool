import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IGeneralTimeTableElement } from 'app/shared/model/general-time-table-element.model';
import { GeneralTimeTableElementService } from './general-time-table-element.service';

@Component({
  templateUrl: './general-time-table-element-delete-dialog.component.html',
})
export class GeneralTimeTableElementDeleteDialogComponent {
  generalTimeTableElement?: IGeneralTimeTableElement;

  constructor(
    protected generalTimeTableElementService: GeneralTimeTableElementService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.generalTimeTableElementService.delete(id).subscribe(() => {
      this.eventManager.broadcast('generalTimeTableElementListModification');
      this.activeModal.close();
    });
  }
}
