import { async, TestBed } from '@angular/core/testing';
import { ApiModule } from '@web/api';
import { HttpClientModule } from '@angular/common/http';
import { EmployeeRequestDto, EmployeeService, Pageable, Sort } from "..";


describe('Employee Api', () => {
  let serviceApi: EmployeeService;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      imports: [
        ApiModule.forRoot(),
        HttpClientModule]
    }).compileComponents();
    serviceApi = TestBed.inject(EmployeeService);
  }));

  it('should service created', async () => {
    expect(serviceApi).toBeDefined();
  });

  describe("GET", () => {
    it('should getAll success', async () => {
      const pageAble: Pageable = {};
      const sort: Sort = {};
      const pageEmployeeDto = await serviceApi
        .getAll(pageAble, sort)
        .toPromise();

      expect(pageEmployeeDto).toBeDefined();
      expect(pageEmployeeDto.totalElements).toEqual(30);
      expect(pageEmployeeDto.content.length).toEqual(30);
      expect(pageEmployeeDto.content[0].id).toBeDefined();
      expect(pageEmployeeDto.content[0].department.id).toBeDefined();
    });

    it('should getOne success', async () => {
      const employeeDto = await serviceApi
        .getById("1")
        .toPromise();

      expect(employeeDto).toBeDefined();
      expect(employeeDto.id).toBeDefined();
      expect(employeeDto.department.id).toBeDefined();
    });

    it('should getOne zero id throw Not Found', async () => {
      const getOnePromise = serviceApi
        .getById("0")
        .toPromise();
      await expect(getOnePromise).rejects.toHaveProperty('status', 404);
    });
  });

  describe("POST/PUT", () => {

    it('should createEmployee success', async () => {
      const employeeRequestDto: EmployeeRequestDto = {
        firstName: "John",
        lastName: "Doe",
        departmentId: "1",
        mobileNumber: "+0831213123",
        email: "john@doe.com",
      };
      const newEmployeeDto = await serviceApi
        .createEmployee(employeeRequestDto)
        .toPromise();

      expect(newEmployeeDto).toBeDefined();
      expect(newEmployeeDto.id).toBeDefined();
    });

    it('should createEmployee fail with wrong param formats', async () => {
      const employeeRequestDto: EmployeeRequestDto = {
        firstName: "John",
        lastName: "Doe",
        departmentId: "1",
        mobileNumber: "+0831213123",
        email: "wrong_email_format"
      };
      const createEmployeePromise = serviceApi
        .createEmployee(employeeRequestDto)
        .toPromise();

      await expect(createEmployeePromise).rejects.toHaveProperty("status", 400);
    });

    it('should updateEmployee success', async () => {
      const employeeRequestDto: EmployeeRequestDto = {
        firstName: "John",
        lastName: "Doe",
        departmentId: "1",
        mobileNumber: "+0831213123"
      };
      const employeeDto = await serviceApi
        .updateEmployee("1", employeeRequestDto)
        .toPromise();

      expect(employeeDto).toBeDefined();
      expect(employeeDto.id).toBeDefined();
    });

  });

  describe("DELETE", () => {

    it('should deleteEmployee success', async () => {
      const deleteEmployeePromise = serviceApi
        .deleteEmployee("1")
        .toPromise();

      await expect(deleteEmployeePromise).resolves.toBeNull();
    });

    it('should deleteEmployee fail with missing employee id', async () => {
      const deleteEmployeePromise = serviceApi
        .deleteEmployee("0")
        .toPromise();

      await expect(deleteEmployeePromise).rejects.toHaveProperty("status", 404);
    });

  });

});
