import { NgModule, ErrorHandler } from '@angular/core';
import { IonicApp, IonicModule, IonicErrorHandler } from 'ionic-angular';
import { Storage } from '@ionic/storage';
import '../../node_modules/chart.js/dist/Chart.bundle.min.js';
import { ChartsModule } from 'ng2-charts';
import { Configuration } from './app.constants';

import { MyApp } from './app.component';
import { ClubsPage } from '../pages/clubs/clubs';
import { ClubDetailsPage } from '../pages/club-details/club-details';
import { EventDetailsPage } from  '../pages/event-details/event-details';
import { LiveMatchesPage } from  '../pages/live-matches/live-matches';
import { MatchesPage } from '../pages/matches/matches';
import { MatchTimelinePage } from  '../pages/match-timeline/match-timeline';
import { ProfilePage } from '../pages/profile/profile';
import { ProfileEditPage } from '../pages/profile-edit/profile-edit';
import { SettingsPage } from  '../pages/settings/settings';
import { TutorialPage } from  '../pages/tutorial/tutorial';

import { SafePipe } from '../pipes/safe.pipe';
import { SearchPipe } from '../pipes/search.pipe';
import { SortPipe } from '../pipes/sort.pipe';

import { UserService } from '../services/user.service';
import { ToastService } from '../services/toast.service';

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
    MatchesPage,
    MatchTimelinePage,
    ProfilePage,
    ProfileEditPage,
    SafePipe,
    SearchPipe,
    SettingsPage,
    SortPipe,    
    TutorialPage,
    Timeline, TimelineBlock, TimelineContent, TimelineImg
  ],
  imports: [
    IonicModule.forRoot(MyApp), ChartsModule
  ],
  bootstrap: [IonicApp],
  entryComponents: [
    MyApp,
    ClubsPage,
    ClubDetailsPage,
    EventDetailsPage,
    LiveMatchesPage,
    MatchesPage,
    MatchTimelinePage,
    ProfilePage,
    ProfileEditPage,
    SettingsPage,
    TutorialPage
  ],
  providers: [{provide: ErrorHandler, useClass: IonicErrorHandler}, UserService, Storage, ToastService, Configuration]
})
export class AppModule {}
