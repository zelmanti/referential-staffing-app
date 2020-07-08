import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { ReferentialStaffingTestModule } from '../../../test.module';
import { EntityTypeDetailComponent } from 'app/entities/entity-type/entity-type-detail.component';
import { EntityType } from 'app/shared/model/entity-type.model';

describe('Component Tests', () => {
  describe('EntityType Management Detail Component', () => {
    let comp: EntityTypeDetailComponent;
    let fixture: ComponentFixture<EntityTypeDetailComponent>;
    const route = ({ data: of({ entityType: new EntityType(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [ReferentialStaffingTestModule],
        declarations: [EntityTypeDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(EntityTypeDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(EntityTypeDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load entityType on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.entityType).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
