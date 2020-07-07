import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { ReferentialStaffingSharedModule } from 'app/shared/shared.module';
import { LMissionComponent } from './l-mission.component';
import { LMissionDetailComponent } from './l-mission-detail.component';
import { LMissionUpdateComponent } from './l-mission-update.component';
import { LMissionDeleteDialogComponent } from './l-mission-delete-dialog.component';
import { lMissionRoute } from './l-mission.route';

@NgModule({
  imports: [ReferentialStaffingSharedModule, RouterModule.forChild(lMissionRoute)],
  declarations: [LMissionComponent, LMissionDetailComponent, LMissionUpdateComponent, LMissionDeleteDialogComponent],
  entryComponents: [LMissionDeleteDialogComponent],
})
export class ReferentialStaffingLMissionModule {}
