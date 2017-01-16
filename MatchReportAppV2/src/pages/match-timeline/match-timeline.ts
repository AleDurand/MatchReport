import { Component } from '@angular/core';
import { NavController, NavParams } from 'ionic-angular';
import { Event} from '../../models/event.model';
import { EventService } from  '../../services/event.service'; 


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
  		console.log(events)
  	})
  }
  
 
}
