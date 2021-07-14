import { Pipe, PipeTransform } from '@angular/core';
import {Observable} from 'rxjs';

@Pipe({
  name: 'countdown'
})
export class CountdownPipe implements PipeTransform {

  transform(value: Date): string {
    if(!value)
      return value.toString();

    const date = new Date(value);

    const counter = Math.floor((date.getTime() - new Date().getTime()) / 1000);

    return this.dhms(counter);
  }

  dhms(t) {
    var days, hours, minutes, seconds;
    days = Math.floor(t / 86400);
    t -= days * 86400;
    hours = Math.floor(t / 3600) % 24;
    t -= hours * 3600;
    minutes = Math.floor(t / 60) % 60;
    t -= minutes * 60;
    seconds = t % 60;

    return [
      days + 'd',
      hours + 'h',
      minutes + 'm',
      seconds + 's'
    ].join(' ');
  }
}
