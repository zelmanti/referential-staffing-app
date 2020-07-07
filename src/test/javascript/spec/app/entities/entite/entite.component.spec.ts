import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { ReferentialStaffingTestModule } from '../../../test.module';
import { EntiteComponent } from 'app/entities/entite/entite.component';
import { EntiteService } from 'app/entities/entite/entite.service';
import { Entite } from 'app/shared/model/entite.model';

describe('Component Tests', () => {
  describe('Entite Management Component', () => {
    let comp: EntiteComponent;
    let fixture: ComponentFixture<EntiteComponent>;
    let service: EntiteService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [ReferentialStaffingTestModule],
        declarations: [EntiteComponent],
      })
        .overrideTemplate(EntiteComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(EntiteComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(EntiteService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new Entite(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.entites && comp.entites[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
