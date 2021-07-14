import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {LoginComponent} from './login/login.component';
import {FrontpageComponent} from './frontpage/frontpage.component';

// lazy load the member & heist modules
const routes: Routes = [
  {path: '', component: FrontpageComponent},
  {path: 'heist', loadChildren: () => import('./modules/heist/heist.module').then(m => m.HeistModule)},
  {path: 'member', loadChildren: () => import('./modules/member/member.module').then(m => m.MemberModule)},
  {path: 'login', component: LoginComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
