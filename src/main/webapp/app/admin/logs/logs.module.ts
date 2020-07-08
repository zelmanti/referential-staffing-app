import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { ReferentialStaffingSharedModule } from 'app/shared/shared.module';

import { LogsComponent } from './logs.component';

import { logsRoute } from './logs.route';

@NgModule({
  imports: [ReferentialStaffingSharedModule, RouterModule.forChild([logsRoute])],
  declarations: [LogsComponent],
})
export class LogsModule {}
