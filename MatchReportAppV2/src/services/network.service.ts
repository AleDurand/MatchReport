import { Injectable } from '@angular/core';
import { Network } from '@ionic-native/network'

import { ToastService } from './toast.service';

@Injectable()
export class NetworkService {

  constructor(private network: Network, private toast: ToastService) {}

  noConnection() {
    return (this.network.type === 'none');
  }

}
