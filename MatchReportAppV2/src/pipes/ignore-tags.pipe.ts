import { Pipe, PipeTransform } from '@angular/core';
import { DomSanitizer } from '@angular/platform-browser';

@Pipe({ name: 'ignoreTags', pure: true })
export class IgnoreTags implements PipeTransform {

  constructor(private sanitizer: DomSanitizer) {

  }

  transform(text) {
    return text? String(text).replace(/<[^>]+>/gm, '') : '';
  }

}
