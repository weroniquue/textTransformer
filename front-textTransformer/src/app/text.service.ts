import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { catchError, map, tap } from 'rxjs/operators';
import {TextTransformed} from './models/text-transformed';

const httpOptions = {
  headers: new HttpHeaders({
    'Access-Control-Allow-Origin': '*',
    'Content-type' : 'application/json'
  })
};


@Injectable({
  providedIn: 'root'
})
export class TextService {

  constructor( private http: HttpClient//,
    //private textService: TextService
    ) { }

  getOutputText(): Observable<TextTransformed> {
    //return of('przykladowy output');
    return this.http.get<TextTransformed>('http://localhost:8080/TextTransformer/transform?text=abc&function=upper', httpOptions);
      //.pipe(catchError(this.handleError('getOutputText', []))
    //);
  }

}
