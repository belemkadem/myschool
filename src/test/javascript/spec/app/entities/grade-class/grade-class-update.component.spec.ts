import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { MyschoolTestModule } from '../../../test.module';
import { GradeClassUpdateComponent } from 'app/entities/grade-class/grade-class-update.component';
import { GradeClassService } from 'app/entities/grade-class/grade-class.service';
import { GradeClass } from 'app/shared/model/grade-class.model';

describe('Component Tests', () => {
  describe('GradeClass Management Update Component', () => {
    let comp: GradeClassUpdateComponent;
    let fixture: ComponentFixture<GradeClassUpdateComponent>;
    let service: GradeClassService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [MyschoolTestModule],
        declarations: [GradeClassUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(GradeClassUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(GradeClassUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(GradeClassService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new GradeClass(123);
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
        const entity = new GradeClass();
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
