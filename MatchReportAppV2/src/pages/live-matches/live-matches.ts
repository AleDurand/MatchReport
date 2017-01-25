import { Component } from '@angular/core';
import { Loading, LoadingController, NavController, NavParams } from 'ionic-angular';

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
	public loader: Loading;

  constructor(
  	private loadingCtrl: LoadingController,	private matchService: MatchService, public navCtrl: NavController, 
  	public navParams: NavParams
	) {
  	this.loader = this.loadingCtrl.create({ content: 'Loading...' });
  	this.loader.present();
  }

  ionViewWillEnter() {
  	this.matchService.getAll().subscribe(
  		(data) => { this.matches = data; },
  		(error) => { console.log(error); }
		);
  }

  ionViewDidEnter() {
  	this.loader.dismissAll();
  }

  openMatch(match: Match) {
  	this.navCtrl.push(MatchTimelinePage, { index: match.id });
  }

}
