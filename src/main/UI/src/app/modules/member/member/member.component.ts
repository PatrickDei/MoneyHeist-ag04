import { Component, OnInit } from '@angular/core';
import {MemberService} from '../../../services/member.service';
import {Member} from '../../../models/member.model';
import {UserService} from '../../../services/user.service';

@Component({
  selector: 'app-member',
  templateUrl: './member.component.html',
  styleUrls: ['./member.component.css']
})
export class MemberComponent implements OnInit {

  members: Member[];

  constructor(private memberService: MemberService,
              private userService: UserService) { }

  ngOnInit(): void {
    this.memberService.getAllMembers()
      .subscribe(
        res => this.members = res
      );
  }

  isOrganiser(): boolean{
    return this.userService.isRole('ROLE_ORGANISER');
  }
}
