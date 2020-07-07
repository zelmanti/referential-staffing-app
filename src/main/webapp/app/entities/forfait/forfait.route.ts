import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IForfait, Forfait } from 'app/shared/model/forfait.model';
import { ForfaitService } from './forfait.service';
import { ForfaitComponent } from './forfait.component';
import { ForfaitDetailComponent } from './forfait-detail.component';
import { ForfaitUpdateComponent } from './forfait-update.component';

@Injectable({ providedIn: 'root' })
export class ForfaitResolve implements Resolve<IForfait> {
  constructor(private service: ForfaitService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IForfait> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((forfait: HttpResponse<Forfait>) => {
          if (forfait.body) {
            return of(forfait.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new Forfait());
  }
}

export const forfaitRoute: Routes = [
  {
    path: '',
    component: ForfaitComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'referentialStaffingApp.forfait.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: ForfaitDetailComponent,
    resolve: {
      forfait: ForfaitResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'referentialStaffingApp.forfait.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: ForfaitUpdateComponent,
    resolve: {
      forfait: ForfaitResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'referentialStaffingApp.forfait.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: ForfaitUpdateComponent,
    resolve: {
      forfait: ForfaitResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'referentialStaffingApp.forfait.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
