import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { ITutorType } from 'app/shared/model/tutor-type.model';

type EntityResponseType = HttpResponse<ITutorType>;
type EntityArrayResponseType = HttpResponse<ITutorType[]>;

@Injectable({ providedIn: 'root' })
export class TutorTypeService {
  public resourceUrl = SERVER_API_URL + 'api/tutor-types';

  constructor(protected http: HttpClient) {}

  create(tutorType: ITutorType): Observable<EntityResponseType> {
    return this.http.post<ITutorType>(this.resourceUrl, tutorType, { observe: 'response' });
  }

  update(tutorType: ITutorType): Observable<EntityResponseType> {
    return this.http.put<ITutorType>(this.resourceUrl, tutorType, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<ITutorType>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ITutorType[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
