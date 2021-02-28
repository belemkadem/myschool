import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IGeneralTimeTable } from 'app/shared/model/general-time-table.model';

type EntityResponseType = HttpResponse<IGeneralTimeTable>;
type EntityArrayResponseType = HttpResponse<IGeneralTimeTable[]>;

@Injectable({ providedIn: 'root' })
export class GeneralTimeTableService {
  public resourceUrl = SERVER_API_URL + 'api/general-time-tables';

  constructor(protected http: HttpClient) {}

  create(generalTimeTable: IGeneralTimeTable): Observable<EntityResponseType> {
    return this.http.post<IGeneralTimeTable>(this.resourceUrl, generalTimeTable, { observe: 'response' });
  }

  update(generalTimeTable: IGeneralTimeTable): Observable<EntityResponseType> {
    return this.http.put<IGeneralTimeTable>(this.resourceUrl, generalTimeTable, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IGeneralTimeTable>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IGeneralTimeTable[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
