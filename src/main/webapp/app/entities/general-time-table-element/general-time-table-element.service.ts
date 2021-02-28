import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IGeneralTimeTableElement } from 'app/shared/model/general-time-table-element.model';

type EntityResponseType = HttpResponse<IGeneralTimeTableElement>;
type EntityArrayResponseType = HttpResponse<IGeneralTimeTableElement[]>;

@Injectable({ providedIn: 'root' })
export class GeneralTimeTableElementService {
  public resourceUrl = SERVER_API_URL + 'api/general-time-table-elements';

  constructor(protected http: HttpClient) {}

  create(generalTimeTableElement: IGeneralTimeTableElement): Observable<EntityResponseType> {
    return this.http.post<IGeneralTimeTableElement>(this.resourceUrl, generalTimeTableElement, { observe: 'response' });
  }

  update(generalTimeTableElement: IGeneralTimeTableElement): Observable<EntityResponseType> {
    return this.http.put<IGeneralTimeTableElement>(this.resourceUrl, generalTimeTableElement, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IGeneralTimeTableElement>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IGeneralTimeTableElement[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
