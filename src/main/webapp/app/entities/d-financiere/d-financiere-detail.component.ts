import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IDFinanciere } from 'app/shared/model/d-financiere.model';

@Component({
  selector: 'jhi-d-financiere-detail',
  templateUrl: './d-financiere-detail.component.html',
})
export class DFinanciereDetailComponent implements OnInit {
  dFinanciere: IDFinanciere | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ dFinanciere }) => (this.dFinanciere = dFinanciere));
  }

  previousState(): void {
    window.history.back();
  }
}
