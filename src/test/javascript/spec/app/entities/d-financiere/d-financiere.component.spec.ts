import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { ReferentialStaffingTestModule } from '../../../test.module';
import { DFinanciereComponent } from 'app/entities/d-financiere/d-financiere.component';
import { DFinanciereService } from 'app/entities/d-financiere/d-financiere.service';
import { DFinanciere } from 'app/shared/model/d-financiere.model';

describe('Component Tests', () => {
  describe('DFinanciere Management Component', () => {
    let comp: DFinanciereComponent;
    let fixture: ComponentFixture<DFinanciereComponent>;
    let service: DFinanciereService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [ReferentialStaffingTestModule],
        declarations: [DFinanciereComponent],
      })
        .overrideTemplate(DFinanciereComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(DFinanciereComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(DFinanciereService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new DFinanciere(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.dFinancieres && comp.dFinancieres[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
