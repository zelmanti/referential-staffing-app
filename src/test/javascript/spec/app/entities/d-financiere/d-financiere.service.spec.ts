import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { DFinanciereService } from 'app/entities/d-financiere/d-financiere.service';
import { IDFinanciere, DFinanciere } from 'app/shared/model/d-financiere.model';

describe('Service Tests', () => {
  describe('DFinanciere Service', () => {
    let injector: TestBed;
    let service: DFinanciereService;
    let httpMock: HttpTestingController;
    let elemDefault: IDFinanciere;
    let expectedResult: IDFinanciere | IDFinanciere[] | boolean | null;
    let currentDate: moment.Moment;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(DFinanciereService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new DFinanciere(
        0,
        'AAAAAAA',
        'AAAAAAA',
        0,
        0,
        0,
        0,
        0,
        'AAAAAAA',
        currentDate,
        currentDate,
        currentDate,
        'AAAAAAA',
        0,
        0
      );
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign(
          {
            joursTravailee: currentDate.format(DATE_FORMAT),
            moisTravailee: currentDate.format(DATE_FORMAT),
            anneesTravailee: currentDate.format(DATE_FORMAT),
          },
          elemDefault
        );

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a DFinanciere', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
            joursTravailee: currentDate.format(DATE_FORMAT),
            moisTravailee: currentDate.format(DATE_FORMAT),
            anneesTravailee: currentDate.format(DATE_FORMAT),
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            joursTravailee: currentDate,
            moisTravailee: currentDate,
            anneesTravailee: currentDate,
          },
          returnedFromService
        );

        service.create(new DFinanciere()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a DFinanciere', () => {
        const returnedFromService = Object.assign(
          {
            startDate: 'BBBBBB',
            endDate: 'BBBBBB',
            tjm: 1,
            rfa: 1,
            fraisMentuels: 1,
            fraisJournaliers: 1,
            margeCalculee: 1,
            indicateurTRevenue: 'BBBBBB',
            joursTravailee: currentDate.format(DATE_FORMAT),
            moisTravailee: currentDate.format(DATE_FORMAT),
            anneesTravailee: currentDate.format(DATE_FORMAT),
            scr: 'BBBBBB',
            chiffreAffaireCalculee: 1,
            coutsCalculee: 1,
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            joursTravailee: currentDate,
            moisTravailee: currentDate,
            anneesTravailee: currentDate,
          },
          returnedFromService
        );

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of DFinanciere', () => {
        const returnedFromService = Object.assign(
          {
            startDate: 'BBBBBB',
            endDate: 'BBBBBB',
            tjm: 1,
            rfa: 1,
            fraisMentuels: 1,
            fraisJournaliers: 1,
            margeCalculee: 1,
            indicateurTRevenue: 'BBBBBB',
            joursTravailee: currentDate.format(DATE_FORMAT),
            moisTravailee: currentDate.format(DATE_FORMAT),
            anneesTravailee: currentDate.format(DATE_FORMAT),
            scr: 'BBBBBB',
            chiffreAffaireCalculee: 1,
            coutsCalculee: 1,
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            joursTravailee: currentDate,
            moisTravailee: currentDate,
            anneesTravailee: currentDate,
          },
          returnedFromService
        );

        service.query().subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a DFinanciere', () => {
        service.delete(123).subscribe(resp => (expectedResult = resp.ok));

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
