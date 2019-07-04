import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { ICustomerdetail } from 'app/shared/model/customerdetail.model';

type EntityResponseType = HttpResponse<ICustomerdetail>;
type EntityArrayResponseType = HttpResponse<ICustomerdetail[]>;

@Injectable({ providedIn: 'root' })
export class CustomerdetailService {
  public resourceUrl = SERVER_API_URL + 'api/customerdetails';
  public resourceSearchUrl = SERVER_API_URL + 'api/_search/customerdetails';

  constructor(protected http: HttpClient) {}

  create(customerdetail: ICustomerdetail): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(customerdetail);
    return this.http
      .post<ICustomerdetail>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(customerdetail: ICustomerdetail): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(customerdetail);
    return this.http
      .put<ICustomerdetail>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<ICustomerdetail>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<ICustomerdetail[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  search(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<ICustomerdetail[]>(this.resourceSearchUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  protected convertDateFromClient(customerdetail: ICustomerdetail): ICustomerdetail {
    const copy: ICustomerdetail = Object.assign({}, customerdetail, {
      expectedEndDate:
        customerdetail.expectedEndDate != null && customerdetail.expectedEndDate.isValid() ? customerdetail.expectedEndDate.toJSON() : null,
      createdDate: customerdetail.createdDate != null && customerdetail.createdDate.isValid() ? customerdetail.createdDate.toJSON() : null
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.expectedEndDate = res.body.expectedEndDate != null ? moment(res.body.expectedEndDate) : null;
      res.body.createdDate = res.body.createdDate != null ? moment(res.body.createdDate) : null;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((customerdetail: ICustomerdetail) => {
        customerdetail.expectedEndDate = customerdetail.expectedEndDate != null ? moment(customerdetail.expectedEndDate) : null;
        customerdetail.createdDate = customerdetail.createdDate != null ? moment(customerdetail.createdDate) : null;
      });
    }
    return res;
  }
}
