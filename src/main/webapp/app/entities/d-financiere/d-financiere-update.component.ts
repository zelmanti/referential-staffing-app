import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IDFinanciere, DFinanciere } from 'app/shared/model/d-financiere.model';
import { DFinanciereService } from './d-financiere.service';

@Component({
  selector: 'jhi-d-financiere-update',
  templateUrl: './d-financiere-update.component.html',
})
export class DFinanciereUpdateComponent implements OnInit {
  isSaving = false;
  joursTravaileeDp: any;
  moisTravaileeDp: any;
  anneesTravaileeDp: any;

  editForm = this.fb.group({
    id: [],
    startDate: [],
    endDate: [],
    tjm: [],
    rfa: [],
    fraisMentuels: [],
    fraisJournaliers: [],
    margeCalculee: [],
    indicateurTRevenue: [],
    joursTravailee: [],
    moisTravailee: [],
    anneesTravailee: [],
    scr: [],
    chiffreAffaireCalculee: [],
    coutsCalculee: [],
  });

  constructor(protected dFinanciereService: DFinanciereService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ dFinanciere }) => {
      this.updateForm(dFinanciere);
    });
  }

  updateForm(dFinanciere: IDFinanciere): void {
    this.editForm.patchValue({
      id: dFinanciere.id,
      startDate: dFinanciere.startDate,
      endDate: dFinanciere.endDate,
      tjm: dFinanciere.tjm,
      rfa: dFinanciere.rfa,
      fraisMentuels: dFinanciere.fraisMentuels,
      fraisJournaliers: dFinanciere.fraisJournaliers,
      margeCalculee: dFinanciere.margeCalculee,
      indicateurTRevenue: dFinanciere.indicateurTRevenue,
      joursTravailee: dFinanciere.joursTravailee,
      moisTravailee: dFinanciere.moisTravailee,
      anneesTravailee: dFinanciere.anneesTravailee,
      scr: dFinanciere.scr,
      chiffreAffaireCalculee: dFinanciere.chiffreAffaireCalculee,
      coutsCalculee: dFinanciere.coutsCalculee,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const dFinanciere = this.createFromForm();
    if (dFinanciere.id !== undefined) {
      this.subscribeToSaveResponse(this.dFinanciereService.update(dFinanciere));
    } else {
      this.subscribeToSaveResponse(this.dFinanciereService.create(dFinanciere));
    }
  }

  private createFromForm(): IDFinanciere {
    return {
      ...new DFinanciere(),
      id: this.editForm.get(['id'])!.value,
      startDate: this.editForm.get(['startDate'])!.value,
      endDate: this.editForm.get(['endDate'])!.value,
      tjm: this.editForm.get(['tjm'])!.value,
      rfa: this.editForm.get(['rfa'])!.value,
      fraisMentuels: this.editForm.get(['fraisMentuels'])!.value,
      fraisJournaliers: this.editForm.get(['fraisJournaliers'])!.value,
      margeCalculee: this.editForm.get(['margeCalculee'])!.value,
      indicateurTRevenue: this.editForm.get(['indicateurTRevenue'])!.value,
      joursTravailee: this.editForm.get(['joursTravailee'])!.value,
      moisTravailee: this.editForm.get(['moisTravailee'])!.value,
      anneesTravailee: this.editForm.get(['anneesTravailee'])!.value,
      scr: this.editForm.get(['scr'])!.value,
      chiffreAffaireCalculee: this.editForm.get(['chiffreAffaireCalculee'])!.value,
      coutsCalculee: this.editForm.get(['coutsCalculee'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IDFinanciere>>): void {
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
