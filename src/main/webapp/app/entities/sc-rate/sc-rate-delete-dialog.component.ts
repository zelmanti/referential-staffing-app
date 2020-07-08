import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ISCRate } from 'app/shared/model/sc-rate.model';
import { SCRateService } from './sc-rate.service';

@Component({
  templateUrl: './sc-rate-delete-dialog.component.html',
})
export class SCRateDeleteDialogComponent {
  sCRate?: ISCRate;

  constructor(protected sCRateService: SCRateService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.sCRateService.delete(id).subscribe(() => {
      this.eventManager.broadcast('sCRateListModification');
      this.activeModal.close();
    });
  }
}
