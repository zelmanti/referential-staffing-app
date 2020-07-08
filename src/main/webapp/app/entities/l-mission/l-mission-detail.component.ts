import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ILMission } from 'app/shared/model/l-mission.model';

@Component({
  selector: 'jhi-l-mission-detail',
  templateUrl: './l-mission-detail.component.html',
})
export class LMissionDetailComponent implements OnInit {
  lMission: ILMission | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ lMission }) => (this.lMission = lMission));
  }

  previousState(): void {
    window.history.back();
  }
}
