import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IEntite, Entite } from 'app/shared/model/entite.model';
import { EntiteService } from './entite.service';

@Component({
  selector: 'jhi-entite-update',
  templateUrl: './entite-update.component.html',
})
export class EntiteUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    codeEntity: [],
    libeleEntity: [],
    typeEntitee: [],
    manager: [],
  });

  constructor(protected entiteService: EntiteService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ entite }) => {
      this.updateForm(entite);
    });
  }

  updateForm(entite: IEntite): void {
    this.editForm.patchValue({
      id: entite.id,
      codeEntity: entite.codeEntity,
      libeleEntity: entite.libeleEntity,
      typeEntitee: entite.typeEntitee,
      manager: entite.manager,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const entite = this.createFromForm();
    if (entite.id !== undefined) {
      this.subscribeToSaveResponse(this.entiteService.update(entite));
    } else {
      this.subscribeToSaveResponse(this.entiteService.create(entite));
    }
  }

  private createFromForm(): IEntite {
    return {
      ...new Entite(),
      id: this.editForm.get(['id'])!.value,
      codeEntity: this.editForm.get(['codeEntity'])!.value,
      libeleEntity: this.editForm.get(['libeleEntity'])!.value,
      typeEntitee: this.editForm.get(['typeEntitee'])!.value,
      manager: this.editForm.get(['manager'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IEntite>>): void {
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
