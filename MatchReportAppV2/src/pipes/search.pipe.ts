import { Pipe, PipeTransform } from '@angular/core';

@Pipe({ name: 'search', pure: false })
export class SearchPipe implements PipeTransform {

  transform(items: any[], field : string, value : string): any[] {  
    if (!items) return [];   
    return items.filter(item => item[field].toLowerCase().indexOf(value.toLowerCase()) > -1);
  }
}