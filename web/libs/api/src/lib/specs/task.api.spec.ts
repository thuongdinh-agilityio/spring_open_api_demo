import { async, TestBed } from '@angular/core/testing';
import { ApiModule } from '@web/api';
import { HttpClientModule } from '@angular/common/http';
import { TaskService, TaskStatus } from "..";


describe('Task Api', () => {
  let serviceApi: TaskService;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      imports: [
        ApiModule.forRoot(),
        HttpClientModule]
    }).compileComponents();
    serviceApi = TestBed.inject(TaskService);
  }));

  it('should service created', () => {
    expect(serviceApi).toBeDefined();
  });

  describe("findTasks", () => {

    it('should success without params', async () => {
      const taskDtoList = await serviceApi
        .findTasks()
        .toPromise();

      expect(taskDtoList).toBeDefined();
      expect(taskDtoList.length).toEqual(30);
      expect(taskDtoList[0].id).toBeDefined();
      expect(taskDtoList[0].content).toBeDefined();
    });

    it('should success with task status', async () => {
      const taskDtoList = await serviceApi
        .findTasks(null, null, TaskStatus.FINISHED)
        .toPromise();

      expect(taskDtoList).toBeDefined();
      expect(taskDtoList.length).toEqual(30);
      expect(taskDtoList[0].id).toBeDefined();
      expect(taskDtoList[0].content).toBeDefined();
    });

    it('should fail with wrong date format', async () => {
      const findTasksPromise = serviceApi
        .findTasks("wrong-date-format")
        .toPromise();

      await expect(findTasksPromise).rejects
        .toHaveProperty("status", 400);
    });

  });

});
