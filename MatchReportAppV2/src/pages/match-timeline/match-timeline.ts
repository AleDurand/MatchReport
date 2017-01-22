import { Component } from '@angular/core';
import { NavController, NavParams } from 'ionic-angular';

import { EventService } from  '../../services/event.service'; 

import { Event } from '../../models/event.model';

@Component({
  selector: 'page-match-timeline',
  templateUrl: 'match-timeline.html',
   providers: [ EventService ]

})
export class MatchTimelinePage {
	public events : Array<Event>;

  constructor(public navCtrl: NavController, private EventService: EventService) {}
  
  ionViewWillEnter() {
  	this.getAll();

  }

  getAll() {
  	this.EventService.getAll().subscribe((events) => {
  		this.events = events;
  	})
  }
  
 
}
