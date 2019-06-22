import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RealEstateSharedLibsModule, RealEstateSharedCommonModule, JhiLoginModalComponent, HasAnyAuthorityDirective } from './';

@NgModule({
  imports: [RealEstateSharedLibsModule, RealEstateSharedCommonModule],
  declarations: [JhiLoginModalComponent, HasAnyAuthorityDirective],
  entryComponents: [JhiLoginModalComponent],
  exports: [RealEstateSharedCommonModule, JhiLoginModalComponent, HasAnyAuthorityDirective],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class RealEstateSharedModule {
  static forRoot() {
    return {
      ngModule: RealEstateSharedModule
    };
  }
}
