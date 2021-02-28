import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { MyschoolTestModule } from '../../../test.module';
import { GeneralTimeTableComponent } from 'app/entities/general-time-table/general-time-table.component';
import { GeneralTimeTableService } from 'app/entities/general-time-table/general-time-table.service';
import { GeneralTimeTable } from 'app/shared/model/general-time-table.model';

describe('Component Tests', () => {
  describe('GeneralTimeTable Management Component', () => {
    let comp: GeneralTimeTableComponent;
    let fixture: ComponentFixture<GeneralTimeTableComponent>;
    let service: GeneralTimeTableService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [MyschoolTestModule],
        declarations: [GeneralTimeTableComponent],
      })
        .overrideTemplate(GeneralTimeTableComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(GeneralTimeTableComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(GeneralTimeTableService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new GeneralTimeTable(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.generalTimeTables && comp.generalTimeTables[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
