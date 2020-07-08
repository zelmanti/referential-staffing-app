import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { ReferentialStaffingTestModule } from '../../../test.module';
import { DFinanciereDetailComponent } from 'app/entities/d-financiere/d-financiere-detail.component';
import { DFinanciere } from 'app/shared/model/d-financiere.model';

describe('Component Tests', () => {
  describe('DFinanciere Management Detail Component', () => {
    let comp: DFinanciereDetailComponent;
    let fixture: ComponentFixture<DFinanciereDetailComponent>;
    const route = ({ data: of({ dFinanciere: new DFinanciere(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [ReferentialStaffingTestModule],
        declarations: [DFinanciereDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(DFinanciereDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(DFinanciereDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load dFinanciere on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.dFinanciere).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
