import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { MyschoolTestModule } from '../../../test.module';
import { GradeClassDetailComponent } from 'app/entities/grade-class/grade-class-detail.component';
import { GradeClass } from 'app/shared/model/grade-class.model';

describe('Component Tests', () => {
  describe('GradeClass Management Detail Component', () => {
    let comp: GradeClassDetailComponent;
    let fixture: ComponentFixture<GradeClassDetailComponent>;
    const route = ({ data: of({ gradeClass: new GradeClass(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [MyschoolTestModule],
        declarations: [GradeClassDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(GradeClassDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(GradeClassDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load gradeClass on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.gradeClass).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
