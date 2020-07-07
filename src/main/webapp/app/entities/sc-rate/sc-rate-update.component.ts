import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { ISCRate, SCRate } from 'app/shared/model/sc-rate.model';
import { SCRateService } from './sc-rate.service';

@Component({
  selector: 'jhi-sc-rate-update',
  templateUrl: './sc-rate-update.component.html',
})
export class SCRateUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    code: [],
    niveau: [],
    localisation: [],
    montant: [],
  });

  constructor(protected sCRateService: SCRateService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ sCRate }) => {
      this.updateForm(sCRate);
    });
  }

  updateForm(sCRate: ISCRate): void {
    this.editForm.patchValue({
      id: sCRate.id,
      code: sCRate.code,
      niveau: sCRate.niveau,
      localisation: sCRate.localisation,
      montant: sCRate.montant,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const sCRate = this.createFromForm();
    if (sCRate.id !== undefined) {
      this.subscribeToSaveResponse(this.sCRateService.update(sCRate));
    } else {
      this.subscribeToSaveResponse(this.sCRateService.create(sCRate));
    }
  }

  private createFromForm(): ISCRate {
    return {
      ...new SCRate(),
      id: this.editForm.get(['id'])!.value,
      code: this.editForm.get(['code'])!.value,
      niveau: this.editForm.get(['niveau'])!.value,
      localisation: this.editForm.get(['localisation'])!.value,
      montant: this.editForm.get(['montant'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ISCRate>>): void {
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
