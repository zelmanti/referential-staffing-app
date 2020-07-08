import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { ReferentialStaffingTestModule } from '../../../test.module';
import { LMissionUpdateComponent } from 'app/entities/l-mission/l-mission-update.component';
import { LMissionService } from 'app/entities/l-mission/l-mission.service';
import { LMission } from 'app/shared/model/l-mission.model';

describe('Component Tests', () => {
  describe('LMission Management Update Component', () => {
    let comp: LMissionUpdateComponent;
    let fixture: ComponentFixture<LMissionUpdateComponent>;
    let service: LMissionService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [ReferentialStaffingTestModule],
        declarations: [LMissionUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(LMissionUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(LMissionUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(LMissionService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new LMission(123);
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
        const entity = new LMission();
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
