import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Member} from '../models/member.model';
import {API_URL} from '../constants/app.constants';
import {catchError, tap} from 'rxjs/operators';
import {handleError} from '../error/errorHandler';
import {Skill} from '../models/skill.model';

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

  createMember(member: Member): Observable<any>{
    return this.http.post(this.memberUrl, member, {observe: 'response'})
      .pipe(
        tap(res => console.log(res.headers)),
        catchError(handleError('createMember'))
      );
  }

  getMemberById(id: number): Observable<Member>{
    return this.http.get<Member>(this.memberUrl + '/' + id)
      .pipe(
        tap(res => console.log(res)),
        catchError(handleError('getMemberById'))
      );
  }

  putMemberSkills(id: number, skills: {skills: Skill[], mainSkill: string}): Observable<any>{
    return this.http.put(this.memberUrl + '/' + id + '/skills', skills, {observe: 'response'})
      .pipe(
        tap(res => console.log(res)),
        catchError(handleError('putMemberSkills'))
      );
  }

  deleteSkillFromMember(id: number, name: string): Observable<any>{
    return this.http.delete(this.memberUrl + '/' + id + '/skills/' + name, {observe: 'response'})
      .pipe(
        tap(res => console.log(res)),
        catchError(handleError('deleteSkillFromMember'))
      );
  }
}
