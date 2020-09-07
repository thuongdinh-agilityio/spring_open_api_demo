import { async, TestBed } from '@angular/core/testing';
import { ApiModule } from '@web/api';
import { HttpClientModule } from '@angular/common/http';


describe('ApiModule', () => {

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      imports: [
        ApiModule.forRoot(),
        HttpClientModule]
    }).compileComponents();
  }));

  it('should module create', async () => {
    expect(ApiModule).toBeDefined();
  });

});
