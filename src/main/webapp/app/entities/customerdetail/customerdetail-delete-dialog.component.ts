import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ICustomerdetail } from 'app/shared/model/customerdetail.model';
import { CustomerdetailService } from './customerdetail.service';

@Component({
  selector: 'jhi-customerdetail-delete-dialog',
  templateUrl: './customerdetail-delete-dialog.component.html'
})
export class CustomerdetailDeleteDialogComponent {
  customerdetail: ICustomerdetail;

  constructor(
    protected customerdetailService: CustomerdetailService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.customerdetailService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'customerdetailListModification',
        content: 'Deleted an customerdetail'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-customerdetail-delete-popup',
  template: ''
})
export class CustomerdetailDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ customerdetail }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(CustomerdetailDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.customerdetail = customerdetail;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/customerdetail', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/customerdetail', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          }
        );
      }, 0);
    });
  }

  ngOnDestroy() {
    this.ngbModalRef = null;
  }
}
