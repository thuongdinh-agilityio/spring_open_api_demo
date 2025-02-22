/**
 * Open API Demo - REST APIs
 * Demonstration about Open API and Spring Restful.
 *
 * The version of the OpenAPI document: v1.0.0
 * 
 *
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */
import { DepartmentDto } from './departmentDto';


export interface EmployeeDto { 
    id: string;
    firstName: string;
    lastName: string;
    mobileNumber: string;
    email: string;
    department: DepartmentDto;
}

