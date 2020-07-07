import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IEntityType } from 'app/shared/model/entity-type.model';

@Component({
  selector: 'jhi-entity-type-detail',
  templateUrl: './entity-type-detail.component.html',
})
export class EntityTypeDetailComponent implements OnInit {
  entityType: IEntityType | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ entityType }) => (this.entityType = entityType));
  }

  previousState(): void {
    window.history.back();
  }
}
