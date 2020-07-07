import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { ILMission } from 'app/shared/model/l-mission.model';
import { LMissionService } from './l-mission.service';
import { LMissionDeleteDialogComponent } from './l-mission-delete-dialog.component';

@Component({
  selector: 'jhi-l-mission',
  templateUrl: './l-mission.component.html',
})
export class LMissionComponent implements OnInit, OnDestroy {
  lMissions?: ILMission[];
  eventSubscriber?: Subscription;

  constructor(protected lMissionService: LMissionService, protected eventManager: JhiEventManager, protected modalService: NgbModal) {}

  loadAll(): void {
    this.lMissionService.query().subscribe((res: HttpResponse<ILMission[]>) => (this.lMissions = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInLMissions();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: ILMission): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInLMissions(): void {
    this.eventSubscriber = this.eventManager.subscribe('lMissionListModification', () => this.loadAll());
  }

  delete(lMission: ILMission): void {
    const modalRef = this.modalService.open(LMissionDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.lMission = lMission;
  }
}
