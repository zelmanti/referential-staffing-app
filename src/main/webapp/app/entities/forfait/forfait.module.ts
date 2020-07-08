import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { ReferentialStaffingSharedModule } from 'app/shared/shared.module';
import { ForfaitComponent } from './forfait.component';
import { ForfaitDetailComponent } from './forfait-detail.component';
import { ForfaitUpdateComponent } from './forfait-update.component';
import { ForfaitDeleteDialogComponent } from './forfait-delete-dialog.component';
import { forfaitRoute } from './forfait.route';

@NgModule({
  imports: [ReferentialStaffingSharedModule, RouterModule.forChild(forfaitRoute)],
  declarations: [ForfaitComponent, ForfaitDetailComponent, ForfaitUpdateComponent, ForfaitDeleteDialogComponent],
  entryComponents: [ForfaitDeleteDialogComponent],
})
export class ReferentialStaffingForfaitModule {}
