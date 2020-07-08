export interface IClient {
  id?: number;
  nameClient?: string;
  namClientIris?: string;
  codeClientIris?: string;
  marche?: string;
  isActive?: boolean;
}

export class Client implements IClient {
  constructor(
    public id?: number,
    public nameClient?: string,
    public namClientIris?: string,
    public codeClientIris?: string,
    public marche?: string,
    public isActive?: boolean
  ) {
    this.isActive = this.isActive || false;
  }
}
