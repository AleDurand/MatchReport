import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { Timeline } from './timeline/timeline/timeline';
import { TimelineBlock } from './timeline/timeline-block/timeline-block';
import { TimelineContent } from './timeline/timeline-content/timeline-content';
import { TimelineImg } from './timeline/timeline-img/timeline-img';

@NgModule({
  imports: [ BrowserModule ],
  declarations: [ Timeline, TimelineBlock, TimelineContent, TimelineImg ],
  exports: [ Timeline, TimelineBlock, TimelineContent, TimelineImg ]
})
export class ComponentsModule { }
