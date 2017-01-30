import { Component } from '@angular/core';
import { NavController } from 'ionic-angular';
import { NgForm } from '@angular/forms';

import { ClubsPage } from '../clubs/clubs';

import { UserService } from '../../services/user.service';


@Component({
  selector: 'page-login',
  templateUrl: 'login.html'
})
export class LoginPage {
  login: { username?: string, password?: string } = {};
  submitted = false;

  constructor(public navCtrl: NavController, public userService: UserService) { }

  onLogin(form: NgForm) {
    this.submitted = true;

    if (form.valid) {
      this.userService.login(this.login.username);
      this.navCtrl.setRoot(ClubsPage);
    }
  }

  loginWithFacebook() {
    this.userService.loginWithFacebook();
    this.navCtrl.setRoot(ClubsPage);
  }

}
