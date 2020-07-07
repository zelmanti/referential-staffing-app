import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ISCRate } from 'app/shared/model/sc-rate.model';

@Component({
  selector: 'jhi-sc-rate-detail',
  templateUrl: './sc-rate-detail.component.html',
})
export class SCRateDetailComponent implements OnInit {
  sCRate: ISCRate | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ sCRate }) => (this.sCRate = sCRate));
  }

  previousState(): void {
    window.history.back();
  }
}
