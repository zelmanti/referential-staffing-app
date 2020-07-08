import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { ILMission, LMission } from 'app/shared/model/l-mission.model';
import { LMissionService } from './l-mission.service';
import { LMissionComponent } from './l-mission.component';
import { LMissionDetailComponent } from './l-mission-detail.component';
import { LMissionUpdateComponent } from './l-mission-update.component';

@Injectable({ providedIn: 'root' })
export class LMissionResolve implements Resolve<ILMission> {
  constructor(private service: LMissionService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ILMission> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((lMission: HttpResponse<LMission>) => {
          if (lMission.body) {
            return of(lMission.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new LMission());
  }
}

export const lMissionRoute: Routes = [
  {
    path: '',
    component: LMissionComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'referentialStaffingApp.lMission.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: LMissionDetailComponent,
    resolve: {
      lMission: LMissionResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'referentialStaffingApp.lMission.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: LMissionUpdateComponent,
    resolve: {
      lMission: LMissionResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'referentialStaffingApp.lMission.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: LMissionUpdateComponent,
    resolve: {
      lMission: LMissionResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'referentialStaffingApp.lMission.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
