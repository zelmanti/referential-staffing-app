import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IForfait } from 'app/shared/model/forfait.model';

type EntityResponseType = HttpResponse<IForfait>;
type EntityArrayResponseType = HttpResponse<IForfait[]>;

@Injectable({ providedIn: 'root' })
export class ForfaitService {
  public resourceUrl = SERVER_API_URL + 'api/forfaits';

  constructor(protected http: HttpClient) {}

  create(forfait: IForfait): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(forfait);
    return this.http
      .post<IForfait>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(forfait: IForfait): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(forfait);
    return this.http
      .put<IForfait>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IForfait>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IForfait[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(forfait: IForfait): IForfait {
    const copy: IForfait = Object.assign({}, forfait, {
      startDate: forfait.startDate && forfait.startDate.isValid() ? forfait.startDate.format(DATE_FORMAT) : undefined,
      endDate: forfait.endDate && forfait.endDate.isValid() ? forfait.endDate.format(DATE_FORMAT) : undefined,
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.startDate = res.body.startDate ? moment(res.body.startDate) : undefined;
      res.body.endDate = res.body.endDate ? moment(res.body.endDate) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((forfait: IForfait) => {
        forfait.startDate = forfait.startDate ? moment(forfait.startDate) : undefined;
        forfait.endDate = forfait.endDate ? moment(forfait.endDate) : undefined;
      });
    }
    return res;
  }
}
