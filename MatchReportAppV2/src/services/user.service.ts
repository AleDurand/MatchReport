import { Injectable } from '@angular/core';
import { Storage } from '@ionic/storage';
import { Facebook } from '@ionic-native/facebook';
import { Events, Platform } from 'ionic-angular';

import { User } from '../models/user.model';

@Injectable()
export class UserService {

  constructor(
    public events: Events, public facebook: Facebook,
    public platform: Platform, public storage: Storage
  ) {

  }

  login() {
    let permissions = [ "public_profile", "email" ];

    return new Promise((resolve, reject) => {
      this.facebook.login(permissions).then((response) => {
        let userId = response.authResponse.userID;
        let params = new Array<string>();

        this.facebook.api("/me?fields=first_name,last_name,gender,email", params).then((data) => {
          data.picture = "https://graph.facebook.com/" + userId + "/picture?type=large";
          let user = new User({ firstname: data.first_name, lastname: data.last_name, gender: data.gender, picture: data.picture, email: data.email });
          this.storage.set('logged-in', true);
          this.storage.set('user', user);
          this.events.publish('user:login');
          resolve(true);
        }).catch((error) => { reject(error); });
      }).catch((error) => { reject(error); })
    });
  }

  logout() {
    return new Promise((resolve, reject) => {
      this.facebook.logout().then(() => {
        this.storage.remove('logged-in');
        this.storage.remove('user');
        this.events.publish('user:logout');
        resolve(true);
      }).catch((error) => { reject(error); })
    })

  };

  getUser() {
    return this.storage.get('user').then((value) => {
      return value;
    });
  }

  hasLoggedIn() {
    return this.storage.get('logged-in').then((value) => {
      return value === true;
    });
  }

}
