import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { MyschoolSharedModule } from 'app/shared/shared.module';
import { GradeClassComponent } from './grade-class.component';
import { GradeClassDetailComponent } from './grade-class-detail.component';
import { GradeClassUpdateComponent } from './grade-class-update.component';
import { GradeClassDeleteDialogComponent } from './grade-class-delete-dialog.component';
import { gradeClassRoute } from './grade-class.route';

@NgModule({
  imports: [MyschoolSharedModule, RouterModule.forChild(gradeClassRoute)],
  declarations: [GradeClassComponent, GradeClassDetailComponent, GradeClassUpdateComponent, GradeClassDeleteDialogComponent],
  entryComponents: [GradeClassDeleteDialogComponent],
})
export class MyschoolGradeClassModule {}
