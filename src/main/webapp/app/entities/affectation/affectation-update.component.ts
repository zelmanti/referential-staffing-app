import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IAffectation, Affectation } from 'app/shared/model/affectation.model';
import { AffectationService } from './affectation.service';

@Component({
  selector: 'jhi-affectation-update',
  templateUrl: './affectation-update.component.html',
})
export class AffectationUpdateComponent implements OnInit {
  isSaving = false;
  startDateDp: any;
  endDateDp: any;

  editForm = this.fb.group({
    id: [],
    ressource: [],
    rattachementRessource: [],
    prestation: [],
    codeProjet: [],
    status: [],
    client: [],
    rattachementClient: [],
    startDate: [],
    endDate: [],
  });

  constructor(protected affectationService: AffectationService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ affectation }) => {
      this.updateForm(affectation);
    });
  }

  updateForm(affectation: IAffectation): void {
    this.editForm.patchValue({
      id: affectation.id,
      ressource: affectation.ressource,
      rattachementRessource: affectation.rattachementRessource,
      prestation: affectation.prestation,
      codeProjet: affectation.codeProjet,
      status: affectation.status,
      client: affectation.client,
      rattachementClient: affectation.rattachementClient,
      startDate: affectation.startDate,
      endDate: affectation.endDate,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const affectation = this.createFromForm();
    if (affectation.id !== undefined) {
      this.subscribeToSaveResponse(this.affectationService.update(affectation));
    } else {
      this.subscribeToSaveResponse(this.affectationService.create(affectation));
    }
  }

  private createFromForm(): IAffectation {
    return {
      ...new Affectation(),
      id: this.editForm.get(['id'])!.value,
      ressource: this.editForm.get(['ressource'])!.value,
      rattachementRessource: this.editForm.get(['rattachementRessource'])!.value,
      prestation: this.editForm.get(['prestation'])!.value,
      codeProjet: this.editForm.get(['codeProjet'])!.value,
      status: this.editForm.get(['status'])!.value,
      client: this.editForm.get(['client'])!.value,
      rattachementClient: this.editForm.get(['rattachementClient'])!.value,
      startDate: this.editForm.get(['startDate'])!.value,
      endDate: this.editForm.get(['endDate'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IAffectation>>): void {
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
