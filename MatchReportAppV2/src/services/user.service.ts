import { Injectable } from '@angular/core';
import { Events } from 'ionic-angular';
import { Storage } from '@ionic/storage';
import { Facebook } from 'ionic-native';

import { User } from '../models/user.model';

@Injectable()
export class UserService {

  constructor(public events: Events, public storage: Storage) {

  }

  login(username: string) {
    this.storage.set('logged-in', true);
    this.storage.set('user', new User({ name: username, gender: 'Unknown', picture: null }));
    this.events.publish('user:login');
  };

  signup(username: string) {
    this.storage.set('logged-in', true);
    this.storage.set('user', new User({ name: username, gender: 'Unknown', picture: null }));
    this.events.publish('user:signup');
  };

  logout() {
    this.storage.remove('logged-in');
    this.storage.remove('user');
    this.events.publish('user:logout');
  };

  getUser() {
    return this.storage.get('user').then((value) => {
      return value;
    });
  };

  hasLoggedIn() {
    return this.storage.get('logged-in').then((value) => {
      return value === true;
    });
  };

   loginWithFacebook(){
    let permissions = [ "public_profile", "email" ];
   
    Facebook.login(permissions).then((response) => {
      let userId = response.authResponse.userID;
      let params = new Array<string>();

      Facebook.api("/me?fields=name,gender,email", params).then((data) => {
        data.picture = "https://graph.facebook.com/" + userId + "/picture?type=large";
        let user = new User({ name: data.name, gender: data.gender, picture: data.picture, email: data.email });
        this.storage.set('user', user);
        this.events.publish('user:signup');
      })
    });
  }

}
