import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { ISCRate } from 'app/shared/model/sc-rate.model';
import { SCRateService } from './sc-rate.service';
import { SCRateDeleteDialogComponent } from './sc-rate-delete-dialog.component';

@Component({
  selector: 'jhi-sc-rate',
  templateUrl: './sc-rate.component.html',
})
export class SCRateComponent implements OnInit, OnDestroy {
  sCRates?: ISCRate[];
  eventSubscriber?: Subscription;

  constructor(protected sCRateService: SCRateService, protected eventManager: JhiEventManager, protected modalService: NgbModal) {}

  loadAll(): void {
    this.sCRateService.query().subscribe((res: HttpResponse<ISCRate[]>) => (this.sCRates = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInSCRates();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: ISCRate): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInSCRates(): void {
    this.eventSubscriber = this.eventManager.subscribe('sCRateListModification', () => this.loadAll());
  }

  delete(sCRate: ISCRate): void {
    const modalRef = this.modalService.open(SCRateDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.sCRate = sCRate;
  }
}
