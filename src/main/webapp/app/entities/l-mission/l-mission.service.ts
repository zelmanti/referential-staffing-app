import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { ILMission } from 'app/shared/model/l-mission.model';

type EntityResponseType = HttpResponse<ILMission>;
type EntityArrayResponseType = HttpResponse<ILMission[]>;

@Injectable({ providedIn: 'root' })
export class LMissionService {
  public resourceUrl = SERVER_API_URL + 'api/l-missions';

  constructor(protected http: HttpClient) {}

  create(lMission: ILMission): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(lMission);
    return this.http
      .post<ILMission>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(lMission: ILMission): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(lMission);
    return this.http
      .put<ILMission>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<ILMission>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<ILMission[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(lMission: ILMission): ILMission {
    const copy: ILMission = Object.assign({}, lMission, {
      startDate: lMission.startDate && lMission.startDate.isValid() ? lMission.startDate.format(DATE_FORMAT) : undefined,
      endDate: lMission.endDate && lMission.endDate.isValid() ? lMission.endDate.format(DATE_FORMAT) : undefined,
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
      res.body.forEach((lMission: ILMission) => {
        lMission.startDate = lMission.startDate ? moment(lMission.startDate) : undefined;
        lMission.endDate = lMission.endDate ? moment(lMission.endDate) : undefined;
      });
    }
    return res;
  }
}
