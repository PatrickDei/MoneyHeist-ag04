import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {HeistModule} from './modules/heist/heist.module';
import {MemberModule} from './modules/member/member.module';
import {LoginComponent} from './modules/login/login.component';

const routes: Routes = [
  {path: 'heists', loadChildren: () => HeistModule},
  {path: 'members', loadChildren: () => MemberModule},
  {path: 'login', component: LoginComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
