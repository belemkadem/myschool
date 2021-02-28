import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { MyschoolTestModule } from '../../../test.module';
import { GeneralTimeTableElementUpdateComponent } from 'app/entities/general-time-table-element/general-time-table-element-update.component';
import { GeneralTimeTableElementService } from 'app/entities/general-time-table-element/general-time-table-element.service';
import { GeneralTimeTableElement } from 'app/shared/model/general-time-table-element.model';

describe('Component Tests', () => {
  describe('GeneralTimeTableElement Management Update Component', () => {
    let comp: GeneralTimeTableElementUpdateComponent;
    let fixture: ComponentFixture<GeneralTimeTableElementUpdateComponent>;
    let service: GeneralTimeTableElementService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [MyschoolTestModule],
        declarations: [GeneralTimeTableElementUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(GeneralTimeTableElementUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(GeneralTimeTableElementUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(GeneralTimeTableElementService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new GeneralTimeTableElement(123);
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
        const entity = new GeneralTimeTableElement();
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
