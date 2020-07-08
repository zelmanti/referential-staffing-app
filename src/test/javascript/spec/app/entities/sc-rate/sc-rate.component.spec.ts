import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { ReferentialStaffingTestModule } from '../../../test.module';
import { SCRateComponent } from 'app/entities/sc-rate/sc-rate.component';
import { SCRateService } from 'app/entities/sc-rate/sc-rate.service';
import { SCRate } from 'app/shared/model/sc-rate.model';

describe('Component Tests', () => {
  describe('SCRate Management Component', () => {
    let comp: SCRateComponent;
    let fixture: ComponentFixture<SCRateComponent>;
    let service: SCRateService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [ReferentialStaffingTestModule],
        declarations: [SCRateComponent],
      })
        .overrideTemplate(SCRateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(SCRateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(SCRateService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new SCRate(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.sCRates && comp.sCRates[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
