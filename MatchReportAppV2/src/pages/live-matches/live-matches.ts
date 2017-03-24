import { Component } from '@angular/core';
import { NavController, NavParams } from 'ionic-angular';

import { MatchTimelinePage } from '../match-timeline/match-timeline';
import { MatchService } from '../../services/match.service';

import { Match } from '../../models/match.model';

@Component({
  selector: 'page-live-matches',
  templateUrl: 'live-matches.html',
  providers: [ MatchService ]
})
export class LiveMatchesPage {

	public matches: Array<Match>;

  constructor(private matchService: MatchService, public navCtrl: NavController, public navParams: NavParams) {
  }

  ionViewWillEnter() {
  	this.matchService.getAll().subscribe(
  		(data) => { this.matches = data; },
  		(error) => { console.log(error); }
		);
  }

  openMatch(match: Match) {
  	this.navCtrl.push(MatchTimelinePage, { index: match.id });
  }

}
