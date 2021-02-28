import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { MyschoolTestModule } from '../../../test.module';
import { GradeClassComponent } from 'app/entities/grade-class/grade-class.component';
import { GradeClassService } from 'app/entities/grade-class/grade-class.service';
import { GradeClass } from 'app/shared/model/grade-class.model';

describe('Component Tests', () => {
  describe('GradeClass Management Component', () => {
    let comp: GradeClassComponent;
    let fixture: ComponentFixture<GradeClassComponent>;
    let service: GradeClassService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [MyschoolTestModule],
        declarations: [GradeClassComponent],
      })
        .overrideTemplate(GradeClassComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(GradeClassComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(GradeClassService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new GradeClass(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.gradeClasses && comp.gradeClasses[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
