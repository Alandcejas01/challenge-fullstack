import { Component, OnInit } from '@angular/core';
import { BreakpointObserver, Breakpoints } from '@angular/cdk/layout';
import { Observable } from 'rxjs';
import { map, shareReplay } from 'rxjs/operators';
import { AccountService } from '../../services/account.service';
import { JourneyResponse } from 'src/app/core/models/journey-response.dto';
import { ErrorhandlesService } from 'src/app/core/utils/errorhandles.service';

@Component({
  selector: 'app-home-page',
  templateUrl: './home-page.component.html',
  styleUrls: ['./home-page.component.css'],
})
export class HomePageComponent implements OnInit {
  isHandset$: Observable<boolean> = this.breakpointObserver
    .observe(Breakpoints.Handset)
    .pipe(
      map(result => result.matches),
      shareReplay()
    );
  journeyResponse!: JourneyResponse;
  constructor(
    private breakpointObserver: BreakpointObserver,
    private accountService: AccountService,
    private errorHandle: ErrorhandlesService
  ) {}

  ngOnInit(): void {
    this.getJourney();
  }

  getJourney(): void {
    this.accountService.getJourney(1).subscribe({
      next: (journey: JourneyResponse) => {
        this.journeyResponse = journey;
      },
      error: err => this.errorHandle.apiError(err),
    });
  }
}
