import {BrowserModule, Title} from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NavbarComponent } from './navbar/navbar.component';
import {HTTP_INTERCEPTORS, HttpClientModule} from '@angular/common/http';
import {AuthExpiredInterceptor} from './interceptors/auth-expired.inteceptor';
import {AuthInterceptor} from './interceptors/auth.interceptor';
import {LoginComponent} from './login/login.component';
import {ReactiveFormsModule} from '@angular/forms';
import { CountdownPipe } from './pipes/countdown.pipe';
import { FrontpageComponent } from './frontpage/frontpage.component';

@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    LoginComponent,
    CountdownPipe,
    FrontpageComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    ReactiveFormsModule
  ],
  exports: [
    CountdownPipe
  ],
  providers: [
    Title,
    {
      provide: HTTP_INTERCEPTORS,
      useClass: AuthInterceptor,
      multi: true
    },
    {
      provide: HTTP_INTERCEPTORS,
      useClass: AuthExpiredInterceptor,
      multi: true
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
