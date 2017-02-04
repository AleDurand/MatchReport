import { Component } from '@angular/core';
import { NavController, NavParams, ViewController } from 'ionic-angular';

@Component({
  selector: 'page-profile-edit',
  templateUrl: 'profile-edit.html'
})
export class ProfileEditPage {

  constructor(public navCtrl: NavController, public navParams: NavParams, private viewCtrl: ViewController) {}

  dismiss() {
    this.viewCtrl.dismiss();
  }

}
