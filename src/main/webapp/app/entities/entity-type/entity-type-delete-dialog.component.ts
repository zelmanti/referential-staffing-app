import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IEntityType } from 'app/shared/model/entity-type.model';
import { EntityTypeService } from './entity-type.service';

@Component({
  templateUrl: './entity-type-delete-dialog.component.html',
})
export class EntityTypeDeleteDialogComponent {
  entityType?: IEntityType;

  constructor(
    protected entityTypeService: EntityTypeService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.entityTypeService.delete(id).subscribe(() => {
      this.eventManager.broadcast('entityTypeListModification');
      this.activeModal.close();
    });
  }
}
