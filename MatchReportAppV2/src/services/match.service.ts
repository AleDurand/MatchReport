import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/Observable'

import { Configuration } from '../app/app.constants';

import { Match } from '../models/match.model';
import { MatchStatus } from  '../models/match-status.model';

@Injectable()
export class MatchService {

	private actionUrl: string;

	constructor(private configuration: Configuration) {
		this.actionUrl = configuration.ServerWithApiUrl + '/matches/';
	}

	getById(index: number) {
	  return Observable.create(observer => {
			let toReturn = null;
			matches.forEach((match) => {
				if(match.id === index){
					toReturn = match;
				}
			})
			observer.next(toReturn);
			observer.complete();
		});
	}

	getAll() {
	  return Observable.create(observer => {
			observer.next(matches);
			observer.complete();
		});
	}

}

let matches = [
	{ id: 1, date: new Date(), stadium: 'Bombonera', localTeam: 'Boca', visitorTeam: 'Riber', status: MatchStatus.PENDING },
	{ id: 2, date: new Date(), stadium: 'Monumental', localTeam: 'San Lorenzo', visitorTeam: 'Riber', status: MatchStatus.IN_PROGRESS },
	{ id: 3, date: new Date(), stadium: 'Cilindro de Avellaneda', localTeam: 'San Lorenzo', visitorTeam: 'Racing', status: MatchStatus.IN_PROGRESS },
	{ id: 4, date: new Date(), stadium: 'Casanova', localTeam: 'Olimpo', visitorTeam: 'Independiente', status: MatchStatus.FINALIZED }
]
