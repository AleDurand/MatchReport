import { Component } from '@angular/core';
import { Loading, LoadingController, MenuController, NavController } from 'ionic-angular';

import { ClubsPage } from '../clubs/clubs';
import { UserService } from '../../services/user.service';

@Component({
  selector: 'page-tutorial',
  templateUrl: 'tutorial.html'
})

export class TutorialPage {

  public loader: Loading;

  constructor(
    public loadingCtrl: LoadingController, public menu: MenuController, public navCtrl: NavController, 
    public userService: UserService
  ) {
    this.loader = this.loadingCtrl.create({ content: 'Loading...' });
  }

  ionViewDidEnter() {
    this.menu.enable(false);
  }

  ionViewDidLeave() {
    this.menu.enable(true);
  }

  loginWithFacebook() {
    this.userService.login().then((result) => {
      this.loader.present();
      this.navCtrl.setRoot(ClubsPage);
      setTimeout(() => this.loader.dismiss(), 2000);
    }).catch((error) => {})
  }

}
