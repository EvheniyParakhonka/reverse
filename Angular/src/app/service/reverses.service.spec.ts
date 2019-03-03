import { TestBed } from '@angular/core/testing';

import { ReversesService } from './reverses.service';

describe('ReversesService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: ReversesService = TestBed.get(ReversesService);
    expect(service).toBeTruthy();
  });
});
