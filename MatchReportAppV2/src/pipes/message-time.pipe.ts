import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'messageTime',
  pure: false
})
export class MessageTime implements PipeTransform {

	transform(value: string) {
		let d = new Date(value);
		let now = new Date();
		let seconds = Math.round(Math.abs((now.getTime() - d.getTime()) / 1000));
		let minutes = Math.round(Math.abs(seconds / 60));

		if (seconds <= 45) {
			return 'Hace unos segundos';
		} else if (seconds <= 90) {
			return 'Hace un minuto';
		} else if (minutes <= 5) {
			return 'Hace ' + minutes + ' minutos';
		} else if (d.getDate() === now.getDate()) {
      let options = { hour: '2-digit', minute: '2-digit', hour12: false, pattern: "{hour}:{minute}" };
      return d.toLocaleTimeString('es-AR', options);
		} else if (d.getDate() + 1 === now.getDate()) {
      return 'Ayer';
		} else {
      let options = { year: 'numeric', month: '2-digit', day: '2-digit', pattern: "{day}/{month}/{year}" };
      return d.toLocaleDateString('es-AR', options);
    }
	}

}
