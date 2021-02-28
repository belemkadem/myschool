import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { MyschoolTestModule } from '../../../test.module';
import { GeneralTimeTableDetailComponent } from 'app/entities/general-time-table/general-time-table-detail.component';
import { GeneralTimeTable } from 'app/shared/model/general-time-table.model';

describe('Component Tests', () => {
  describe('GeneralTimeTable Management Detail Component', () => {
    let comp: GeneralTimeTableDetailComponent;
    let fixture: ComponentFixture<GeneralTimeTableDetailComponent>;
    const route = ({ data: of({ generalTimeTable: new GeneralTimeTable(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [MyschoolTestModule],
        declarations: [GeneralTimeTableDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(GeneralTimeTableDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(GeneralTimeTableDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load generalTimeTable on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.generalTimeTable).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
