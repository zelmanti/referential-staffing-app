import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { ILMission, LMission } from 'app/shared/model/l-mission.model';
import { LMissionService } from './l-mission.service';

@Component({
  selector: 'jhi-l-mission-update',
  templateUrl: './l-mission-update.component.html',
})
export class LMissionUpdateComponent implements OnInit {
  isSaving = false;
  startDateDp: any;
  endDateDp: any;

  editForm = this.fb.group({
    id: [],
    startDate: [],
    endDate: [],
    descriptionMission: [],
    indicateurRenouvelement: [],
    isActive: [],
  });

  constructor(protected lMissionService: LMissionService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ lMission }) => {
      this.updateForm(lMission);
    });
  }

  updateForm(lMission: ILMission): void {
    this.editForm.patchValue({
      id: lMission.id,
      startDate: lMission.startDate,
      endDate: lMission.endDate,
      descriptionMission: lMission.descriptionMission,
      indicateurRenouvelement: lMission.indicateurRenouvelement,
      isActive: lMission.isActive,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const lMission = this.createFromForm();
    if (lMission.id !== undefined) {
      this.subscribeToSaveResponse(this.lMissionService.update(lMission));
    } else {
      this.subscribeToSaveResponse(this.lMissionService.create(lMission));
    }
  }

  private createFromForm(): ILMission {
    return {
      ...new LMission(),
      id: this.editForm.get(['id'])!.value,
      startDate: this.editForm.get(['startDate'])!.value,
      endDate: this.editForm.get(['endDate'])!.value,
      descriptionMission: this.editForm.get(['descriptionMission'])!.value,
      indicateurRenouvelement: this.editForm.get(['indicateurRenouvelement'])!.value,
      isActive: this.editForm.get(['isActive'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ILMission>>): void {
    result.subscribe(
      () => this.onSaveSuccess(),
      () => this.onSaveError()
    );
  }

  protected onSaveSuccess(): void {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError(): void {
    this.isSaving = false;
  }
}
