import { Component } from '@angular/core';
import { NavController } from 'ionic-angular';

import { ClubDetailsPage } from '../club-details/club-details';
import { ClubService } from  '../../services/club.service';
import { ToastService } from '../../services/toast.service';

import { Club } from '../../models/club.model';

@Component({
  selector: 'page-clubs',
  templateUrl: 'clubs.html',
  providers: [ ClubService ]
})
export class ClubsPage {

	public clubs : Array<Club>;
  public loading: boolean = true;

  constructor(private clubService: ClubService, public navCtrl: NavController, private toast: ToastService) {

  }

  ionViewDidEnter() {
    this.clubService.getAll().subscribe(
      (data) => { this.clubs = data; this.loading = false; },
      (error) => { this.loading = false; this.toast.error(error.message); }
    );
  }

  open(index: number) {
  	this.navCtrl.push(ClubDetailsPage, { 'club': this.clubs[index] })
  }

}
