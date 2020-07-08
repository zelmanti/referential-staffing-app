export interface IEntite {
  id?: number;
  codeEntity?: string;
  libeleEntity?: string;
  typeEntitee?: string;
  manager?: string;
}

export class Entite implements IEntite {
  constructor(
    public id?: number,
    public codeEntity?: string,
    public libeleEntity?: string,
    public typeEntitee?: string,
    public manager?: string
  ) {}
}
