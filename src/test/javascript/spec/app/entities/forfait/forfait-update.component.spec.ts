import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { ReferentialStaffingTestModule } from '../../../test.module';
import { ForfaitUpdateComponent } from 'app/entities/forfait/forfait-update.component';
import { ForfaitService } from 'app/entities/forfait/forfait.service';
import { Forfait } from 'app/shared/model/forfait.model';

describe('Component Tests', () => {
  describe('Forfait Management Update Component', () => {
    let comp: ForfaitUpdateComponent;
    let fixture: ComponentFixture<ForfaitUpdateComponent>;
    let service: ForfaitService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [ReferentialStaffingTestModule],
        declarations: [ForfaitUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(ForfaitUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ForfaitUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ForfaitService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new Forfait(123);
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
        const entity = new Forfait();
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
