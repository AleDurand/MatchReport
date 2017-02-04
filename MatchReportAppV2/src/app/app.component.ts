import { Component, ViewChild } from '@angular/core';
import { Events, MenuController, Nav, Platform } from 'ionic-angular';
import { StatusBar, Splashscreen } from 'ionic-native';

import { ClubsPage } from '../pages/clubs/clubs';
import { LiveMatchesPage } from '../pages/live-matches/live-matches';
import { MatchesPage } from '../pages/matches/matches'; 
import { SettingsPage } from  '../pages/settings/settings';
import { TutorialPage } from  '../pages/tutorial/tutorial';

import { UserService } from '../services/user.service';

import { Page } from '../models/page.model';
import { User } from '../models/user.model';

@Component({
  templateUrl: 'app.html'
})
export class MyApp {
  @ViewChild(Nav) nav: Nav;

  public pages: Array<Page>;
  public rootPage: any = ClubsPage;
  public user: User;

  constructor(
    public events: Events, public menu: MenuController, public platform: Platform, 
    public userService: UserService
  ) {
    this.initializeApp();
    this.pages = [
      new Page({ title: 'Clubs', component: ClubsPage, icon: 'people', logsOut: false }),
      new Page({ title: 'Matches', component: MatchesPage, icon: 'calendar', logsOut: false }),
      new Page({ title: 'Live matches', component: LiveMatchesPage, icon: 'pulse', logsOut: false }),
      new Page({ title: 'Settings', component: SettingsPage, icon: 'settings', logsOut: false }),
      new Page({ title: 'Logout', component: TutorialPage, icon: 'log-out', logsOut: true })
    ];

  }

  initializeApp() {
    this.userService.hasLoggedIn().then((loggedIn) => {
      if (loggedIn) {
        this.userService.getUser().then((user) => { this.user = user; });
        this.rootPage = ClubsPage;
      } else this.rootPage = TutorialPage;
        
      this.platform.ready().then(() => {
        StatusBar.styleDefault();
        setTimeout(() => { Splashscreen.hide(); }, 1000);
      });    
    })
    this.listenToLoginEvents();
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

  listenToLoginEvents() {
    this.events.subscribe('user:login', () => {
      this.userService.getUser().then((user) => { this.user = user; console.log(user);}); 
      this.menu.enable(true);
      this.nav.setRoot(ClubsPage);
    });
    this.events.subscribe('user:logout', () => { 
      this.menu.enable(false); 
      this.nav.setRoot(TutorialPage);
    });      
  }

  isActive(page: Page) {
    if (this.nav.getActive() && this.nav.getActive().component === page.component) {
      return 'primary';
    }
    return;
  }

}
