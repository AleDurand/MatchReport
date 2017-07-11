import { Component } from '@angular/core';
import { NavController } from 'ionic-angular';

import { ToastService } from '../../services/toast.service';
import { UserService } from '../../services/user.service';

@Component({
  selector: 'page-tutorial',
  templateUrl: 'tutorial.html'
})

export class TutorialPage {

  constructor(public navCtrl: NavController, private toast: ToastService, public userService: UserService) {
    
  }

  loginWithFacebook() {
    this.userService.login()
      .then((result) => {})
      .catch((error) => { this.toast.error(error.message); })
  }

}
