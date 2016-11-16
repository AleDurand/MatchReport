import { Injectable } from '@angular/core';
import { ToastController } from 'ionic-angular';

@Injectable()
export class ToastService {

  constructor(private toast : ToastController){

  }

  default(message){
    let toast = this.toast.create({
      message: message,
      duration: 3000,
      showCloseButton: true
    });
    toast.present();
  }

  success(message){
    let toast = this.toast.create({
      message: message,
      duration: 3000,
      cssClass: 'toast-success',
      showCloseButton: true
    });
    toast.present();   
  }

  warning(message){
    let toast = this.toast.create({
      message: message,
      duration: 3000,
      cssClass: 'toast-warning',
      showCloseButton: true
    });
    toast.present();
  }

  error(message){
    let toast = this.toast.create({
      message: message,
      duration: 3000,
      cssClass: 'toast-error',
      showCloseButton: true
    });
    toast.present();
  }
}