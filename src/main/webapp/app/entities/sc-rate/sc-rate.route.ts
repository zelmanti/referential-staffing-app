import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { ISCRate, SCRate } from 'app/shared/model/sc-rate.model';
import { SCRateService } from './sc-rate.service';
import { SCRateComponent } from './sc-rate.component';
import { SCRateDetailComponent } from './sc-rate-detail.component';
import { SCRateUpdateComponent } from './sc-rate-update.component';

@Injectable({ providedIn: 'root' })
export class SCRateResolve implements Resolve<ISCRate> {
  constructor(private service: SCRateService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ISCRate> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((sCRate: HttpResponse<SCRate>) => {
          if (sCRate.body) {
            return of(sCRate.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new SCRate());
  }
}

export const sCRateRoute: Routes = [
  {
    path: '',
    component: SCRateComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'referentialStaffingApp.sCRate.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: SCRateDetailComponent,
    resolve: {
      sCRate: SCRateResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'referentialStaffingApp.sCRate.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: SCRateUpdateComponent,
    resolve: {
      sCRate: SCRateResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'referentialStaffingApp.sCRate.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: SCRateUpdateComponent,
    resolve: {
      sCRate: SCRateResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'referentialStaffingApp.sCRate.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
