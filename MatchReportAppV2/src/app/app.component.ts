import { Component, ViewChild } from '@angular/core';
import { Events, LoadingController, MenuController, Nav, Platform } from 'ionic-angular';
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
    public events: Events, private loadingCtrl: LoadingController, public menu: MenuController, 
    public platform: Platform, public userService: UserService
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
    var loader = this.loadingCtrl.create();
    loader.present();
    if (page.logsOut === true) {
      this.userService.logout().then((result) => {
        this.nav.setRoot(page.component).then(() => loader.dismissAll());
      }).catch((error) => loader.dismissAll());
    } else {
      this.nav.setRoot(page.component).then(() => loader.dismissAll());
    }
  }

  listenToLoginEvents() {
    this.events.subscribe('user:login', () => {
      this.userService.getUser().then((user) => { this.user = user; }); 
      this.menu.enable(true);
      var loader = this.loadingCtrl.create();
      loader.present();
      this.nav.setRoot(ClubsPage).then(() => loader.dismissAll());
    });
    this.events.subscribe('user:logout', () => { 
      this.menu.enable(false); 
      var loader = this.loadingCtrl.create();
      loader.present();
      this.nav.setRoot(TutorialPage).then(() => loader.dismissAll());
    });      
  }

  isActive(page: Page) {
    if (this.nav.getActive() && this.nav.getActive().component === page.component) {
      return 'primary';
    }
    return;
  }

}
