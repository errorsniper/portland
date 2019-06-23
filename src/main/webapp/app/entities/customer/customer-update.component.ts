import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { ICustomer, Customer } from 'app/shared/model/customer.model';
import { CustomerService } from './customer.service';

@Component({
  selector: 'jhi-customer-update',
  templateUrl: './customer-update.component.html'
})
export class CustomerUpdateComponent implements OnInit {
  customer: ICustomer;
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    name: [null, [Validators.required]],
    mobileNumber: [null, [Validators.required]],
    address: [],
    email: [],
    location: [],
    createdDate: [],
    createdBy: [],
    purposeOfVisit: []
  });

  constructor(protected customerService: CustomerService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ customer }) => {
      this.updateForm(customer);
      this.customer = customer;
    });
  }

  updateForm(customer: ICustomer) {
    this.editForm.patchValue({
      id: customer.id,
      name: customer.name,
      mobileNumber: customer.mobileNumber,
      address: customer.address,
      email: customer.email,
      location: customer.location,
      createdDate: customer.createdDate != null ? customer.createdDate.format(DATE_TIME_FORMAT) : null,
      createdBy: customer.createdBy,
      purposeOfVisit: customer.purposeOfVisit
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const customer = this.createFromForm();
    if (customer.id !== undefined) {
      this.subscribeToSaveResponse(this.customerService.update(customer));
    } else {
      this.subscribeToSaveResponse(this.customerService.create(customer));
    }
  }

  private createFromForm(): ICustomer {
    const entity = {
      ...new Customer(),
      id: this.editForm.get(['id']).value,
      name: this.editForm.get(['name']).value,
      mobileNumber: this.editForm.get(['mobileNumber']).value,
      address: this.editForm.get(['address']).value,
      email: this.editForm.get(['email']).value,
      location: this.editForm.get(['location']).value,
      createdDate:
        this.editForm.get(['createdDate']).value != null ? moment(this.editForm.get(['createdDate']).value, DATE_TIME_FORMAT) : undefined,
      createdBy: this.editForm.get(['createdBy']).value,
      purposeOfVisit: this.editForm.get(['purposeOfVisit']).value
    };
    return entity;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ICustomer>>) {
    result.subscribe((res: HttpResponse<ICustomer>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
  }

  protected onSaveSuccess() {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError() {
    this.isSaving = false;
  }
}
