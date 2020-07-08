import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { ReferentialStaffingTestModule } from '../../../test.module';
import { EntityTypeComponent } from 'app/entities/entity-type/entity-type.component';
import { EntityTypeService } from 'app/entities/entity-type/entity-type.service';
import { EntityType } from 'app/shared/model/entity-type.model';

describe('Component Tests', () => {
  describe('EntityType Management Component', () => {
    let comp: EntityTypeComponent;
    let fixture: ComponentFixture<EntityTypeComponent>;
    let service: EntityTypeService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [ReferentialStaffingTestModule],
        declarations: [EntityTypeComponent],
      })
        .overrideTemplate(EntityTypeComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(EntityTypeComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(EntityTypeService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new EntityType(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.entityTypes && comp.entityTypes[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
