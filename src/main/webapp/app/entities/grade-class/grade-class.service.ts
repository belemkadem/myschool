import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IGradeClass } from 'app/shared/model/grade-class.model';

type EntityResponseType = HttpResponse<IGradeClass>;
type EntityArrayResponseType = HttpResponse<IGradeClass[]>;

@Injectable({ providedIn: 'root' })
export class GradeClassService {
  public resourceUrl = SERVER_API_URL + 'api/grade-classes';

  constructor(protected http: HttpClient) {}

  create(gradeClass: IGradeClass): Observable<EntityResponseType> {
    return this.http.post<IGradeClass>(this.resourceUrl, gradeClass, { observe: 'response' });
  }

  update(gradeClass: IGradeClass): Observable<EntityResponseType> {
    return this.http.put<IGradeClass>(this.resourceUrl, gradeClass, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IGradeClass>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IGradeClass[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
