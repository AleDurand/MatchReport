import { Component } from '@angular/core';

import { NavController } from 'ionic-angular';

import { TeamService } from '../../services/team.service';
import { ToastService } from '../../services/toast.service';

@Component({
  selector: 'page-teams',
  templateUrl: 'teams.html',
  providers: [TeamService, ToastService]
})
export class TeamsPage {

  public teams;

  constructor(public navCtrl: NavController, private toast: ToastService, private teamService: TeamService) {
    this.teams = this.getTeams();
  }

  getTeams() {
    this.teamService.getTeams().subscribe(
      data => {
        this.teams = data.json().content;
      },
      err => {
        if(err.status === 0) this.toast.error('Error al comunicarse con el server.');
        else this.toast.error(err);
      },
      () => console.log('getRepos completed')
    );
  }

}
