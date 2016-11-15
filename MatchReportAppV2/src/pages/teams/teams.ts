import { Component } from '@angular/core';

import { NavController } from 'ionic-angular';

import {TeamService} from '../../services/teams';

@Component({
  selector: 'page-teams',
  templateUrl: 'teams.html',
  providers: [TeamService]
})
export class TeamsPage {

    public teams;

  constructor(public navCtrl: NavController, private teamService: TeamService) {
      this.teams = this.getTeams();
  }

  getTeams() {
        this.teamService.getTeams().subscribe(
            data => {
                this.teams = data.json().content;
            },
            err => console.error(err),
            () => console.log('getRepos completed')
        );
    }

}
