import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ILMission } from 'app/shared/model/l-mission.model';
import { LMissionService } from './l-mission.service';

@Component({
  templateUrl: './l-mission-delete-dialog.component.html',
})
export class LMissionDeleteDialogComponent {
  lMission?: ILMission;

  constructor(protected lMissionService: LMissionService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.lMissionService.delete(id).subscribe(() => {
      this.eventManager.broadcast('lMissionListModification');
      this.activeModal.close();
    });
  }
}
