import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/Observable'
import { Event} from '../models/event.model';
import { Configuration } from '../app/app.constants';

@Injectable()
export class EventService {

    private actionUrl: string;

    constructor(private configuration: Configuration) {
        this.actionUrl = configuration.ServerWithApiUrl + '/events/';
    }

    getAll() {
      return Observable.create(observer => {
            observer.next(event);
            observer.complete();
        });
    }

}

let event = [
    new Event({ id: 1, name: 'Amarilla', minute: 3,  type: 'baseball', desc: ' lo faja',team: 'Local'}),
    new Event({ id: 2, name: 'Descalificacion', minute: 15,  type: 'basketball',desc: ' lo destruye',team: 'Local'}),
    new Event({ id: 3, name: 'Descalificacion con informe', minute: 23.3,  type: 'beer',
      desc: ' lo aniquila de forma que no pueda hacer deporte nunca mas en su vida',team: 'Visita'}),
    
]
