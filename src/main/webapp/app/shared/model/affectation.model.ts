import { Moment } from 'moment';

export interface IAffectation {
  id?: number;
  ressource?: string;
  rattachementRessource?: string;
  prestation?: string;
  codeProjet?: string;
  status?: string;
  client?: string;
  rattachementClient?: string;
  startDate?: Moment;
  endDate?: Moment;
}

export class Affectation implements IAffectation {
  constructor(
    public id?: number,
    public ressource?: string,
    public rattachementRessource?: string,
    public prestation?: string,
    public codeProjet?: string,
    public status?: string,
    public client?: string,
    public rattachementClient?: string,
    public startDate?: Moment,
    public endDate?: Moment
  ) {}
}
