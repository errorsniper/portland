/* tslint:disable max-line-length */
import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { of } from 'rxjs';
import { take, map } from 'rxjs/operators';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { CustomerdetailService } from 'app/entities/customerdetail/customerdetail.service';
import { ICustomerdetail, Customerdetail } from 'app/shared/model/customerdetail.model';

describe('Service Tests', () => {
  describe('Customerdetail Service', () => {
    let injector: TestBed;
    let service: CustomerdetailService;
    let httpMock: HttpTestingController;
    let elemDefault: ICustomerdetail;
    let expectedResult;
    let currentDate: moment.Moment;
    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = {};
      injector = getTestBed();
      service = injector.get(CustomerdetailService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new Customerdetail(
        0,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        currentDate,
        'AAAAAAA',
        currentDate,
        'AAAAAAA'
      );
    });

    describe('Service methods', () => {
      it('should find an element', async () => {
        const returnedFromService = Object.assign(
          {
            expectedEndDate: currentDate.format(DATE_TIME_FORMAT),
            createdDate: currentDate.format(DATE_TIME_FORMAT)
          },
          elemDefault
        );
        service
          .find(123)
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject({ body: elemDefault });
      });

      it('should create a Customerdetail', async () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
            expectedEndDate: currentDate.format(DATE_TIME_FORMAT),
            createdDate: currentDate.format(DATE_TIME_FORMAT)
          },
          elemDefault
        );
        const expected = Object.assign(
          {
            expectedEndDate: currentDate,
            createdDate: currentDate
          },
          returnedFromService
        );
        service
          .create(new Customerdetail(null))
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp));
        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject({ body: expected });
      });

      it('should update a Customerdetail', async () => {
        const returnedFromService = Object.assign(
          {
            serviceType: 'BBBBBB',
            sizeOfPlot: 'BBBBBB',
            constructionType: 'BBBBBB',
            buildingType: 'BBBBBB',
            soilType: 'BBBBBB',
            noOfRoomsRequired: 'BBBBBB',
            expectedEndDate: currentDate.format(DATE_TIME_FORMAT),
            budGet: 'BBBBBB',
            createdDate: currentDate.format(DATE_TIME_FORMAT),
            createdBy: 'BBBBBB'
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            expectedEndDate: currentDate,
            createdDate: currentDate
          },
          returnedFromService
        );
        service
          .update(expected)
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp));
        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject({ body: expected });
      });

      it('should return a list of Customerdetail', async () => {
        const returnedFromService = Object.assign(
          {
            serviceType: 'BBBBBB',
            sizeOfPlot: 'BBBBBB',
            constructionType: 'BBBBBB',
            buildingType: 'BBBBBB',
            soilType: 'BBBBBB',
            noOfRoomsRequired: 'BBBBBB',
            expectedEndDate: currentDate.format(DATE_TIME_FORMAT),
            budGet: 'BBBBBB',
            createdDate: currentDate.format(DATE_TIME_FORMAT),
            createdBy: 'BBBBBB'
          },
          elemDefault
        );
        const expected = Object.assign(
          {
            expectedEndDate: currentDate,
            createdDate: currentDate
          },
          returnedFromService
        );
        service
          .query(expected)
          .pipe(
            take(1),
            map(resp => resp.body)
          )
          .subscribe(body => (expectedResult = body));
        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a Customerdetail', async () => {
        const rxPromise = service.delete(123).subscribe(resp => (expectedResult = resp.ok));

        const req = httpMock.expectOne({ method: 'DELETE' });
        req.flush({ status: 200 });
        expect(expectedResult);
      });
    });

    afterEach(() => {
      httpMock.verify();
    });
  });
});
