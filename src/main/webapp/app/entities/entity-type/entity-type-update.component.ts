import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IEntityType, EntityType } from 'app/shared/model/entity-type.model';
import { EntityTypeService } from './entity-type.service';

@Component({
  selector: 'jhi-entity-type-update',
  templateUrl: './entity-type-update.component.html',
})
export class EntityTypeUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    codeTypeEntitee: [],
    libelleTypeEntitee: [],
    isActive: [],
  });

  constructor(protected entityTypeService: EntityTypeService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ entityType }) => {
      this.updateForm(entityType);
    });
  }

  updateForm(entityType: IEntityType): void {
    this.editForm.patchValue({
      id: entityType.id,
      codeTypeEntitee: entityType.codeTypeEntitee,
      libelleTypeEntitee: entityType.libelleTypeEntitee,
      isActive: entityType.isActive,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const entityType = this.createFromForm();
    if (entityType.id !== undefined) {
      this.subscribeToSaveResponse(this.entityTypeService.update(entityType));
    } else {
      this.subscribeToSaveResponse(this.entityTypeService.create(entityType));
    }
  }

  private createFromForm(): IEntityType {
    return {
      ...new EntityType(),
      id: this.editForm.get(['id'])!.value,
      codeTypeEntitee: this.editForm.get(['codeTypeEntitee'])!.value,
      libelleTypeEntitee: this.editForm.get(['libelleTypeEntitee'])!.value,
      isActive: this.editForm.get(['isActive'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IEntityType>>): void {
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
