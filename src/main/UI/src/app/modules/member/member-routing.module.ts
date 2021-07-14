import {RouterModule, Routes} from '@angular/router';
import {NgModule} from '@angular/core';
import {MemberComponent} from './member/member.component';
import {MemberCreateComponent} from './member-create/member-create.component';
import {MemberDetailsComponent} from './member-details/member-details.component';
import {MemberEditComponent} from './member-edit/member-edit.component';

const routes: Routes = [
  {path: '', component: MemberComponent},
  {path: 'create', component: MemberCreateComponent},
  {path: ':id', component: MemberDetailsComponent},
  {path: ':id/edit', component: MemberEditComponent}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class MemberRoutingModule { }
