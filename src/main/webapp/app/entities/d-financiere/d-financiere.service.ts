import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IDFinanciere } from 'app/shared/model/d-financiere.model';

type EntityResponseType = HttpResponse<IDFinanciere>;
type EntityArrayResponseType = HttpResponse<IDFinanciere[]>;

@Injectable({ providedIn: 'root' })
export class DFinanciereService {
  public resourceUrl = SERVER_API_URL + 'api/d-financieres';

  constructor(protected http: HttpClient) {}

  create(dFinanciere: IDFinanciere): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(dFinanciere);
    return this.http
      .post<IDFinanciere>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(dFinanciere: IDFinanciere): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(dFinanciere);
    return this.http
      .put<IDFinanciere>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IDFinanciere>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IDFinanciere[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(dFinanciere: IDFinanciere): IDFinanciere {
    const copy: IDFinanciere = Object.assign({}, dFinanciere, {
      joursTravailee:
        dFinanciere.joursTravailee && dFinanciere.joursTravailee.isValid() ? dFinanciere.joursTravailee.format(DATE_FORMAT) : undefined,
      moisTravailee:
        dFinanciere.moisTravailee && dFinanciere.moisTravailee.isValid() ? dFinanciere.moisTravailee.format(DATE_FORMAT) : undefined,
      anneesTravailee:
        dFinanciere.anneesTravailee && dFinanciere.anneesTravailee.isValid() ? dFinanciere.anneesTravailee.format(DATE_FORMAT) : undefined,
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.joursTravailee = res.body.joursTravailee ? moment(res.body.joursTravailee) : undefined;
      res.body.moisTravailee = res.body.moisTravailee ? moment(res.body.moisTravailee) : undefined;
      res.body.anneesTravailee = res.body.anneesTravailee ? moment(res.body.anneesTravailee) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((dFinanciere: IDFinanciere) => {
        dFinanciere.joursTravailee = dFinanciere.joursTravailee ? moment(dFinanciere.joursTravailee) : undefined;
        dFinanciere.moisTravailee = dFinanciere.moisTravailee ? moment(dFinanciere.moisTravailee) : undefined;
        dFinanciere.anneesTravailee = dFinanciere.anneesTravailee ? moment(dFinanciere.anneesTravailee) : undefined;
      });
    }
    return res;
  }
}
