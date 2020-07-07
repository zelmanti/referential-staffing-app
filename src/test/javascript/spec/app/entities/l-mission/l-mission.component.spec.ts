import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { ReferentialStaffingTestModule } from '../../../test.module';
import { LMissionComponent } from 'app/entities/l-mission/l-mission.component';
import { LMissionService } from 'app/entities/l-mission/l-mission.service';
import { LMission } from 'app/shared/model/l-mission.model';

describe('Component Tests', () => {
  describe('LMission Management Component', () => {
    let comp: LMissionComponent;
    let fixture: ComponentFixture<LMissionComponent>;
    let service: LMissionService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [ReferentialStaffingTestModule],
        declarations: [LMissionComponent],
      })
        .overrideTemplate(LMissionComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(LMissionComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(LMissionService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new LMission(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.lMissions && comp.lMissions[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
