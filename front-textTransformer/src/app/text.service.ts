import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class TextService {

  constructor() { }

  getOutputText(): Observable<string> {
    return of('przykladowy output');
  }
}
