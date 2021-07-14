import { Component, OnInit } from '@angular/core';
import {UserService} from '../services/user.service';
import {LoginService} from '../services/login.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {

  constructor(private userService: UserService, private loginService: LoginService) { }

  ngOnInit(): void {
  }

  isUserLoggedIn(): boolean{
    return !!this.userService.currentUser || !!localStorage.getItem('isOrganiser');
  }

  logout(): void{
    this.loginService.logout();
  }
}
