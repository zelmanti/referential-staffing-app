import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IDFinanciere } from 'app/shared/model/d-financiere.model';
import { DFinanciereService } from './d-financiere.service';

@Component({
  templateUrl: './d-financiere-delete-dialog.component.html',
})
export class DFinanciereDeleteDialogComponent {
  dFinanciere?: IDFinanciere;

  constructor(
    protected dFinanciereService: DFinanciereService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.dFinanciereService.delete(id).subscribe(() => {
      this.eventManager.broadcast('dFinanciereListModification');
      this.activeModal.close();
    });
  }
}
