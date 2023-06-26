import { TestBed, inject } from '@angular/core/testing';

import { GsService } from './gs.service';

describe('GsService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [GsService]
    });
  });

  it('should ...', inject([GsService], (service: GsService) => {
    expect(service).toBeTruthy();
  }));
});
