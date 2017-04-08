import { Component } from '@angular/core';
import { NavController } from 'ionic-angular';

import { EventDetailsPage } from '../event-details/event-details';
import { EventService } from  '../../services/event.service';
import { ToastService } from '../../services/toast.service';

import { Event } from '../../models/event.model';

@Component({
  selector: 'page-match-timeline',
  templateUrl: 'match-timeline.html',
   providers: [ EventService ]

})
export class MatchTimelinePage {

  public events : Array<Event>;

  constructor(private eventService: EventService, public navCtrl: NavController, private toast: ToastService) {

  }

  ionViewDidEnter() {
    this.eventService.getAll().subscribe(
      (data) => { this.events = data; },
      (error) => { this.toast.error(error.message); }
    );
  }

  openEvent(event: Event) {
    this.navCtrl.push(EventDetailsPage, { index: event.id });
  }

}
