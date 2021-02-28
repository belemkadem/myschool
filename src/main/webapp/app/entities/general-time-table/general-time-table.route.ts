import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IGeneralTimeTable, GeneralTimeTable } from 'app/shared/model/general-time-table.model';
import { GeneralTimeTableService } from './general-time-table.service';
import { GeneralTimeTableComponent } from './general-time-table.component';
import { GeneralTimeTableDetailComponent } from './general-time-table-detail.component';
import { GeneralTimeTableUpdateComponent } from './general-time-table-update.component';

@Injectable({ providedIn: 'root' })
export class GeneralTimeTableResolve implements Resolve<IGeneralTimeTable> {
  constructor(private service: GeneralTimeTableService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IGeneralTimeTable> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((generalTimeTable: HttpResponse<GeneralTimeTable>) => {
          if (generalTimeTable.body) {
            return of(generalTimeTable.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new GeneralTimeTable());
  }
}

export const generalTimeTableRoute: Routes = [
  {
    path: '',
    component: GeneralTimeTableComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'myschoolApp.generalTimeTable.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: GeneralTimeTableDetailComponent,
    resolve: {
      generalTimeTable: GeneralTimeTableResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'myschoolApp.generalTimeTable.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: GeneralTimeTableUpdateComponent,
    resolve: {
      generalTimeTable: GeneralTimeTableResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'myschoolApp.generalTimeTable.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: GeneralTimeTableUpdateComponent,
    resolve: {
      generalTimeTable: GeneralTimeTableResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'myschoolApp.generalTimeTable.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
