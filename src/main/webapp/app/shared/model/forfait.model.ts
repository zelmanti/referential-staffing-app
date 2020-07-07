import { Moment } from 'moment';

export interface IForfait {
  id?: number;
  projet?: string;
  responsable?: string;
  startDate?: Moment;
  endDate?: Moment;
  ressource?: string;
  status?: string;
  lieu?: string;
}

export class Forfait implements IForfait {
  constructor(
    public id?: number,
    public projet?: string,
    public responsable?: string,
    public startDate?: Moment,
    public endDate?: Moment,
    public ressource?: string,
    public status?: string,
    public lieu?: string
  ) {}
}
