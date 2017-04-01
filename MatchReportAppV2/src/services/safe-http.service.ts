import { Injectable } from '@angular/core';
import { Http, RequestOptionsArgs } from '@angular/http';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/observable/throw'
import 'rxjs/add/operator/catch';
import 'rxjs/add/operator/timeout';

import { NetworkService } from './network.service';

import { HttpError } from '../models/http-error.model';

@Injectable()
export class SafeHttp {

  constructor(private http: Http, private networkService: NetworkService) {}

  get(url: string, options?: RequestOptionsArgs) {
    if (this.networkService.noConnection()) return Observable.throw(new Error("Por favor verifica tu conexion a internet."));
    else {
      return this.http.get(url, options)
        .timeout(5000)
        .catch(error => {
          if(error.status === 0) {
            console.log(JSON.stringify(error));
            return Observable.throw(new Error("Error al comunicarse con el servidor."));
          }
          if (error.name === "TimeoutError") return Observable.throw(new Error("Tiempo agotado. Intente nuevamente mas tarde."));
          else {
            if(error.status >= 400 && error.status < 500) {
              let toReturn = new HttpError({ status: error.json().status, message: error.json().message });
              return Observable.throw(toReturn);
            } else {
              console.log(JSON.stringify(error));
              return Observable.throw(new Error("Error interno en el servidor. Intente nuevamente mas tarde."));
            }
          }
        });
    }
  }

  put(url: string, body: string, options?: RequestOptionsArgs) {
    if (this.networkService.noConnection()) return Observable.throw(new Error("Por favor verifica tu conexion a internet."));
    else {
      return this.http.put(url, body, options)
        .timeout(5000)
        .catch(error => {
          if(error.status === 0) {
            console.log(JSON.stringify(error));
            return Observable.throw(new Error("Error al comunicarse con el servidor."));
          }
          if (error.name === "TimeoutError") return Observable.throw(new Error("Tiempo agotado. Intente nuevamente mas tarde."));
          else {
            if(error.status >= 400 && error.status < 500) {
              let toReturn = new HttpError({ status: error.json().status, message: error.json().message });
              return Observable.throw(toReturn);
            } else {
              console.log(JSON.stringify(error));
              return Observable.throw(new Error("Error interno en el servidor. Intente nuevamente mas tarde."));
            }
          }
        });
    }
  }

  post(url: string, body: string, options?: RequestOptionsArgs) {
    if (this.networkService.noConnection()) return Observable.throw(new Error("Por favor verifica tu conexion a internet."));
    else {
      return this.http.post(url, body, options)
        .timeout(5000)
        .catch(error => {
          if(error.status === 0) {
            console.log(JSON.stringify(error));
            return Observable.throw(new Error("Error al comunicarse con el servidor."));
          }
          if (error.name === "TimeoutError") return Observable.throw(new Error("Tiempo agotado. Intente nuevamente mas tarde."));
          else {
            if(error.status >= 400 && error.status < 500) {
              let toReturn = new HttpError({ status: error.json().status, message: error.json().message });
              return Observable.throw(toReturn);
            } else {
              console.log(JSON.stringify(error));
              return Observable.throw(new Error("Error interno en el servidor. Intente nuevamente mas tarde."));
            }
          }
        });
    }
  }

  delete(url: string, options?: RequestOptionsArgs) {
    if (this.networkService.noConnection()) return Observable.throw(new Error("Por favor verifica tu conexion a internet."));
    else {
      return this.http.delete(url, options)
        .timeout(5000)
        .catch(error => {
          if(error.status === 0) {
            console.log(JSON.stringify(error));
            return Observable.throw(new Error("Error al comunicarse con el servidor."));
          }
          if (error.name === "TimeoutError") return Observable.throw(new Error("Tiempo agotado. Intente nuevamente mas tarde."));
          else {
            if(error.status >= 400 && error.status < 500) {
              let toReturn = new HttpError({ status: error.json().status, message: error.json().message });
              return Observable.throw(toReturn);
            } else {
              console.log(JSON.stringify(error));
              return Observable.throw(new Error("Error interno en el servidor. Intente nuevamente mas tarde."));
            }
          }
        });
    }
  }

}
