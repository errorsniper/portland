import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ICustomerdetail } from 'app/shared/model/customerdetail.model';

@Component({
  selector: 'jhi-customerdetail-detail',
  templateUrl: './customerdetail-detail.component.html'
})
export class CustomerdetailDetailComponent implements OnInit {
  customerdetail: ICustomerdetail;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ customerdetail }) => {
      this.customerdetail = customerdetail;
    });
  }

  previousState() {
    window.history.back();
  }
}
