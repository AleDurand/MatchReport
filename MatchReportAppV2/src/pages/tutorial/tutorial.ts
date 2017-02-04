import { Component } from '@angular/core';
import { NavController } from 'ionic-angular';

import { UserService } from '../../services/user.service';

@Component({
  selector: 'page-tutorial',
  templateUrl: 'tutorial.html'
})

export class TutorialPage {

  constructor(public navCtrl: NavController, public userService: UserService) {}

  loginWithFacebook() {
    this.userService.login()
      .then((result) => {})
      .catch((error) => {})
  }

}
