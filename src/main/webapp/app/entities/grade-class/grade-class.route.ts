import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IGradeClass, GradeClass } from 'app/shared/model/grade-class.model';
import { GradeClassService } from './grade-class.service';
import { GradeClassComponent } from './grade-class.component';
import { GradeClassDetailComponent } from './grade-class-detail.component';
import { GradeClassUpdateComponent } from './grade-class-update.component';

@Injectable({ providedIn: 'root' })
export class GradeClassResolve implements Resolve<IGradeClass> {
  constructor(private service: GradeClassService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IGradeClass> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((gradeClass: HttpResponse<GradeClass>) => {
          if (gradeClass.body) {
            return of(gradeClass.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new GradeClass());
  }
}

export const gradeClassRoute: Routes = [
  {
    path: '',
    component: GradeClassComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'myschoolApp.gradeClass.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: GradeClassDetailComponent,
    resolve: {
      gradeClass: GradeClassResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'myschoolApp.gradeClass.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: GradeClassUpdateComponent,
    resolve: {
      gradeClass: GradeClassResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'myschoolApp.gradeClass.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: GradeClassUpdateComponent,
    resolve: {
      gradeClass: GradeClassResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'myschoolApp.gradeClass.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
