import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {HeistModule} from './modules/heist/heist.module';
import {LoginComponent} from './modules/login/login.component';

// lazy load the member module
const routes: Routes = [
  {path: 'heists', loadChildren: () => HeistModule},
  {path: 'members', loadChildren: () => import('./modules/member/member.module').then(m => m.MemberModule)},
  {path: 'login', component: LoginComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
