import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders, HttpParams} from '@angular/common/http';
import {BehaviorSubject, Observable, Subject} from 'rxjs';
import {Member} from '../models/member.model';
import {catchError, tap} from 'rxjs/operators';
import {handleError} from '../error/errorHandler';
import {API_URL} from '../constants/app.constants';
import {Heist} from '../models/heist.model';
import {Skill} from '../models/skill.model';
import {Requirement} from '../models/requirement.model';

@Injectable({
  providedIn: 'root'
})
export class HeistService {

  heistUrl = API_URL + '/heist';

  constructor(private http: HttpClient) { }

  getAllHeists(): Observable<Heist[]>{
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

  createHeist(heist: Heist): Observable<any>{
    return this.http.post(this.heistUrl, heist, {observe: 'response'})
      .pipe(
        tap(res => console.log(res.headers)),
        catchError(handleError('postHeist'))
      );
  }

  getEligibleMembers(id: number): Observable<{skills: Skill[], members: Member[]}>{
    return this.http.get<Heist>(this.heistUrl + '/' + id + '/eligible_members')
      .pipe(
        tap(res => console.log(res)),
        catchError(handleError('getEligibleMembers'))
      );
  }

  applyMembersForHeist(members: string[], id: number): Observable<any>{
    return this.http.put(this.heistUrl + '/' + id + '/members', members, {observe: 'response'})
      .pipe(
        tap(res => console.log(res)),
        catchError(handleError('applyMembersForHeist'))
      );
  }

  updateSkills(skills: Requirement[], id: number): Observable<any>{
    return this.http.patch(this.heistUrl + '/' + id + '/skills', skills, {observe: 'response'})
      .pipe(
        tap(res => console.log(res)),
        catchError(handleError('updateSkills'))
      );
  }

  startHeist(id: number): Observable<any>{
    return this.http.put(this.heistUrl + '/' + id + '/start', null, {observe: 'response'})
      .pipe(
        tap(res => console.log(res)),
        catchError(handleError('startHeist'))
      );
  }

  getHeistMembers(id: number): Observable<Member[]>{
    return this.http.get<Heist>(this.heistUrl + '/' + id + '/members')
      .pipe(
        tap(res => console.log(res)),
        catchError(handleError('getHeistMembers'))
      );
  }

  getHeistOutcome(id: number): Observable<{outcome: string}>{
    return this.http.get<Heist>(this.heistUrl + '/' + id + '/outcome')
      .pipe(
        tap(res => console.log(res)),
        catchError(handleError('getHeistOutcome'))
      );
  }
}
