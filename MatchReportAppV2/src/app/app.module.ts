import { NgModule, ErrorHandler } from '@angular/core';
import { IonicApp, IonicModule, IonicErrorHandler } from 'ionic-angular';
import { Configuration } from './app.constants';

import { MyApp } from './app.component';
import { ToastService } from '../services/toast.service';
import { ClubsPage } from '../pages/clubs/clubs';
import { ClubDetailsPage } from '../pages/club-details/club-details';
import { MatchTimelinePage } from  '../pages/match-timeline/match-timeline';

@NgModule({
  declarations: [
    MyApp,
    ClubsPage,
    ClubDetailsPage,
    MatchTimelinePage
  ],
  imports: [
    IonicModule.forRoot(MyApp)
  ],
  bootstrap: [IonicApp],
  entryComponents: [
    MyApp,
    ClubsPage,
    ClubDetailsPage,
    MatchTimelinePage
  ],
  providers: [{provide: ErrorHandler, useClass: IonicErrorHandler}, ToastService, Configuration]
})
export class AppModule {}
