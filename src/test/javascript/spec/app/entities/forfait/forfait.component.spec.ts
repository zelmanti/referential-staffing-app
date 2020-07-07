import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { ReferentialStaffingTestModule } from '../../../test.module';
import { ForfaitComponent } from 'app/entities/forfait/forfait.component';
import { ForfaitService } from 'app/entities/forfait/forfait.service';
import { Forfait } from 'app/shared/model/forfait.model';

describe('Component Tests', () => {
  describe('Forfait Management Component', () => {
    let comp: ForfaitComponent;
    let fixture: ComponentFixture<ForfaitComponent>;
    let service: ForfaitService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [ReferentialStaffingTestModule],
        declarations: [ForfaitComponent],
      })
        .overrideTemplate(ForfaitComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ForfaitComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ForfaitService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new Forfait(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.forfaits && comp.forfaits[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
