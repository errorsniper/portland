/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { RealEstateTestModule } from '../../../test.module';
import { CustomerdetailDetailComponent } from 'app/entities/customerdetail/customerdetail-detail.component';
import { Customerdetail } from 'app/shared/model/customerdetail.model';

describe('Component Tests', () => {
  describe('Customerdetail Management Detail Component', () => {
    let comp: CustomerdetailDetailComponent;
    let fixture: ComponentFixture<CustomerdetailDetailComponent>;
    const route = ({ data: of({ customerdetail: new Customerdetail(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [RealEstateTestModule],
        declarations: [CustomerdetailDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(CustomerdetailDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(CustomerdetailDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.customerdetail).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
