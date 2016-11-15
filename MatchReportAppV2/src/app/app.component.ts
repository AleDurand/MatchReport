import { Component, ViewChild } from '@angular/core';
import { Nav, Platform } from 'ionic-angular';
import { StatusBar, Splashscreen } from 'ionic-native';

import { TabsPage } from '../pages/tabs/tabs';

export interface PageInterface {
  title: string;
  component: any;
  icon: string;
  logsOut?: boolean;
  index?: number;
}

@Component({
  templateUrl: 'app.html'
})
export class MyApp {

    // the root nav is a child of the root app component
    // @ViewChild(Nav) gets a reference to the app's root nav
    @ViewChild(Nav) nav: Nav;

    appPages: PageInterface[] = [
    { title: 'Home', component: TabsPage, icon: 'calendar' },
    { title: 'Teams', component: TabsPage, index: 1, icon: 'contacts' },
    { title: 'Contact', component: TabsPage, index: 2, icon: 'information-circle' }
    ];
    
    rootPage = TabsPage;

    constructor(platform: Platform) {
      platform.ready().then(() => {
            // Okay, so the platform is ready and our plugins are available.
            // Here you can do any higher level native things you might need.
            StatusBar.styleDefault();
            Splashscreen.hide();
          });
    }

    openPage(page: PageInterface) {
        // the nav component was found using @ViewChild(Nav)
        // reset the nav to remove previous pages and only have this page
        // we wouldn't want the back button to show in this scenario
        if (page.index) {
          this.nav.setRoot(page.component, {tabIndex: page.index});
        } else {
          this.nav.setRoot(page.component);
        }

        if (page.logsOut === true) {
          
        }
      }
    }
