import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

import { Configuration } from '../app/app.constants';
import { Club } from '../models/club.model';

@Injectable()
export class ClubService {

  private actionUrl: string;

  constructor(private configuration: Configuration) {
    this.actionUrl = configuration.ServerWithApiUrl + '/clubs/';
  }

  getAll() {
    return Observable.create(observer => {
      observer.next(clubs);
      observer.complete();
    });
  }

}

let clubs = [
  new Club({ id: 1, name: 'Boca Juniors', address: 'Alem 2200', image: 'http://a.espncdn.com/i/teamlogos/soccer/500/5.png', url: 'http://www.bocajuniors.com.ar/'}),
  new Club({ id: 2, name: 'River Plate', address: 'Alem 2200', image: 'http://a.espncdn.com/i/teamlogos/soccer/500/5.png', url: 'http://www.bocajuniors.com.ar/'}),
  new Club({ id: 3, name: 'San Lorenzo', address: 'Alem 2200', image: 'http://a.espncdn.com/i/teamlogos/soccer/500/5.png', url: 'http://www.bocajuniors.com.ar/'}),
  new Club({ id: 4, name: 'Independiente', address: 'Alem 2200', image: 'http://a.espncdn.com/i/teamlogos/soccer/500/5.png', url: 'http://www.bocajuniors.com.ar/'}),
  new Club({ id: 5, name: 'Racing club', address: 'Alem 2200', image: 'http://a.espncdn.com/i/teamlogos/soccer/500/5.png', url: 'http://www.bocajuniors.com.ar/'}),
]
