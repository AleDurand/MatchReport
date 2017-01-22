import { Component } from '@angular/core';
import { NavController } from 'ionic-angular';

import { ClubDetailsPage } from '../club-details/club-details';
import { ClubService } from  '../../services/club.service'; 

import { Club } from '../../models/club.model';

@Component({
  selector: 'page-clubs',
  templateUrl: 'clubs.html',
  providers: [ ClubService ]
})
export class ClubsPage {

	public clubs : Array<Club>;

  constructor(public navCtrl: NavController, private clubService: ClubService) {

  }

  ionViewWillEnter() {
  	this.getAll();
  }

  getAll() {
  	this.clubService.getAll().subscribe((clubs) => {
  		this.clubs = clubs;
  	})
  }

  open(index: number) {
  	this.navCtrl.push(ClubDetailsPage, { 'club': this.clubs[index] })
  }
  
}
