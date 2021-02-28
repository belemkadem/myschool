import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { MyschoolTestModule } from '../../../test.module';
import { GeneralTimeTableElementComponent } from 'app/entities/general-time-table-element/general-time-table-element.component';
import { GeneralTimeTableElementService } from 'app/entities/general-time-table-element/general-time-table-element.service';
import { GeneralTimeTableElement } from 'app/shared/model/general-time-table-element.model';

describe('Component Tests', () => {
  describe('GeneralTimeTableElement Management Component', () => {
    let comp: GeneralTimeTableElementComponent;
    let fixture: ComponentFixture<GeneralTimeTableElementComponent>;
    let service: GeneralTimeTableElementService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [MyschoolTestModule],
        declarations: [GeneralTimeTableElementComponent],
      })
        .overrideTemplate(GeneralTimeTableElementComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(GeneralTimeTableElementComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(GeneralTimeTableElementService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new GeneralTimeTableElement(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.generalTimeTableElements && comp.generalTimeTableElements[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
