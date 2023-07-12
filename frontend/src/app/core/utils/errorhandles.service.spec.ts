import { TestBed } from '@angular/core/testing';

import { ErrorhandlesService } from './errorhandles.service';

describe('ErrorhandlesService', () => {
  let service: ErrorhandlesService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ErrorhandlesService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
