import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IDFinanciere } from 'app/shared/model/d-financiere.model';
import { DFinanciereService } from './d-financiere.service';
import { DFinanciereDeleteDialogComponent } from './d-financiere-delete-dialog.component';

@Component({
  selector: 'jhi-d-financiere',
  templateUrl: './d-financiere.component.html',
})
export class DFinanciereComponent implements OnInit, OnDestroy {
  dFinancieres?: IDFinanciere[];
  eventSubscriber?: Subscription;

  constructor(
    protected dFinanciereService: DFinanciereService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.dFinanciereService.query().subscribe((res: HttpResponse<IDFinanciere[]>) => (this.dFinancieres = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInDFinancieres();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IDFinanciere): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInDFinancieres(): void {
    this.eventSubscriber = this.eventManager.subscribe('dFinanciereListModification', () => this.loadAll());
  }

  delete(dFinanciere: IDFinanciere): void {
    const modalRef = this.modalService.open(DFinanciereDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.dFinanciere = dFinanciere;
  }
}
