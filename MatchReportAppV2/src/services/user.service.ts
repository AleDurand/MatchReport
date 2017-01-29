import { Injectable } from '@angular/core';
import { Events } from 'ionic-angular';
import { Storage } from '@ionic/storage';


@Injectable()
export class UserService {

  constructor(public events: Events, public storage: Storage) {

  }

  login(username: string) {
    this.storage.set('logged-in', true);
    this.storage.set('username', username);
    this.events.publish('user:login');
  };

  signup(username: string) {
    this.storage.set('logged-in', true);
    this.storage.set('username', username);
    this.events.publish('user:signup');
  };

  logout() {
    this.storage.remove('logged-in');
    this.storage.remove('username');
    this.events.publish('user:logout');
  };

  getUsername() {
    return this.storage.get('username').then((value) => {
      return value;
    });
  };

  hasLoggedIn() {
    return this.storage.get('logged-in').then((value) => {
      return value === true;
    });
  };

}
