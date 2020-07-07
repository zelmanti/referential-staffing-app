import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { ISCRate } from 'app/shared/model/sc-rate.model';

type EntityResponseType = HttpResponse<ISCRate>;
type EntityArrayResponseType = HttpResponse<ISCRate[]>;

@Injectable({ providedIn: 'root' })
export class SCRateService {
  public resourceUrl = SERVER_API_URL + 'api/sc-rates';

  constructor(protected http: HttpClient) {}

  create(sCRate: ISCRate): Observable<EntityResponseType> {
    return this.http.post<ISCRate>(this.resourceUrl, sCRate, { observe: 'response' });
  }

  update(sCRate: ISCRate): Observable<EntityResponseType> {
    return this.http.put<ISCRate>(this.resourceUrl, sCRate, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<ISCRate>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ISCRate[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
