import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { ReferentialStaffingTestModule } from '../../../test.module';
import { SCRateUpdateComponent } from 'app/entities/sc-rate/sc-rate-update.component';
import { SCRateService } from 'app/entities/sc-rate/sc-rate.service';
import { SCRate } from 'app/shared/model/sc-rate.model';

describe('Component Tests', () => {
  describe('SCRate Management Update Component', () => {
    let comp: SCRateUpdateComponent;
    let fixture: ComponentFixture<SCRateUpdateComponent>;
    let service: SCRateService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [ReferentialStaffingTestModule],
        declarations: [SCRateUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(SCRateUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(SCRateUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(SCRateService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new SCRate(123);
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
        const entity = new SCRate();
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
