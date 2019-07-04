import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { JhiLanguageService } from 'ng-jhipster';
import { JhiLanguageHelper } from 'app/core';

import { RealEstateSharedModule } from 'app/shared';
import {
  CustomerdetailComponent,
  CustomerdetailDetailComponent,
  CustomerdetailUpdateComponent,
  CustomerdetailDeletePopupComponent,
  CustomerdetailDeleteDialogComponent,
  customerdetailRoute,
  customerdetailPopupRoute
} from './';

const ENTITY_STATES = [...customerdetailRoute, ...customerdetailPopupRoute];

@NgModule({
  imports: [RealEstateSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    CustomerdetailComponent,
    CustomerdetailDetailComponent,
    CustomerdetailUpdateComponent,
    CustomerdetailDeleteDialogComponent,
    CustomerdetailDeletePopupComponent
  ],
  entryComponents: [
    CustomerdetailComponent,
    CustomerdetailUpdateComponent,
    CustomerdetailDeleteDialogComponent,
    CustomerdetailDeletePopupComponent
  ],
  providers: [{ provide: JhiLanguageService, useClass: JhiLanguageService }],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class RealEstateCustomerdetailModule {
  constructor(private languageService: JhiLanguageService, private languageHelper: JhiLanguageHelper) {
    this.languageHelper.language.subscribe((languageKey: string) => {
      if (languageKey !== undefined) {
        this.languageService.changeLanguage(languageKey);
      }
    });
  }
}
