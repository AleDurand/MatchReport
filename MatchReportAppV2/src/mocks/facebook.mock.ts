import { Injectable } from '@angular/core';
import { Facebook } from '@ionic-native/facebook';

@Injectable()
export class FacebookMock extends Facebook {

  login(permissions: Array<string>) {
    return new Promise((resolve, reject) => resolve({ authResponse: { userID: 1 }}));
  }

  api(requestPath: string, permissions: Array<string>) {
    return new Promise((resolve, reject) => {
      var data = { first_name: 'firstname', last_name: 'lastname', gender: 'male', email: 'mail@matchreport.com' }
      resolve(data);
    });
  }

  logout() {
    return new Promise((resolve, reject) => resolve());
  }

}
