import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { MyschoolTestModule } from '../../../test.module';
import { TutorTypeDetailComponent } from 'app/entities/tutor-type/tutor-type-detail.component';
import { TutorType } from 'app/shared/model/tutor-type.model';

describe('Component Tests', () => {
  describe('TutorType Management Detail Component', () => {
    let comp: TutorTypeDetailComponent;
    let fixture: ComponentFixture<TutorTypeDetailComponent>;
    const route = ({ data: of({ tutorType: new TutorType(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [MyschoolTestModule],
        declarations: [TutorTypeDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(TutorTypeDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(TutorTypeDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load tutorType on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.tutorType).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
