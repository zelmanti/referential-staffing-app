import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { ReferentialStaffingTestModule } from '../../../test.module';
import { DFinanciereUpdateComponent } from 'app/entities/d-financiere/d-financiere-update.component';
import { DFinanciereService } from 'app/entities/d-financiere/d-financiere.service';
import { DFinanciere } from 'app/shared/model/d-financiere.model';

describe('Component Tests', () => {
  describe('DFinanciere Management Update Component', () => {
    let comp: DFinanciereUpdateComponent;
    let fixture: ComponentFixture<DFinanciereUpdateComponent>;
    let service: DFinanciereService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [ReferentialStaffingTestModule],
        declarations: [DFinanciereUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(DFinanciereUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(DFinanciereUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(DFinanciereService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new DFinanciere(123);
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
        const entity = new DFinanciere();
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
