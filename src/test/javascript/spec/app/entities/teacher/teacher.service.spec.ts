import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { TeacherService } from 'app/entities/teacher/teacher.service';
import { ITeacher, Teacher } from 'app/shared/model/teacher.model';
import { BloodGroup } from 'app/shared/model/enumerations/blood-group.model';
import { Gender } from 'app/shared/model/enumerations/gender.model';

describe('Service Tests', () => {
  describe('Teacher Service', () => {
    let injector: TestBed;
    let service: TeacherService;
    let httpMock: HttpTestingController;
    let elemDefault: ITeacher;
    let expectedResult: ITeacher | ITeacher[] | boolean | null;
    let currentDate: moment.Moment;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(TeacherService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new Teacher(
        0,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        currentDate,
        BloodGroup.OP,
        Gender.MALE,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA'
      );
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign(
          {
            dateOfBirth: currentDate.format(DATE_FORMAT),
          },
          elemDefault
        );

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a Teacher', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
            dateOfBirth: currentDate.format(DATE_FORMAT),
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            dateOfBirth: currentDate,
          },
          returnedFromService
        );

        service.create(new Teacher()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a Teacher', () => {
        const returnedFromService = Object.assign(
          {
            designation: 'BBBBBB',
            speciality: 'BBBBBB',
            lastName: 'BBBBBB',
            firstName: 'BBBBBB',
            arabicLastName: 'BBBBBB',
            arabicFirstName: 'BBBBBB',
            dateOfBirth: currentDate.format(DATE_FORMAT),
            bloodGroup: 'BBBBBB',
            gender: 'BBBBBB',
            address: 'BBBBBB',
            email: 'BBBBBB',
            picture: 'BBBBBB',
            password: 'BBBBBB',
            nationality: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            dateOfBirth: currentDate,
          },
          returnedFromService
        );

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of Teacher', () => {
        const returnedFromService = Object.assign(
          {
            designation: 'BBBBBB',
            speciality: 'BBBBBB',
            lastName: 'BBBBBB',
            firstName: 'BBBBBB',
            arabicLastName: 'BBBBBB',
            arabicFirstName: 'BBBBBB',
            dateOfBirth: currentDate.format(DATE_FORMAT),
            bloodGroup: 'BBBBBB',
            gender: 'BBBBBB',
            address: 'BBBBBB',
            email: 'BBBBBB',
            picture: 'BBBBBB',
            password: 'BBBBBB',
            nationality: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            dateOfBirth: currentDate,
          },
          returnedFromService
        );

        service.query().subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a Teacher', () => {
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
