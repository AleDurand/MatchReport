import { NgModule, ErrorHandler } from '@angular/core';
import { IonicApp, IonicModule, IonicErrorHandler } from 'ionic-angular';
import { Configuration } from './app.constants';

import { MyApp } from './app.component';
import { ToastService } from '../services/toast.service';
import { ClubsPage } from '../pages/clubs/clubs';
import { ClubDetailsPage } from '../pages/club-details/club-details';

@NgModule({
  declarations: [
    MyApp,
    ClubsPage,
    ClubDetailsPage
  ],
  imports: [
    IonicModule.forRoot(MyApp)
  ],
  bootstrap: [IonicApp],
  entryComponents: [
    MyApp,
    ClubsPage,
    ClubDetailsPage
  ],
  providers: [{provide: ErrorHandler, useClass: IonicErrorHandler}, ToastService, Configuration]
})
export class AppModule {}
