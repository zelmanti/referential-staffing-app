import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IForfait } from 'app/shared/model/forfait.model';
import { ForfaitService } from './forfait.service';
import { ForfaitDeleteDialogComponent } from './forfait-delete-dialog.component';

@Component({
  selector: 'jhi-forfait',
  templateUrl: './forfait.component.html',
})
export class ForfaitComponent implements OnInit, OnDestroy {
  forfaits?: IForfait[];
  eventSubscriber?: Subscription;

  constructor(protected forfaitService: ForfaitService, protected eventManager: JhiEventManager, protected modalService: NgbModal) {}

  loadAll(): void {
    this.forfaitService.query().subscribe((res: HttpResponse<IForfait[]>) => (this.forfaits = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInForfaits();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IForfait): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInForfaits(): void {
    this.eventSubscriber = this.eventManager.subscribe('forfaitListModification', () => this.loadAll());
  }

  delete(forfait: IForfait): void {
    const modalRef = this.modalService.open(ForfaitDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.forfait = forfait;
  }
}
