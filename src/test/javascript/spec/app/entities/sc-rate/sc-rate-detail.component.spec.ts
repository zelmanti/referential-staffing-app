import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { ReferentialStaffingTestModule } from '../../../test.module';
import { SCRateDetailComponent } from 'app/entities/sc-rate/sc-rate-detail.component';
import { SCRate } from 'app/shared/model/sc-rate.model';

describe('Component Tests', () => {
  describe('SCRate Management Detail Component', () => {
    let comp: SCRateDetailComponent;
    let fixture: ComponentFixture<SCRateDetailComponent>;
    const route = ({ data: of({ sCRate: new SCRate(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [ReferentialStaffingTestModule],
        declarations: [SCRateDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(SCRateDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(SCRateDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load sCRate on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.sCRate).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
