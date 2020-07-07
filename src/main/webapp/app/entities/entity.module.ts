import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'affectation',
        loadChildren: () => import('./affectation/affectation.module').then(m => m.ReferentialStaffingAffectationModule),
      },
      {
        path: 'forfait',
        loadChildren: () => import('./forfait/forfait.module').then(m => m.ReferentialStaffingForfaitModule),
      },
      {
        path: 'l-mission',
        loadChildren: () => import('./l-mission/l-mission.module').then(m => m.ReferentialStaffingLMissionModule),
      },
      {
        path: 'd-financiere',
        loadChildren: () => import('./d-financiere/d-financiere.module').then(m => m.ReferentialStaffingDFinanciereModule),
      },
      {
        path: 'entite',
        loadChildren: () => import('./entite/entite.module').then(m => m.ReferentialStaffingEntiteModule),
      },
      {
        path: 'sc-rate',
        loadChildren: () => import('./sc-rate/sc-rate.module').then(m => m.ReferentialStaffingSCRateModule),
      },
      {
        path: 'entity-type',
        loadChildren: () => import('./entity-type/entity-type.module').then(m => m.ReferentialStaffingEntityTypeModule),
      },
      {
        path: 'client',
        loadChildren: () => import('./client/client.module').then(m => m.ReferentialStaffingClientModule),
      },
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ]),
  ],
})
export class ReferentialStaffingEntityModule {}
