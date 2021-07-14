import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Params, Router} from '@angular/router';
import {UserService} from '../../../services/user.service';
import {MemberService} from '../../../services/member.service';
import {Member} from '../../../models/member.model';

@Component({
  selector: 'app-member-details',
  templateUrl: './member-details.component.html',
  styleUrls: ['./member-details.component.css']
})
export class MemberDetailsComponent implements OnInit {

  memberId: number;
  member: Member;

  constructor(private route: ActivatedRoute,
              private memberService: MemberService,
              private userService: UserService,
              private router: Router) {
  }

  ngOnInit(): void {
    this.route.params.subscribe(
      (p: Params) => {
        this.memberId = p.id;

        this.memberService.getMemberById(this.memberId)
          .subscribe(
            res => this.member = res
          );
      }
    );
  }

  isOrganiser(): boolean{
    return this.userService.isRole('ROLE_ORGANISER');
  }

  onEdit(): void{
    this.router.navigate(['member', this.memberId, 'edit']);
  }
}
