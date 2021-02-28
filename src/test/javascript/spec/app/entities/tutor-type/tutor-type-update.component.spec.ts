import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { MyschoolTestModule } from '../../../test.module';
import { TutorTypeUpdateComponent } from 'app/entities/tutor-type/tutor-type-update.component';
import { TutorTypeService } from 'app/entities/tutor-type/tutor-type.service';
import { TutorType } from 'app/shared/model/tutor-type.model';

describe('Component Tests', () => {
  describe('TutorType Management Update Component', () => {
    let comp: TutorTypeUpdateComponent;
    let fixture: ComponentFixture<TutorTypeUpdateComponent>;
    let service: TutorTypeService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [MyschoolTestModule],
        declarations: [TutorTypeUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(TutorTypeUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(TutorTypeUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(TutorTypeService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new TutorType(123);
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
        const entity = new TutorType();
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
