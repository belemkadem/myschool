import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IGeneralTimeTable } from 'app/shared/model/general-time-table.model';
import { GeneralTimeTableService } from './general-time-table.service';

@Component({
  templateUrl: './general-time-table-delete-dialog.component.html',
})
export class GeneralTimeTableDeleteDialogComponent {
  generalTimeTable?: IGeneralTimeTable;

  constructor(
    protected generalTimeTableService: GeneralTimeTableService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.generalTimeTableService.delete(id).subscribe(() => {
      this.eventManager.broadcast('generalTimeTableListModification');
      this.activeModal.close();
    });
  }
}
