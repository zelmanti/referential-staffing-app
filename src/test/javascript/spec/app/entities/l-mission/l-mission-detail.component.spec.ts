import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { ReferentialStaffingTestModule } from '../../../test.module';
import { LMissionDetailComponent } from 'app/entities/l-mission/l-mission-detail.component';
import { LMission } from 'app/shared/model/l-mission.model';

describe('Component Tests', () => {
  describe('LMission Management Detail Component', () => {
    let comp: LMissionDetailComponent;
    let fixture: ComponentFixture<LMissionDetailComponent>;
    const route = ({ data: of({ lMission: new LMission(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [ReferentialStaffingTestModule],
        declarations: [LMissionDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(LMissionDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(LMissionDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load lMission on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.lMission).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
