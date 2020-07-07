export interface IEntityType {
  id?: number;
  codeTypeEntitee?: string;
  libelleTypeEntitee?: string;
  isActive?: boolean;
}

export class EntityType implements IEntityType {
  constructor(public id?: number, public codeTypeEntitee?: string, public libelleTypeEntitee?: string, public isActive?: boolean) {
    this.isActive = this.isActive || false;
  }
}
