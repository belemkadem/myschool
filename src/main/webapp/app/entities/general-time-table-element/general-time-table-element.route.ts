import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IGeneralTimeTableElement, GeneralTimeTableElement } from 'app/shared/model/general-time-table-element.model';
import { GeneralTimeTableElementService } from './general-time-table-element.service';
import { GeneralTimeTableElementComponent } from './general-time-table-element.component';
import { GeneralTimeTableElementDetailComponent } from './general-time-table-element-detail.component';
import { GeneralTimeTableElementUpdateComponent } from './general-time-table-element-update.component';

@Injectable({ providedIn: 'root' })
export class GeneralTimeTableElementResolve implements Resolve<IGeneralTimeTableElement> {
  constructor(private service: GeneralTimeTableElementService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IGeneralTimeTableElement> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((generalTimeTableElement: HttpResponse<GeneralTimeTableElement>) => {
          if (generalTimeTableElement.body) {
            return of(generalTimeTableElement.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new GeneralTimeTableElement());
  }
}

export const generalTimeTableElementRoute: Routes = [
  {
    path: '',
    component: GeneralTimeTableElementComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'myschoolApp.generalTimeTableElement.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: GeneralTimeTableElementDetailComponent,
    resolve: {
      generalTimeTableElement: GeneralTimeTableElementResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'myschoolApp.generalTimeTableElement.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: GeneralTimeTableElementUpdateComponent,
    resolve: {
      generalTimeTableElement: GeneralTimeTableElementResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'myschoolApp.generalTimeTableElement.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: GeneralTimeTableElementUpdateComponent,
    resolve: {
      generalTimeTableElement: GeneralTimeTableElementResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'myschoolApp.generalTimeTableElement.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
