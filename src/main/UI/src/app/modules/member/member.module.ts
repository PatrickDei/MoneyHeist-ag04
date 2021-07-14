import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MemberComponent } from './member/member.component';
import {MemberRoutingModule} from './member-routing.module';
import { MemberCreateComponent } from './member-create/member-create.component';
import { MemberDetailsComponent } from './member-details/member-details.component';
import { MemberEditComponent } from './member-edit/member-edit.component';



@NgModule({
  declarations: [MemberComponent, MemberCreateComponent, MemberDetailsComponent, MemberEditComponent],
  imports: [
    CommonModule,
    MemberRoutingModule
  ]
})
export class MemberModule { }
