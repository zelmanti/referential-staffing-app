export interface ISCRate {
  id?: number;
  code?: string;
  niveau?: string;
  localisation?: string;
  montant?: string;
}

export class SCRate implements ISCRate {
  constructor(public id?: number, public code?: string, public niveau?: string, public localisation?: string, public montant?: string) {}
}
