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
import { Pageable } from './pageable';
import { EmployeeDto } from './employeeDto';
import { Sort } from './sort';


export interface PageEmployeeDto { 
    totalPages?: number;
    totalElements?: number;
    sort?: Sort;
    size?: number;
    content?: Array<EmployeeDto>;
    number?: number;
    first?: boolean;
    numberOfElements?: number;
    pageable?: Pageable;
    last?: boolean;
    empty?: boolean;
}
