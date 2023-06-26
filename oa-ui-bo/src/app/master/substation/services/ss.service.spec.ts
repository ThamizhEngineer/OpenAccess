import { TestBed, inject } from '@angular/core/testing';

import { SsService } from './ss.service';

describe('SsService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [SsService]
    });
  });

  it('should be created', inject([SsService], (service: SsService) => {
    expect(service).toBeTruthy();
  }));
});
