import { Component } from '@angular/core';
import { ModalController, NavController, NavParams } from 'ionic-angular';

import { ProfileEditPage } from '../profile-edit/profile-edit';
import { UserService } from '../../services/user.service';

import { User } from '../../models/user.model';

@Component({
  selector: 'page-profile',
  templateUrl: 'profile.html'
})
export class ProfilePage {

  public user: User;

  constructor(
    public navCtrl: NavController, public navParams: NavParams, private modalCtrl: ModalController,
    public userService: UserService
  ) {
    this.userService.getUser().then((user) => { this.user = user; });
  }  

  edit() {
    let modal = this.modalCtrl.create(ProfileEditPage, {});
    modal.present();
  }

}
