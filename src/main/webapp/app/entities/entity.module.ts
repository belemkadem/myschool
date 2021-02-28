import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'department',
        loadChildren: () => import('./department/department.module').then(m => m.MyschoolDepartmentModule),
      },
      {
        path: 'grade',
        loadChildren: () => import('./grade/grade.module').then(m => m.MyschoolGradeModule),
      },
      {
        path: 'grade-class',
        loadChildren: () => import('./grade-class/grade-class.module').then(m => m.MyschoolGradeClassModule),
      },
      {
        path: 'tutor-type',
        loadChildren: () => import('./tutor-type/tutor-type.module').then(m => m.MyschoolTutorTypeModule),
      },
      {
        path: 'student',
        loadChildren: () => import('./student/student.module').then(m => m.MyschoolStudentModule),
      },
      {
        path: 'tutor',
        loadChildren: () => import('./tutor/tutor.module').then(m => m.MyschoolTutorModule),
      },
      {
        path: 'teacher',
        loadChildren: () => import('./teacher/teacher.module').then(m => m.MyschoolTeacherModule),
      },
      {
        path: 'subject',
        loadChildren: () => import('./subject/subject.module').then(m => m.MyschoolSubjectModule),
      },
      {
        path: 'general-time-table',
        loadChildren: () => import('./general-time-table/general-time-table.module').then(m => m.MyschoolGeneralTimeTableModule),
      },
      {
        path: 'general-time-table-element',
        loadChildren: () =>
          import('./general-time-table-element/general-time-table-element.module').then(m => m.MyschoolGeneralTimeTableElementModule),
      },
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ]),
  ],
})
export class MyschoolEntityModule {}
