import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {BehaviorSubject, Observable, Subject} from 'rxjs';
import {Member} from '../models/member.model';
import {catchError, tap} from 'rxjs/operators';
import {handleError} from '../error/errorHandler';
import {API_URL} from '../constants/app.constants';
import {Heist} from '../models/heist.model';

@Injectable({
  providedIn: 'root'
})
export class HeistService {

  heistUrl = API_URL + '/heist';

  constructor(private http: HttpClient) { }

  getAllMembers(): Observable<Heist[]>{
    return this.http.get<Heist[]>(this.heistUrl)
      .pipe(
        tap(res => console.log(res)),
        catchError(handleError('fetchAllHeists'))
      );
  }

  getHeistById(id: number): Observable<Heist>{
    return this.http.get<Heist>(this.heistUrl + '/' + id)
      .pipe(
        tap(res => console.log(res)),
        catchError(handleError('fetchHeistById'))
      );
  }

  creteHeist(heist: Heist): Observable<any>{
    return this.http.post(this.heistUrl, heist)
      .pipe(
        tap(res => console.log(res)),
        catchError(handleError('postHeist'))
      );
  }
}
