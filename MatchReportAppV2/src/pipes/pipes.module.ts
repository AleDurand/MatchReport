import { NgModule } from '@angular/core';

import { IgnoreTags } from './ignore-tags.pipe';
import { MessageTime } from './message-time.pipe';
import { SafeHtmlPipe } from './safe-html.pipe';
import { SafePipe } from './safe.pipe';
import { SearchPipe } from './search.pipe';
import { SortPipe } from './sort.pipe';

@NgModule({
  declarations: [ IgnoreTags, MessageTime, SafeHtmlPipe, SafePipe, SearchPipe, SortPipe ],
  exports: [ IgnoreTags, MessageTime, SafeHtmlPipe, SafePipe, SearchPipe, SortPipe ]
})
export class PipesModule { }
