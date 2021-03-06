import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {catchError, tap} from 'rxjs/operators';
import {API_URL} from '../constants/app.constants';
import {handleError} from '../error/errorHandler';
import {Credentials} from '../models/credentials.model';
import {UserService} from './user.service';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  private url = API_URL + '/api/authenticate';

  constructor(private http: HttpClient, private userService: UserService) { }

  authenticate(userCredentials: Credentials): Observable<any> {
    return this.http.post<any>(this.url, userCredentials)
      .pipe(
        tap( res => console.log(res)),
        catchError(handleError('authenticate'))
      );
  }

  logout(): void{
    // tell server you're logging out, but for this simple auth just remove credentials from user service
    this.userService.currentUser = null;
    localStorage.removeItem('isOrganiser');
  }
}
