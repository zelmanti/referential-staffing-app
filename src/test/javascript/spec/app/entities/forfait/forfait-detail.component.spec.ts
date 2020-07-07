import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { ReferentialStaffingTestModule } from '../../../test.module';
import { ForfaitDetailComponent } from 'app/entities/forfait/forfait-detail.component';
import { Forfait } from 'app/shared/model/forfait.model';

describe('Component Tests', () => {
  describe('Forfait Management Detail Component', () => {
    let comp: ForfaitDetailComponent;
    let fixture: ComponentFixture<ForfaitDetailComponent>;
    const route = ({ data: of({ forfait: new Forfait(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [ReferentialStaffingTestModule],
        declarations: [ForfaitDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(ForfaitDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(ForfaitDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load forfait on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.forfait).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
