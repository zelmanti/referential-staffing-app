import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IEntite } from 'app/shared/model/entite.model';
import { EntiteService } from './entite.service';
import { EntiteDeleteDialogComponent } from './entite-delete-dialog.component';

@Component({
  selector: 'jhi-entite',
  templateUrl: './entite.component.html',
})
export class EntiteComponent implements OnInit, OnDestroy {
  entites?: IEntite[];
  eventSubscriber?: Subscription;

  constructor(protected entiteService: EntiteService, protected eventManager: JhiEventManager, protected modalService: NgbModal) {}

  loadAll(): void {
    this.entiteService.query().subscribe((res: HttpResponse<IEntite[]>) => (this.entites = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInEntites();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IEntite): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInEntites(): void {
    this.eventSubscriber = this.eventManager.subscribe('entiteListModification', () => this.loadAll());
  }

  delete(entite: IEntite): void {
    const modalRef = this.modalService.open(EntiteDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.entite = entite;
  }
}
