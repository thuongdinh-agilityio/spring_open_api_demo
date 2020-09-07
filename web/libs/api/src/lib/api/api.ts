export * from './applicationStatus.service';
import { ApplicationStatusService } from './applicationStatus.service';
export * from './employee.service';
import { EmployeeService } from './employee.service';
export * from './task.service';
import { TaskService } from './task.service';
export const APIS = [ApplicationStatusService, EmployeeService, TaskService];
