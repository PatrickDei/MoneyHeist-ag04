import {Injectable} from '@angular/core';
import {User} from '../models/user.model';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  currentUser: User | null;

  constructor() {
    this.currentUser = null;
  }

  isRole(role: string): boolean{
    if (this.currentUser)
      return this.currentUser.roles.some(r => r === role);
    return localStorage.getItem('isOrganiser') === 'ROLE_ORGANISER';
  }
}
