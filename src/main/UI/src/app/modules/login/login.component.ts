import { Component, OnInit } from '@angular/core';
import {Credentials} from '../../models/credentials.model';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {LoginService} from '../../services/login.service';
import {UserService} from '../../services/user.service';
import {Router} from '@angular/router';
import {User} from '../../models/user.model';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  userCredentials: Credentials;

  authenticating: boolean;
  failedLogin: boolean;

  form: FormGroup;

  constructor(
    private loginService: LoginService,
    private userService: UserService,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.userCredentials = new Credentials();
    this.form = new FormGroup({
      username: new FormControl(null, Validators.required),
      password: new FormControl(null, Validators.required)
    });
  }

  login(){
    if(this.form.valid) {
      this.authenticating = true;
      this.failedLogin = false;

      this.userCredentials.username = this.form.get('username').value;
      this.userCredentials.password = this.form.get('password').value;

      this.loginService.authenticate(this.userCredentials).subscribe(
        (user: User) => this.onLoginSuccess(user),
        () => this.failedLogin = true
      );
    }
  }

  onLoginSuccess(user: User): void{
    this.authenticating = false;
    this.userService.currentUser = user;
    this.router.navigate(['/']);
  }
}

