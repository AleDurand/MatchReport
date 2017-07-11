import { Pipe, PipeTransform } from "@angular/core";

@Pipe({ name: "sort", pure: false })
export class SortPipe implements PipeTransform {

  transform(items: Array<any>, field: string, orderType?: string): Array<any> {
    if(items === null || items === undefined || (orderType !== 'ASC' && orderType !== 'DESC')) {
      return items;
    }

    items.sort((a: any, b: any) => {
      if (orderType === 'ASC') {
        if (a[field] < b[field]) return -1;
        if (a[field] > b[field]) return 1;
        return 0;
      } else {
        if (a[field] < b[field]) return 1;
        if (a[field] > b[field]) return -1;
        return 0;
      }
    });
    return items;
  }
}
