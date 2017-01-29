import { Component, ViewChild } from '@angular/core';
import { Nav, Platform } from 'ionic-angular';
import { StatusBar, Splashscreen } from 'ionic-native';

import { ClubsPage } from '../pages/clubs/clubs';
import { LiveMatchesPage } from '../pages/live-matches/live-matches';
import { LoginPage } from '../pages/login/login';
import { MatchesPage } from '../pages/matches/matches'; 
import { SettingsPage } from  '../pages/settings/settings';

@Component({
  templateUrl: 'app.html'
})
export class MyApp {
  @ViewChild(Nav) nav: Nav;

  rootPage: any = ClubsPage;

  pages: Array<{title: string, component: any}>;

  constructor(public platform: Platform) {
    this.initializeApp();
    this.pages = [
      { title: 'Clubs', component: ClubsPage },
      { title: 'Matches', component: MatchesPage },
      { title: 'Live matches', component: LiveMatchesPage },
      { title: 'Settings', component: SettingsPage },
      { title: 'Login', component: LoginPage }
    ];

  }

  initializeApp() {
    this.platform.ready().then(() => {
      StatusBar.styleDefault();
      setTimeout(() => { Splashscreen.hide(); }, 1000);
    });
  }

  openPage(page) {
    this.nav.setRoot(page.component);
  }
}
