import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { MyschoolTestModule } from '../../../test.module';
import { TutorTypeComponent } from 'app/entities/tutor-type/tutor-type.component';
import { TutorTypeService } from 'app/entities/tutor-type/tutor-type.service';
import { TutorType } from 'app/shared/model/tutor-type.model';

describe('Component Tests', () => {
  describe('TutorType Management Component', () => {
    let comp: TutorTypeComponent;
    let fixture: ComponentFixture<TutorTypeComponent>;
    let service: TutorTypeService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [MyschoolTestModule],
        declarations: [TutorTypeComponent],
      })
        .overrideTemplate(TutorTypeComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(TutorTypeComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(TutorTypeService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new TutorType(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.tutorTypes && comp.tutorTypes[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
