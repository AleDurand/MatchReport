import {Injectable} from '@angular/core';
import {Http} from '@angular/http';
import { Configuration } from '../app/app.constants';

@Injectable()
export class TeamService {

    private actionUrl: string;

    constructor(private http: Http, private configuration: Configuration) {
         this.actionUrl = configuration.ServerWithApiUrl + '/clubs';
    }

    getTeams() {
        let teams = this.http.get(this.actionUrl);
        return teams;
    }

}