import { NgModule, LOCALE_ID } from '@angular/core';
import { DatePipe, registerLocaleData } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';
import { Title } from '@angular/platform-browser';
import locale from '@angular/common/locales/en';

import { NgbDateAdapter } from '@ng-bootstrap/ng-bootstrap';
import { NgbDateMomentAdapter } from 'app/shared/util/datepicker-adapter';
import { AccountService } from '.';

@NgModule({
  imports: [HttpClientModule],
  exports: [],
  declarations: [],
  providers: [
    AccountService,
    Title,
    {
      provide: LOCALE_ID,
      useValue: 'en'
    },
    { provide: NgbDateAdapter, useClass: NgbDateMomentAdapter },
    DatePipe
  ]
})
export class RealEstateCoreModule {
  constructor() {
    registerLocaleData(locale);
  }
}
