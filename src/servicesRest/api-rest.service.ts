import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { Response } from './class/response';
import{ environment } from '../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class ApiRestService {

  constructor(private http: HttpClient) { }

  // Http Options
  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json'
    })
  }

  readxls(input: any) : Observable<Response>{

    return this.http.post<Response>(environment.url + '/readXls', {"b64":input} , this.httpOptions);

  }
}
