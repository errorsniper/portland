import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { DashboardComponent } from './dashboard.component';
import { RealEstateSharedModule } from 'app/shared';
import { RouterModule } from '@angular/router';
import { DASHBOARD_ROUTE } from './dashboard.route';

@NgModule({
  imports: [CommonModule, RealEstateSharedModule, RouterModule.forChild(DASHBOARD_ROUTE)],
  declarations: [DashboardComponent]
})
export class DashboardModule {}
