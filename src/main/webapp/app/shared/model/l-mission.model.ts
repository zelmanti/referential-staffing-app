import { Moment } from 'moment';

export interface ILMission {
  id?: number;
  startDate?: Moment;
  endDate?: Moment;
  descriptionMission?: string;
  indicateurRenouvelement?: string;
  isActive?: string;
}

export class LMission implements ILMission {
  constructor(
    public id?: number,
    public startDate?: Moment,
    public endDate?: Moment,
    public descriptionMission?: string,
    public indicateurRenouvelement?: string,
    public isActive?: string
  ) {}
}
