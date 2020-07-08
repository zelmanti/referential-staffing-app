import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IForfait } from 'app/shared/model/forfait.model';
import { ForfaitService } from './forfait.service';

@Component({
  templateUrl: './forfait-delete-dialog.component.html',
})
export class ForfaitDeleteDialogComponent {
  forfait?: IForfait;

  constructor(protected forfaitService: ForfaitService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.forfaitService.delete(id).subscribe(() => {
      this.eventManager.broadcast('forfaitListModification');
      this.activeModal.close();
    });
  }
}
