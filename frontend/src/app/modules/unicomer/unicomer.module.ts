import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { UnicomerRoutingModule } from './unicomer-routing.module';
import { LayoutModule } from '@angular/cdk/layout';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatSidenavModule } from '@angular/material/sidenav';
import { MatIconModule } from '@angular/material/icon';
import { MatListModule } from '@angular/material/list';
import { HomePageComponent } from './pages/home-page/home-page.component';
import { HomeComponent } from './components/home/home.component';
import { LastTransactionsComponent } from './components/last-transactions/last-transactions.component';

@NgModule({
  declarations: [HomePageComponent, HomeComponent, LastTransactionsComponent],
  imports: [
    CommonModule,
    UnicomerRoutingModule,
    LayoutModule,
    MatToolbarModule,
    MatSidenavModule,
    MatIconModule,
    MatListModule,
  ],
})
export class UnicomerModule {}
