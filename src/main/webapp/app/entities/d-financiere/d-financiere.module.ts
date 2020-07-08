import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { ReferentialStaffingSharedModule } from 'app/shared/shared.module';
import { DFinanciereComponent } from './d-financiere.component';
import { DFinanciereDetailComponent } from './d-financiere-detail.component';
import { DFinanciereUpdateComponent } from './d-financiere-update.component';
import { DFinanciereDeleteDialogComponent } from './d-financiere-delete-dialog.component';
import { dFinanciereRoute } from './d-financiere.route';

@NgModule({
  imports: [ReferentialStaffingSharedModule, RouterModule.forChild(dFinanciereRoute)],
  declarations: [DFinanciereComponent, DFinanciereDetailComponent, DFinanciereUpdateComponent, DFinanciereDeleteDialogComponent],
  entryComponents: [DFinanciereDeleteDialogComponent],
})
export class ReferentialStaffingDFinanciereModule {}
