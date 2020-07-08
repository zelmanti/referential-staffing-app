import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IEntityType } from 'app/shared/model/entity-type.model';

type EntityResponseType = HttpResponse<IEntityType>;
type EntityArrayResponseType = HttpResponse<IEntityType[]>;

@Injectable({ providedIn: 'root' })
export class EntityTypeService {
  public resourceUrl = SERVER_API_URL + 'api/entity-types';

  constructor(protected http: HttpClient) {}

  create(entityType: IEntityType): Observable<EntityResponseType> {
    return this.http.post<IEntityType>(this.resourceUrl, entityType, { observe: 'response' });
  }

  update(entityType: IEntityType): Observable<EntityResponseType> {
    return this.http.put<IEntityType>(this.resourceUrl, entityType, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IEntityType>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IEntityType[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
