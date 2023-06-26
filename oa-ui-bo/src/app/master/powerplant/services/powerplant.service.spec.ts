import { TestBed, inject } from '@angular/core/testing';

import { PowerplantService } from './powerplant.service';

describe('PowerplantService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [PowerplantService]
    });
  });

  it('should be created', inject([PowerplantService], (service: PowerplantService) => {
    expect(service).toBeTruthy();
  }));
});
