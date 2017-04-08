import { NgModule } from '@angular/core';

import { SafePipe } from './safe.pipe';
import { SearchPipe } from './search.pipe';
import { SortPipe } from './sort.pipe';

@NgModule({
  declarations: [ SafePipe, SearchPipe, SortPipe ],
  exports: [ SafePipe, SearchPipe, SortPipe ]
})
export class PipesModule { }
