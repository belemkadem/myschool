import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { MyschoolSharedModule } from 'app/shared/shared.module';
import { GradeComponent } from './grade.component';
import { GradeDetailComponent } from './grade-detail.component';
import { GradeUpdateComponent } from './grade-update.component';
import { GradeDeleteDialogComponent } from './grade-delete-dialog.component';
import { gradeRoute } from './grade.route';

@NgModule({
  imports: [MyschoolSharedModule, RouterModule.forChild(gradeRoute)],
  declarations: [GradeComponent, GradeDetailComponent, GradeUpdateComponent, GradeDeleteDialogComponent],
  entryComponents: [GradeDeleteDialogComponent],
})
export class MyschoolGradeModule {}
