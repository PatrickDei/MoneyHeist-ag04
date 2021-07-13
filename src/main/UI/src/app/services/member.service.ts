import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Member} from '../models/member.model';
import {API_URL} from '../constants/app.constants';
import {catchError, tap} from 'rxjs/operators';
import {handleError} from '../error/errorHandler';

@Injectable({
  providedIn: 'root'
})
export class MemberService {

  memberUrl = API_URL + '/member';

  constructor(private http: HttpClient) { }

  getAllMembers(): Observable<Member[]>{
    return this.http.get<Member[]>(this.memberUrl)
      .pipe(
        tap(res => console.log(res)),
        catchError(handleError('fetchAllMembers'))
      );
  }
}
