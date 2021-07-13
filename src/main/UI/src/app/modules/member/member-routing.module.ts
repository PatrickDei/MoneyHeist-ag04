import {RouterModule, Routes} from '@angular/router';
import {NgModule} from '@angular/core';
import {MemberComponent} from './member/member.component';

const routes: Routes = [
  {path: '', component: MemberComponent}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class MemberRoutingModule { }
