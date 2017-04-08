import { NgModule, ErrorHandler } from '@angular/core';
import { HttpModule } from '@angular/http';
import { BrowserModule } from '@angular/platform-browser';
import { IonicStorageModule } from '@ionic/storage';
import { IonicApp, IonicModule, IonicErrorHandler } from 'ionic-angular';

import { Facebook } from '@ionic-native/facebook';
import { Network } from '@ionic-native/network';
import { SplashScreen } from '@ionic-native/splash-screen';
import { StatusBar } from '@ionic-native/status-bar';

//import { FacebookMock } from '../mocks/facebook.mock';

import '../../node_modules/chart.js/dist/Chart.bundle.min.js';

import { MyApp } from './app.component';
import { Configuration } from './app.constants';

import { ChartsModule } from 'ng2-charts';
import { ComponentsModule } from '../components/components.module';
import { PipesModule } from '../pipes/pipes.module';

import { ClubsPage } from '../pages/clubs/clubs';
import { ClubDetailsPage } from '../pages/club-details/club-details';
import { EventDetailsPage } from  '../pages/event-details/event-details';
import { LiveMatchesPage } from  '../pages/live-matches/live-matches';
import { MatchesPage } from '../pages/matches/matches';
import { MatchTimelinePage } from  '../pages/match-timeline/match-timeline';
import { SettingsPage } from  '../pages/settings/settings';
import { TutorialPage } from  '../pages/tutorial/tutorial';

import { UserService } from '../services/user.service';
import { ToastService } from '../services/toast.service';

@NgModule({
  declarations: [
    MyApp,
    ClubsPage,
    ClubDetailsPage,
    EventDetailsPage,
    LiveMatchesPage,
    MatchesPage,
    MatchTimelinePage,
    SettingsPage,
    TutorialPage
  ],
  imports: [
    BrowserModule, ChartsModule, ComponentsModule, HttpModule, PipesModule,
    IonicModule.forRoot(MyApp, {}, {
      links: [
        { component: ClubsPage, name: 'Clubs', segment: 'clubs' },
        { component: ClubDetailsPage, name: 'ClubDetails', segment: 'clubs/:id' },
        { component: EventDetailsPage, name: 'EventDetails', segment: 'event/:id' },
        { component: LiveMatchesPage, name: 'LiveMatches', segment: 'matches/live' },
        { component: MatchesPage, name: 'Matches', segment: 'matches' },
        { component: MatchTimelinePage, name: 'MatchDetails', segment: 'matches/:id' },
        { component: SettingsPage, name: 'Settings', segment: 'settings' },
        { component: TutorialPage, name: 'Tutorial', segment: 'tutorial' }
      ]}),
    IonicStorageModule.forRoot()
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
    SettingsPage,
    TutorialPage
  ],
  providers: [
    {provide: ErrorHandler, useClass: IonicErrorHandler},
    Configuration, UserService, ToastService,
    //{ provide: Facebook, useClass: FacebookMock },
    Facebook, Network, SplashScreen, StatusBar
  ]
})
export class AppModule {}
