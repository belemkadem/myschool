import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { ITutorType, TutorType } from 'app/shared/model/tutor-type.model';
import { TutorTypeService } from './tutor-type.service';
import { TutorTypeComponent } from './tutor-type.component';
import { TutorTypeDetailComponent } from './tutor-type-detail.component';
import { TutorTypeUpdateComponent } from './tutor-type-update.component';

@Injectable({ providedIn: 'root' })
export class TutorTypeResolve implements Resolve<ITutorType> {
  constructor(private service: TutorTypeService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ITutorType> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((tutorType: HttpResponse<TutorType>) => {
          if (tutorType.body) {
            return of(tutorType.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new TutorType());
  }
}

export const tutorTypeRoute: Routes = [
  {
    path: '',
    component: TutorTypeComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'myschoolApp.tutorType.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: TutorTypeDetailComponent,
    resolve: {
      tutorType: TutorTypeResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'myschoolApp.tutorType.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: TutorTypeUpdateComponent,
    resolve: {
      tutorType: TutorTypeResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'myschoolApp.tutorType.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: TutorTypeUpdateComponent,
    resolve: {
      tutorType: TutorTypeResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'myschoolApp.tutorType.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
