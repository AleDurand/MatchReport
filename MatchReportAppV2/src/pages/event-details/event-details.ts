import { Component } from '@angular/core';
import { NavController, NavParams } from 'ionic-angular';

import { EventService } from '../../services/event.service';

import { Event } from '../../models/event.model';

@Component({
  selector: 'page-event-details',
  templateUrl: 'event-details.html',
  providers: [ EventService ]
})
export class EventDetailsPage {

	public eventId: number;
	public event: Event;

  public chartLabels: Array<string> = ['Option 1', 'Option 2', 'Option 3', 'Option 4', 'Option 5'];
  public chartData: Array<number> = [300, 500, 100, 52, 155];
  public charType: string = 'pie';
  public options: any = { responsive: true };

  constructor(private eventService: EventService, public navCtrl: NavController, public navParams: NavParams) {
  	this.eventId = this.navParams.get('index');
  }

  ionViewWillEnter() {
  	this.eventService.getById(this.eventId).subscribe(
  		(data) => { this.event = data; },
  		(error) => { console.log(error); }
		)
  }

}



