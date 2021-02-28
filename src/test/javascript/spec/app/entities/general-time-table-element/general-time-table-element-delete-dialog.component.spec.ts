import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { MyschoolTestModule } from '../../../test.module';
import { MockEventManager } from '../../../helpers/mock-event-manager.service';
import { MockActiveModal } from '../../../helpers/mock-active-modal.service';
import { GeneralTimeTableElementDeleteDialogComponent } from 'app/entities/general-time-table-element/general-time-table-element-delete-dialog.component';
import { GeneralTimeTableElementService } from 'app/entities/general-time-table-element/general-time-table-element.service';

describe('Component Tests', () => {
  describe('GeneralTimeTableElement Management Delete Component', () => {
    let comp: GeneralTimeTableElementDeleteDialogComponent;
    let fixture: ComponentFixture<GeneralTimeTableElementDeleteDialogComponent>;
    let service: GeneralTimeTableElementService;
    let mockEventManager: MockEventManager;
    let mockActiveModal: MockActiveModal;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [MyschoolTestModule],
        declarations: [GeneralTimeTableElementDeleteDialogComponent],
      })
        .overrideTemplate(GeneralTimeTableElementDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(GeneralTimeTableElementDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(GeneralTimeTableElementService);
      mockEventManager = TestBed.get(JhiEventManager);
      mockActiveModal = TestBed.get(NgbActiveModal);
    });

    describe('confirmDelete', () => {
      it('Should call delete service on confirmDelete', inject(
        [],
        fakeAsync(() => {
          // GIVEN
          spyOn(service, 'delete').and.returnValue(of({}));

          // WHEN
          comp.confirmDelete(123);
          tick();

          // THEN
          expect(service.delete).toHaveBeenCalledWith(123);
          expect(mockActiveModal.closeSpy).toHaveBeenCalled();
          expect(mockEventManager.broadcastSpy).toHaveBeenCalled();
        })
      ));

      it('Should not call delete service on clear', () => {
        // GIVEN
        spyOn(service, 'delete');

        // WHEN
        comp.cancel();

        // THEN
        expect(service.delete).not.toHaveBeenCalled();
        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
      });
    });
  });
});
