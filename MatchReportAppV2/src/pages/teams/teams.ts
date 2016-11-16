import { Component } from '@angular/core';

import { NavController, ToastController } from 'ionic-angular';

import {TeamService} from '../../services/teams';

@Component({
  selector: 'page-teams',
  templateUrl: 'teams.html',
  providers: [TeamService]
})
export class TeamsPage {

  public teams;

  constructor(public navCtrl: NavController, public toastCtrl: ToastController, private teamService: TeamService) {
    this.teams = this.getTeams();
  }

  getTeams() {
    this.teamService.getTeams().subscribe(
      data => {
        this.teams = data.json().content;
      },
      err => {
        if(err.status === 0) this.error('Error al comunicarse con el server.');
        else this.error(err);
      },
      () => console.log('getRepos completed')
    );
  }

  error(message) {
    let toast = this.toastCtrl.create({
      message: message,
      duration: 3000,
      cssClass: 'toast-error',
      showCloseButton: true
    });
    toast.present();
  }

}
