import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { ICustomerdetail, Customerdetail } from 'app/shared/model/customerdetail.model';
import { CustomerdetailService } from './customerdetail.service';

@Component({
  selector: 'jhi-customerdetail-update',
  templateUrl: './customerdetail-update.component.html'
})
export class CustomerdetailUpdateComponent implements OnInit {
  customerdetail: ICustomerdetail;
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    serviceType: [null, [Validators.required]],
    sizeOfPlot: [],
    constructionType: [],
    buildingType: [],
    soilType: [],
    noOfRoomsRequired: [],
    expectedEndDate: [],
    budGet: [],
    createdDate: [],
    createdBy: []
  });

  constructor(protected customerdetailService: CustomerdetailService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ customerdetail }) => {
      this.updateForm(customerdetail);
      this.customerdetail = customerdetail;
    });
  }

  updateForm(customerdetail: ICustomerdetail) {
    this.editForm.patchValue({
      id: customerdetail.id,
      serviceType: customerdetail.serviceType,
      sizeOfPlot: customerdetail.sizeOfPlot,
      constructionType: customerdetail.constructionType,
      buildingType: customerdetail.buildingType,
      soilType: customerdetail.soilType,
      noOfRoomsRequired: customerdetail.noOfRoomsRequired,
      expectedEndDate: customerdetail.expectedEndDate != null ? customerdetail.expectedEndDate.format(DATE_TIME_FORMAT) : null,
      budGet: customerdetail.budGet,
      createdDate: customerdetail.createdDate != null ? customerdetail.createdDate.format(DATE_TIME_FORMAT) : null,
      createdBy: customerdetail.createdBy
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const customerdetail = this.createFromForm();
    if (customerdetail.id !== undefined) {
      this.subscribeToSaveResponse(this.customerdetailService.update(customerdetail));
    } else {
      this.subscribeToSaveResponse(this.customerdetailService.create(customerdetail));
    }
  }

  private createFromForm(): ICustomerdetail {
    const entity = {
      ...new Customerdetail(),
      id: this.editForm.get(['id']).value,
      serviceType: this.editForm.get(['serviceType']).value,
      sizeOfPlot: this.editForm.get(['sizeOfPlot']).value,
      constructionType: this.editForm.get(['constructionType']).value,
      buildingType: this.editForm.get(['buildingType']).value,
      soilType: this.editForm.get(['soilType']).value,
      noOfRoomsRequired: this.editForm.get(['noOfRoomsRequired']).value,
      expectedEndDate:
        this.editForm.get(['expectedEndDate']).value != null
          ? moment(this.editForm.get(['expectedEndDate']).value, DATE_TIME_FORMAT)
          : undefined,
      budGet: this.editForm.get(['budGet']).value,
      createdDate:
        this.editForm.get(['createdDate']).value != null ? moment(this.editForm.get(['createdDate']).value, DATE_TIME_FORMAT) : undefined,
      createdBy: this.editForm.get(['createdBy']).value
    };
    return entity;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ICustomerdetail>>) {
    result.subscribe((res: HttpResponse<ICustomerdetail>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
  }

  protected onSaveSuccess() {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError() {
    this.isSaving = false;
  }
}
