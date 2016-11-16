import { Component } from '@angular/core';

import { NavController } from 'ionic-angular';

import { TeamDetailsPage } from './team-details/team-details';
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
    
  }

  ngOnInit(){
      this.teamService.getAll().subscribe(
      data => {
        this.teams = data.json().content;
      },
      err => {
        if(err.status === 0) this.toast.error('Error al comunicarse con el server.');
        else this.toast.error(err.json().message);
      },
      () => console.log('TeamsPage => ngOnInit finished.')
    );
  }

  getById(id) {
    this.navCtrl.push(TeamDetailsPage, { id: id });
  }    

}
