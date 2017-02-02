import { Injectable } from '@angular/core';
import { Events, Platform } from 'ionic-angular';
import { Storage } from '@ionic/storage';
import { Facebook } from 'ionic-native';

import { User } from '../models/user.model';

@Injectable()
export class UserService {

  constructor(public events: Events, public platform: Platform, public storage: Storage) {

  }

  login() {
    let permissions = [ "public_profile", "email" ];
    
    return new Promise((resolve, reject) => {
      if(this.platform.is('cordova')) {
        Facebook.login(permissions).then((response) => {
          let userId = response.authResponse.userID;
          let params = new Array<string>();

          Facebook.api("/me?fields=name,gender,email", params).then((data) => {
            data.picture = "https://graph.facebook.com/" + userId + "/picture?type=large";
            let user = new User({ name: data.name, gender: data.gender, picture: data.picture, email: data.email });
            this.storage.set('logged-in', true);
            this.storage.set('user', user);
            this.events.publish('user:logged-in');
            resolve(true);
          }).catch((error) => { 
            console.log(error);
            reject(error);
          });
        }).catch((error) => { 
          console.log(error);
          reject(error);
        })
      } else {
        reject('Cordova is not available');
      }
    });
  }

  logout() {
    return new Promise((resolve, reject) => {
      if(this.platform.is('cordova')) {
        Facebook.logout().then(() => {
          this.storage.remove('logged-in');
          this.storage.remove('user');
          this.events.publish('user:logout');
          resolve(true);
        }).catch((error) => { 
          console.log(error);
          reject(error);
        })    
      } else {
        reject('Cordova is not available');
      }      
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
