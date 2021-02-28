import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { MyschoolTestModule } from '../../../test.module';
import { GeneralTimeTableUpdateComponent } from 'app/entities/general-time-table/general-time-table-update.component';
import { GeneralTimeTableService } from 'app/entities/general-time-table/general-time-table.service';
import { GeneralTimeTable } from 'app/shared/model/general-time-table.model';

describe('Component Tests', () => {
  describe('GeneralTimeTable Management Update Component', () => {
    let comp: GeneralTimeTableUpdateComponent;
    let fixture: ComponentFixture<GeneralTimeTableUpdateComponent>;
    let service: GeneralTimeTableService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [MyschoolTestModule],
        declarations: [GeneralTimeTableUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(GeneralTimeTableUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(GeneralTimeTableUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(GeneralTimeTableService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new GeneralTimeTable(123);
        spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.update).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));

      it('Should call create service on save for new entity', fakeAsync(() => {
        // GIVEN
        const entity = new GeneralTimeTable();
        spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.create).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));
    });
  });
});
