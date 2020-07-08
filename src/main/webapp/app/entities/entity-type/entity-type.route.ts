import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IEntityType, EntityType } from 'app/shared/model/entity-type.model';
import { EntityTypeService } from './entity-type.service';
import { EntityTypeComponent } from './entity-type.component';
import { EntityTypeDetailComponent } from './entity-type-detail.component';
import { EntityTypeUpdateComponent } from './entity-type-update.component';

@Injectable({ providedIn: 'root' })
export class EntityTypeResolve implements Resolve<IEntityType> {
  constructor(private service: EntityTypeService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IEntityType> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((entityType: HttpResponse<EntityType>) => {
          if (entityType.body) {
            return of(entityType.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new EntityType());
  }
}

export const entityTypeRoute: Routes = [
  {
    path: '',
    component: EntityTypeComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'referentialStaffingApp.entityType.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: EntityTypeDetailComponent,
    resolve: {
      entityType: EntityTypeResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'referentialStaffingApp.entityType.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: EntityTypeUpdateComponent,
    resolve: {
      entityType: EntityTypeResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'referentialStaffingApp.entityType.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: EntityTypeUpdateComponent,
    resolve: {
      entityType: EntityTypeResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'referentialStaffingApp.entityType.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
