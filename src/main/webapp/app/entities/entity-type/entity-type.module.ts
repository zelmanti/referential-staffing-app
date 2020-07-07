import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { ReferentialStaffingSharedModule } from 'app/shared/shared.module';
import { EntityTypeComponent } from './entity-type.component';
import { EntityTypeDetailComponent } from './entity-type-detail.component';
import { EntityTypeUpdateComponent } from './entity-type-update.component';
import { EntityTypeDeleteDialogComponent } from './entity-type-delete-dialog.component';
import { entityTypeRoute } from './entity-type.route';

@NgModule({
  imports: [ReferentialStaffingSharedModule, RouterModule.forChild(entityTypeRoute)],
  declarations: [EntityTypeComponent, EntityTypeDetailComponent, EntityTypeUpdateComponent, EntityTypeDeleteDialogComponent],
  entryComponents: [EntityTypeDeleteDialogComponent],
})
export class ReferentialStaffingEntityTypeModule {}
