import { NgModule } from '@angular/core';
import { HttpModule } from '@angular/http';
import { IonicApp, IonicModule } from 'ionic-angular';
import { MyApp } from './app.component';
import { TeamsPage } from '../pages/teams/teams';
import { TeamDetailsPage } from '../pages/teams/team-details/team-details';
import { ContactPage } from '../pages/contact/contact';
import { HomePage } from '../pages/home/home';
import { TabsPage } from '../pages/tabs/tabs';
import { Configuration } from './app.constants';

@NgModule({
  declarations: [
    MyApp,
    TeamsPage,
    TeamDetailsPage,
    ContactPage,
    HomePage,
    TabsPage
  ],
  imports: [
    IonicModule.forRoot(MyApp, { tabsPlacement: 'bottom' }), HttpModule
  ],
  bootstrap: [IonicApp],
  entryComponents: [
    MyApp,
    TeamsPage,
    TeamDetailsPage,
    ContactPage,
    HomePage,
    TabsPage
  ],
  providers: [Configuration]
})
export class AppModule {}
