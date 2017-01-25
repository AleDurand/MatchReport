import { NgModule, ErrorHandler } from '@angular/core';
import { IonicApp, IonicModule, IonicErrorHandler } from 'ionic-angular';
import { Configuration } from './app.constants';

import { MyApp } from './app.component';
import { ToastService } from '../services/toast.service';
import { ClubsPage } from '../pages/clubs/clubs';
import { ClubDetailsPage } from '../pages/club-details/club-details';
import { EventDetailsPage } from  '../pages/event-details/event-details';
import { LiveMatchesPage } from  '../pages/live-matches/live-matches';
import { LoginPage } from '../pages/login/login';
import { MatchTimelinePage } from  '../pages/match-timeline/match-timeline';
import { SettingsPage } from  '../pages/settings/settings';

import { Timeline } from '../components/timeline/timeline/timeline';
import { TimelineBlock } from '../components/timeline/timeline-block/timeline-block';
import { TimelineContent } from '../components/timeline/timeline-content/timeline-content';
import { TimelineImg } from '../components/timeline/timeline-img/timeline-img';

@NgModule({
  declarations: [
    MyApp,
    ClubsPage,
    ClubDetailsPage,
    EventDetailsPage,
    LiveMatchesPage,
    LoginPage,
    MatchTimelinePage,
    SettingsPage,
    Timeline, TimelineBlock, TimelineContent, TimelineImg
  ],
  imports: [
    IonicModule.forRoot(MyApp)
  ],
  bootstrap: [IonicApp],
  entryComponents: [
    MyApp,
    ClubsPage,
    ClubDetailsPage,
    EventDetailsPage,
    LiveMatchesPage,
    LoginPage,
    MatchTimelinePage,
    SettingsPage
  ],
  providers: [{provide: ErrorHandler, useClass: IonicErrorHandler}, ToastService, Configuration]
})
export class AppModule {}
