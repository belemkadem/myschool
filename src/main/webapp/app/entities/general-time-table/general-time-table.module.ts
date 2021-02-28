import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { MyschoolSharedModule } from 'app/shared/shared.module';
import { GeneralTimeTableComponent } from './general-time-table.component';
import { GeneralTimeTableDetailComponent } from './general-time-table-detail.component';
import { GeneralTimeTableUpdateComponent } from './general-time-table-update.component';
import { GeneralTimeTableDeleteDialogComponent } from './general-time-table-delete-dialog.component';
import { generalTimeTableRoute } from './general-time-table.route';

@NgModule({
  imports: [MyschoolSharedModule, RouterModule.forChild(generalTimeTableRoute)],
  declarations: [
    GeneralTimeTableComponent,
    GeneralTimeTableDetailComponent,
    GeneralTimeTableUpdateComponent,
    GeneralTimeTableDeleteDialogComponent,
  ],
  entryComponents: [GeneralTimeTableDeleteDialogComponent],
})
export class MyschoolGeneralTimeTableModule {}
