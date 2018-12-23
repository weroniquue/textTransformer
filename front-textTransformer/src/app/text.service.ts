import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import {HttpClient, HttpErrorResponse, HttpHeaders} from '@angular/common/http';
import { catchError, map, tap ,retry} from 'rxjs/operators';
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

  URLname:string = 'http://localhost:8080/TextTransformer/transform?';

  constructor(private http: HttpClient) { }

  getOutputText(path: string): Observable<any> {
    return this.http.get<TextTransformed>(this.URLname + path, httpOptions);
  }
}
