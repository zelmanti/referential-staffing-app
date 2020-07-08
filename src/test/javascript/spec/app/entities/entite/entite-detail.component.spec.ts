import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { ReferentialStaffingTestModule } from '../../../test.module';
import { EntiteDetailComponent } from 'app/entities/entite/entite-detail.component';
import { Entite } from 'app/shared/model/entite.model';

describe('Component Tests', () => {
  describe('Entite Management Detail Component', () => {
    let comp: EntiteDetailComponent;
    let fixture: ComponentFixture<EntiteDetailComponent>;
    const route = ({ data: of({ entite: new Entite(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [ReferentialStaffingTestModule],
        declarations: [EntiteDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(EntiteDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(EntiteDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load entite on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.entite).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
