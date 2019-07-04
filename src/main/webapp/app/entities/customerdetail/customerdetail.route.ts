import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { Customerdetail } from 'app/shared/model/customerdetail.model';
import { CustomerdetailService } from './customerdetail.service';
import { CustomerdetailComponent } from './customerdetail.component';
import { CustomerdetailDetailComponent } from './customerdetail-detail.component';
import { CustomerdetailUpdateComponent } from './customerdetail-update.component';
import { CustomerdetailDeletePopupComponent } from './customerdetail-delete-dialog.component';
import { ICustomerdetail } from 'app/shared/model/customerdetail.model';

@Injectable({ providedIn: 'root' })
export class CustomerdetailResolve implements Resolve<ICustomerdetail> {
  constructor(private service: CustomerdetailService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<ICustomerdetail> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<Customerdetail>) => response.ok),
        map((customerdetail: HttpResponse<Customerdetail>) => customerdetail.body)
      );
    }
    return of(new Customerdetail());
  }
}

export const customerdetailRoute: Routes = [
  {
    path: '',
    component: CustomerdetailComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'realEstateApp.customerdetail.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: CustomerdetailDetailComponent,
    resolve: {
      customerdetail: CustomerdetailResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'realEstateApp.customerdetail.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: CustomerdetailUpdateComponent,
    resolve: {
      customerdetail: CustomerdetailResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'realEstateApp.customerdetail.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: CustomerdetailUpdateComponent,
    resolve: {
      customerdetail: CustomerdetailResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'realEstateApp.customerdetail.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const customerdetailPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: CustomerdetailDeletePopupComponent,
    resolve: {
      customerdetail: CustomerdetailResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'realEstateApp.customerdetail.home.title'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
