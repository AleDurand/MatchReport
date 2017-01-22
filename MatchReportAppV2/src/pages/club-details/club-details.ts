import { Component } from '@angular/core';
import { NavController, NavParams } from 'ionic-angular';

import { Club } from '../../models/club.model';

@Component({
  selector: 'page-club-details',
  templateUrl: 'club-details.html'
})
export class ClubDetailsPage {

	public club: Club;

  constructor(public navCtrl: NavController, public navParams: NavParams) {
  	this.club = this.navParams.get('club');
  }

  ionViewDidLoad() {
  	
  }

}
