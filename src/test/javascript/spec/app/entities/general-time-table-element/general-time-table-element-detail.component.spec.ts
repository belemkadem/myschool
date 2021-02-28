import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { MyschoolTestModule } from '../../../test.module';
import { GeneralTimeTableElementDetailComponent } from 'app/entities/general-time-table-element/general-time-table-element-detail.component';
import { GeneralTimeTableElement } from 'app/shared/model/general-time-table-element.model';

describe('Component Tests', () => {
  describe('GeneralTimeTableElement Management Detail Component', () => {
    let comp: GeneralTimeTableElementDetailComponent;
    let fixture: ComponentFixture<GeneralTimeTableElementDetailComponent>;
    const route = ({ data: of({ generalTimeTableElement: new GeneralTimeTableElement(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [MyschoolTestModule],
        declarations: [GeneralTimeTableElementDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(GeneralTimeTableElementDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(GeneralTimeTableElementDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load generalTimeTableElement on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.generalTimeTableElement).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
