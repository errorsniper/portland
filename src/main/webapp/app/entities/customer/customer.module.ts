import { CUSTOM_ELEMENTS_SCHEMA, NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { AgGridModule } from 'ag-grid-angular';
import { JhiLanguageHelper } from 'app/core';
import { RealEstateSharedModule } from 'app/shared';
import { JhiLanguageService } from 'ng-jhipster';
import {
  CustomerComponent,
  CustomerDeleteDialogComponent,
  CustomerDeletePopupComponent,
  CustomerDetailComponent,
  customerPopupRoute,
  customerRoute,
  CustomerUpdateComponent
} from './';

const ENTITY_STATES = [...customerRoute, ...customerPopupRoute];

@NgModule({
  imports: [RealEstateSharedModule, RouterModule.forChild(ENTITY_STATES), AgGridModule.withComponents([])],
  declarations: [
    CustomerComponent,
    CustomerDetailComponent,
    CustomerUpdateComponent,
    CustomerDeleteDialogComponent,
    CustomerDeletePopupComponent
  ],
  entryComponents: [CustomerComponent, CustomerUpdateComponent, CustomerDeleteDialogComponent, CustomerDeletePopupComponent],
  providers: [{ provide: JhiLanguageService, useClass: JhiLanguageService }],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class RealEstateCustomerModule {
  constructor(private languageService: JhiLanguageService, private languageHelper: JhiLanguageHelper) {
    this.languageHelper.language.subscribe((languageKey: string) => {
      if (languageKey !== undefined) {
        this.languageService.changeLanguage(languageKey);
      }
    });
  }
}
