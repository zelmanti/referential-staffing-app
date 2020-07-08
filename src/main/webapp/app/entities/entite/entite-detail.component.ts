import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IEntite } from 'app/shared/model/entite.model';

@Component({
  selector: 'jhi-entite-detail',
  templateUrl: './entite-detail.component.html',
})
export class EntiteDetailComponent implements OnInit {
  entite: IEntite | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ entite }) => (this.entite = entite));
  }

  previousState(): void {
    window.history.back();
  }
}
