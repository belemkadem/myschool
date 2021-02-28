import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { MyschoolSharedModule } from 'app/shared/shared.module';
import { TutorTypeComponent } from './tutor-type.component';
import { TutorTypeDetailComponent } from './tutor-type-detail.component';
import { TutorTypeUpdateComponent } from './tutor-type-update.component';
import { TutorTypeDeleteDialogComponent } from './tutor-type-delete-dialog.component';
import { tutorTypeRoute } from './tutor-type.route';

@NgModule({
  imports: [MyschoolSharedModule, RouterModule.forChild(tutorTypeRoute)],
  declarations: [TutorTypeComponent, TutorTypeDetailComponent, TutorTypeUpdateComponent, TutorTypeDeleteDialogComponent],
  entryComponents: [TutorTypeDeleteDialogComponent],
})
export class MyschoolTutorTypeModule {}
