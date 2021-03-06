import { Component } from '@angular/core';
import { NavController } from 'ionic-angular';

import { MatchTimelinePage } from '../match-timeline/match-timeline';
import { MatchService } from '../../services/match.service';
import { ToastService } from '../../services/toast.service';

import { Match } from '../../models/match.model';

@Component({
  selector: 'page-matches',
  templateUrl: 'matches.html',
  providers: [ MatchService ]
})
export class MatchesPage {

	public matches: Array<Match>;
  public loading: boolean = true;

  constructor(private matchService: MatchService, public navCtrl: NavController, private toast: ToastService) {
  }

  ionViewDidEnter() {
  	this.matchService.getAll().subscribe(
  		(data) => { this.matches = data; this.loading = false; },
  		(error) => { this.loading = false; this.toast.error(error.message); }
		);
  }

  openMatch(match: Match) {
  	this.navCtrl.push(MatchTimelinePage, { index: match.id });
  }

}
