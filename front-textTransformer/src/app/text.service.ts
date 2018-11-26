import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { catchError, map, tap } from 'rxjs/operators';

const httpOptions = {
  headers: new HttpHeaders({
    'Access-Control-Allow-Origin': '*'
  })
}

@Injectable({
  providedIn: 'root'
})
export class TextService {

  constructor( private http: HttpClient//,
    //private textService: TextService
    ) { }

  getOutputText(): Observable<string> {
    //return of('przykladowy output');
    return this.http.get<string>('http://localhost:8080/TextTransformer/transform?text=abc&function=upper', httpOptions);
      //.pipe(catchError(this.handleError('getOutputText', []))
    //);
  }

}
