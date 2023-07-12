import { Component, OnInit } from '@angular/core';
import { AccountService } from '../../services/account.service';
import { JourneyResponse } from 'src/app/core/models/journey-response.dto';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css'],
})
export class HomeComponent implements OnInit {
  journeyResponse!: JourneyResponse;

  constructor(private accountService: AccountService) {}

  ngOnInit(): void {
    this.getJourney();
  }

  getJourney(): void {
    this.accountService.getJourney(1).subscribe({
      next: (journey: JourneyResponse) => {
        this.journeyResponse = journey;
      },
      error: err => console.log(err),
    });
  }
}
