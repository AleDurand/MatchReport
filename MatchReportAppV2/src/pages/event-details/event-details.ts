import { Component } from '@angular/core';
import { FabContainer, NavController, NavParams } from 'ionic-angular';

import { EventService } from '../../services/event.service';
import { ToastService } from '../../services/toast.service';

import { Event } from '../../models/event.model';

@Component({
  selector: 'page-event-details',
  templateUrl: 'event-details.html',
  providers: [ EventService ]
})
export class EventDetailsPage {

	public eventId: number;
	public event: Event;
  public fabMenuOpen: boolean = false;

  public chartLabels: Array<string> = ['Option 1', 'Option 2', 'Option 3', 'Option 4', 'Option 5'];
  public chartData: Array<number> = [300, 500, 100, 52, 155];
  public charType: string = 'pie';
  public options: any = { responsive: true, legend: { display: false },};

  constructor(
    private eventService: EventService, public navCtrl: NavController,
    public navParams: NavParams, private toast: ToastService
  ) {
  	this.eventId = this.navParams.get('index');
  }

  ionViewDidEnter() {
    this.eventService.getById(this.eventId).subscribe(
      (data) => { this.event = data; },
      (error) => { this.toast.error(error.message); }
		);
  }

  toggle() {
    this.fabMenuOpen = !this.fabMenuOpen;
  }

  vote(fab: FabContainer) {
    fab.close();
    this.toggle();
  }

}
