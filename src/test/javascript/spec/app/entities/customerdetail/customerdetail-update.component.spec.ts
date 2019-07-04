/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { Observable, of } from 'rxjs';

import { RealEstateTestModule } from '../../../test.module';
import { CustomerdetailUpdateComponent } from 'app/entities/customerdetail/customerdetail-update.component';
import { CustomerdetailService } from 'app/entities/customerdetail/customerdetail.service';
import { Customerdetail } from 'app/shared/model/customerdetail.model';

describe('Component Tests', () => {
  describe('Customerdetail Management Update Component', () => {
    let comp: CustomerdetailUpdateComponent;
    let fixture: ComponentFixture<CustomerdetailUpdateComponent>;
    let service: CustomerdetailService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [RealEstateTestModule],
        declarations: [CustomerdetailUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(CustomerdetailUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(CustomerdetailUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(CustomerdetailService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new Customerdetail(123);
        spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.update).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));

      it('Should call create service on save for new entity', fakeAsync(() => {
        // GIVEN
        const entity = new Customerdetail();
        spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.create).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));
    });
  });
});
