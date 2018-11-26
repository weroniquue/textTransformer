import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class TextService {

  constructor() { }

  getOutputText(): string {
    return 'przykladowy output';
  }
}
