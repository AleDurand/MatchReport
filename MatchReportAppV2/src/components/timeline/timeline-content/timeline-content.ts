import { Component, Input } from '@angular/core';

@Component({
  selector: 'timeline-content',
  templateUrl: 'timeline-content.html',
})
export class TimelineContent {

	@Input()
	public align: string;

  constructor() {
  	if(this.align !== 'right') {
  		this.align = 'left';
  	}
  }

}
