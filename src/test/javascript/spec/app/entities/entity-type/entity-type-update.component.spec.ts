import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { ReferentialStaffingTestModule } from '../../../test.module';
import { EntityTypeUpdateComponent } from 'app/entities/entity-type/entity-type-update.component';
import { EntityTypeService } from 'app/entities/entity-type/entity-type.service';
import { EntityType } from 'app/shared/model/entity-type.model';

describe('Component Tests', () => {
  describe('EntityType Management Update Component', () => {
    let comp: EntityTypeUpdateComponent;
    let fixture: ComponentFixture<EntityTypeUpdateComponent>;
    let service: EntityTypeService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [ReferentialStaffingTestModule],
        declarations: [EntityTypeUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(EntityTypeUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(EntityTypeUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(EntityTypeService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new EntityType(123);
        spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.update).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));

      it('Should call create service on save for new entity', fakeAsync(() => {
        // GIVEN
        const entity = new EntityType();
        spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.create).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));
    });
  });
});
