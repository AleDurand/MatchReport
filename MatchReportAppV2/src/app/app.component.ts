import { Component, ViewChild } from '@angular/core';
import { Nav, Platform } from 'ionic-angular';
import { Storage } from '@ionic/storage';
import { StatusBar, Splashscreen } from 'ionic-native';

import { ClubsPage } from '../pages/clubs/clubs';
import { LiveMatchesPage } from '../pages/live-matches/live-matches';
import { MatchesPage } from '../pages/matches/matches'; 
import { SettingsPage } from  '../pages/settings/settings';
import { TutorialPage } from  '../pages/tutorial/tutorial';

import { UserService } from '../services/user.service';

@Component({
  templateUrl: 'app.html'
})
export class MyApp {
  @ViewChild(Nav) nav: Nav;

  rootPage: any = ClubsPage;

  pages: Array<{title: string, component: any, icon: string, logsOut: boolean}>;

  constructor(public platform: Platform, public storage: Storage, public userService: UserService) {
    this.initializeApp();
    this.pages = [
      { title: 'Clubs', component: ClubsPage, icon: 'log-in', logsOut: false },
      { title: 'Matches', component: MatchesPage, icon: 'log-in', logsOut: false },
      { title: 'Live matches', component: LiveMatchesPage, icon: 'log-in', logsOut: false },
      { title: 'Settings', component: SettingsPage, icon: 'log-in', logsOut: false },
      { title: 'Logout', component: TutorialPage, icon: 'log-out', logsOut: true }
    ];

  }

  initializeApp() {
    this.storage.get('logged-in').then((loggedIn) => {
      if (loggedIn) this.rootPage = ClubsPage;
      else this.rootPage = TutorialPage;
        
      this.platform.ready().then(() => {
        StatusBar.styleDefault();
        setTimeout(() => { Splashscreen.hide(); }, 1000);
      });    
    })    
  }

  openPage(page) {
    if (page.logsOut === true) {
      this.userService.logout().then((result) => {
        this.nav.setRoot(page.component);
      }).catch((error) => { console.log(error); });
    } else {
      this.nav.setRoot(page.component);
    }
  }
}
