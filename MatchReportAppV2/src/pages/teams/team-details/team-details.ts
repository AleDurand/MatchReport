import { Component } from '@angular/core';

import { NavController, NavParams } from 'ionic-angular';

import { TeamService } from '../../../services/team.service';
import { ToastService } from '../../../services/toast.service';

@Component({
  selector: 'page-team-details',
  templateUrl: 'team-details.html',
  providers: [TeamService, ToastService]
})
export class TeamDetailsPage {

  public team;

  constructor(public navCtrl: NavController, private navParams: NavParams, private toast: ToastService, private teamService: TeamService) {    
    this.team = new Object();
  }

  ngOnInit(){
    let id = this.navParams.get('id');
    this.teamService.getById(id).subscribe(
      data => {
        this.team = data.json();
      },
      err => {
        if(err.status === 0) this.toast.error('Error al comunicarse con el server.');
        else this.toast.error(err.json().message);
      },
      () => console.log('TeamDetailsPage => ngOnInit finished.')
    );
  }

}
