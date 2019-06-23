import { Route } from '@angular/router';

import { DashboardComponent } from './dashboard.component';
import { UserRouteAccessService } from 'app/core';

export const DASHBOARD_ROUTE: Route[] = [
  {
    path: '',
    component: DashboardComponent,
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'realEstateApp.customer.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
