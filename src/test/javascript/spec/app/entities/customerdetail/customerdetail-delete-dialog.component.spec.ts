/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { RealEstateTestModule } from '../../../test.module';
import { CustomerdetailDeleteDialogComponent } from 'app/entities/customerdetail/customerdetail-delete-dialog.component';
import { CustomerdetailService } from 'app/entities/customerdetail/customerdetail.service';

describe('Component Tests', () => {
  describe('Customerdetail Management Delete Component', () => {
    let comp: CustomerdetailDeleteDialogComponent;
    let fixture: ComponentFixture<CustomerdetailDeleteDialogComponent>;
    let service: CustomerdetailService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [RealEstateTestModule],
        declarations: [CustomerdetailDeleteDialogComponent]
      })
        .overrideTemplate(CustomerdetailDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(CustomerdetailDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(CustomerdetailService);
      mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
      mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
    });

    describe('confirmDelete', () => {
      it('Should call delete service on confirmDelete', inject(
        [],
        fakeAsync(() => {
          // GIVEN
          spyOn(service, 'delete').and.returnValue(of({}));

          // WHEN
          comp.confirmDelete(123);
          tick();

          // THEN
          expect(service.delete).toHaveBeenCalledWith(123);
          expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
          expect(mockEventManager.broadcastSpy).toHaveBeenCalled();
        })
      ));
    });
  });
});
