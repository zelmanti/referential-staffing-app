import { Moment } from 'moment';

export interface IDFinanciere {
  id?: number;
  startDate?: string;
  endDate?: string;
  tjm?: number;
  rfa?: number;
  fraisMentuels?: number;
  fraisJournaliers?: number;
  margeCalculee?: number;
  indicateurTRevenue?: string;
  joursTravailee?: Moment;
  moisTravailee?: Moment;
  anneesTravailee?: Moment;
  scr?: string;
  chiffreAffaireCalculee?: number;
  coutsCalculee?: number;
}

export class DFinanciere implements IDFinanciere {
  constructor(
    public id?: number,
    public startDate?: string,
    public endDate?: string,
    public tjm?: number,
    public rfa?: number,
    public fraisMentuels?: number,
    public fraisJournaliers?: number,
    public margeCalculee?: number,
    public indicateurTRevenue?: string,
    public joursTravailee?: Moment,
    public moisTravailee?: Moment,
    public anneesTravailee?: Moment,
    public scr?: string,
    public chiffreAffaireCalculee?: number,
    public coutsCalculee?: number
  ) {}
}
