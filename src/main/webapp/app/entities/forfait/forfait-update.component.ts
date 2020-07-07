import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IForfait, Forfait } from 'app/shared/model/forfait.model';
import { ForfaitService } from './forfait.service';

@Component({
  selector: 'jhi-forfait-update',
  templateUrl: './forfait-update.component.html',
})
export class ForfaitUpdateComponent implements OnInit {
  isSaving = false;
  startDateDp: any;
  endDateDp: any;

  editForm = this.fb.group({
    id: [],
    projet: [],
    responsable: [],
    startDate: [],
    endDate: [],
    ressource: [],
    status: [],
    lieu: [],
  });

  constructor(protected forfaitService: ForfaitService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ forfait }) => {
      this.updateForm(forfait);
    });
  }

  updateForm(forfait: IForfait): void {
    this.editForm.patchValue({
      id: forfait.id,
      projet: forfait.projet,
      responsable: forfait.responsable,
      startDate: forfait.startDate,
      endDate: forfait.endDate,
      ressource: forfait.ressource,
      status: forfait.status,
      lieu: forfait.lieu,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const forfait = this.createFromForm();
    if (forfait.id !== undefined) {
      this.subscribeToSaveResponse(this.forfaitService.update(forfait));
    } else {
      this.subscribeToSaveResponse(this.forfaitService.create(forfait));
    }
  }

  private createFromForm(): IForfait {
    return {
      ...new Forfait(),
      id: this.editForm.get(['id'])!.value,
      projet: this.editForm.get(['projet'])!.value,
      responsable: this.editForm.get(['responsable'])!.value,
      startDate: this.editForm.get(['startDate'])!.value,
      endDate: this.editForm.get(['endDate'])!.value,
      ressource: this.editForm.get(['ressource'])!.value,
      status: this.editForm.get(['status'])!.value,
      lieu: this.editForm.get(['lieu'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IForfait>>): void {
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
