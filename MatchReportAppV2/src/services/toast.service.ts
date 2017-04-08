import { Injectable } from '@angular/core';
import { ToastController } from 'ionic-angular';

@Injectable()
export class ToastService {

  constructor(private toast : ToastController) {

  }

  default(message){
    let toast = this.toast.create({
      message: message,
      duration: 5000,
      showCloseButton: false,
      dismissOnPageChange: true
    });
    toast.present();
  }

  success(message){
    let toast = this.toast.create({
      message: message,
      duration: 5000,
      cssClass: 'toast-success',
      showCloseButton: false,
      dismissOnPageChange: true
    });
    toast.present();
  }

  warning(message){
    let toast = this.toast.create({
      message: message,
      duration: 5000,
      cssClass: 'toast-warning',
      showCloseButton: false,
      dismissOnPageChange: true
    });
    toast.present();
  }

  error(message){
    let toast = this.toast.create({
      message: message,
      duration: 5000,
      cssClass: 'toast-error',
      showCloseButton: false,
      dismissOnPageChange: true
    });
    toast.present();
  }
}
