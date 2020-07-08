import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IEntityType } from 'app/shared/model/entity-type.model';
import { EntityTypeService } from './entity-type.service';
import { EntityTypeDeleteDialogComponent } from './entity-type-delete-dialog.component';

@Component({
  selector: 'jhi-entity-type',
  templateUrl: './entity-type.component.html',
})
export class EntityTypeComponent implements OnInit, OnDestroy {
  entityTypes?: IEntityType[];
  eventSubscriber?: Subscription;

  constructor(protected entityTypeService: EntityTypeService, protected eventManager: JhiEventManager, protected modalService: NgbModal) {}

  loadAll(): void {
    this.entityTypeService.query().subscribe((res: HttpResponse<IEntityType[]>) => (this.entityTypes = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInEntityTypes();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IEntityType): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInEntityTypes(): void {
    this.eventSubscriber = this.eventManager.subscribe('entityTypeListModification', () => this.loadAll());
  }

  delete(entityType: IEntityType): void {
    const modalRef = this.modalService.open(EntityTypeDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.entityType = entityType;
  }
}
