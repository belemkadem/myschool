import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { MyschoolSharedModule } from 'app/shared/shared.module';
import { GeneralTimeTableElementComponent } from './general-time-table-element.component';
import { GeneralTimeTableElementDetailComponent } from './general-time-table-element-detail.component';
import { GeneralTimeTableElementUpdateComponent } from './general-time-table-element-update.component';
import { GeneralTimeTableElementDeleteDialogComponent } from './general-time-table-element-delete-dialog.component';
import { generalTimeTableElementRoute } from './general-time-table-element.route';

@NgModule({
  imports: [MyschoolSharedModule, RouterModule.forChild(generalTimeTableElementRoute)],
  declarations: [
    GeneralTimeTableElementComponent,
    GeneralTimeTableElementDetailComponent,
    GeneralTimeTableElementUpdateComponent,
    GeneralTimeTableElementDeleteDialogComponent,
  ],
  entryComponents: [GeneralTimeTableElementDeleteDialogComponent],
})
export class MyschoolGeneralTimeTableElementModule {}
