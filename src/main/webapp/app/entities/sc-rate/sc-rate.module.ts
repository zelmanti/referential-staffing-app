import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { ReferentialStaffingSharedModule } from 'app/shared/shared.module';
import { SCRateComponent } from './sc-rate.component';
import { SCRateDetailComponent } from './sc-rate-detail.component';
import { SCRateUpdateComponent } from './sc-rate-update.component';
import { SCRateDeleteDialogComponent } from './sc-rate-delete-dialog.component';
import { sCRateRoute } from './sc-rate.route';

@NgModule({
  imports: [ReferentialStaffingSharedModule, RouterModule.forChild(sCRateRoute)],
  declarations: [SCRateComponent, SCRateDetailComponent, SCRateUpdateComponent, SCRateDeleteDialogComponent],
  entryComponents: [SCRateDeleteDialogComponent],
})
export class ReferentialStaffingSCRateModule {}
