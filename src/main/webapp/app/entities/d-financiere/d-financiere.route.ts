import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IDFinanciere, DFinanciere } from 'app/shared/model/d-financiere.model';
import { DFinanciereService } from './d-financiere.service';
import { DFinanciereComponent } from './d-financiere.component';
import { DFinanciereDetailComponent } from './d-financiere-detail.component';
import { DFinanciereUpdateComponent } from './d-financiere-update.component';

@Injectable({ providedIn: 'root' })
export class DFinanciereResolve implements Resolve<IDFinanciere> {
  constructor(private service: DFinanciereService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IDFinanciere> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((dFinanciere: HttpResponse<DFinanciere>) => {
          if (dFinanciere.body) {
            return of(dFinanciere.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new DFinanciere());
  }
}

export const dFinanciereRoute: Routes = [
  {
    path: '',
    component: DFinanciereComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'referentialStaffingApp.dFinanciere.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: DFinanciereDetailComponent,
    resolve: {
      dFinanciere: DFinanciereResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'referentialStaffingApp.dFinanciere.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: DFinanciereUpdateComponent,
    resolve: {
      dFinanciere: DFinanciereResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'referentialStaffingApp.dFinanciere.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: DFinanciereUpdateComponent,
    resolve: {
      dFinanciere: DFinanciereResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'referentialStaffingApp.dFinanciere.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
