import {Component, OnInit} from '@angular/core';
import {Member} from '../../../models/member.model';
import {FormArray, FormBuilder, FormControl, FormGroup, Validators} from '@angular/forms';
import {ActivatedRoute, Params, Router} from '@angular/router';
import {MemberService} from '../../../services/member.service';

@Component({
  selector: 'app-member-edit',
  templateUrl: './member-edit.component.html',
  styleUrls: ['./member-edit.component.css']
})
export class MemberEditComponent implements OnInit {

  memberId: number;
  member: Member;

  skillForm: FormGroup;

  constructor(private route: ActivatedRoute,
              private memberService: MemberService,
              private formBuilder: FormBuilder,
              private router: Router) {
  }

  ngOnInit(): void{
    this.route.params.subscribe(
      (p: Params) => {
        this.memberId = p.id;
        this.memberService.getMemberById(this.memberId)
          .subscribe(
            res => this.member = res);
      }
    );

    this.skillForm = this.formBuilder.group({
      skills: this.formBuilder.array([this.formBuilder.group({
        name:  new FormControl(null, Validators.required),
        level:  new FormControl(null, Validators.pattern('[*]{1,10}'))
      })])
    });
  }

  onRemoveSkill(skillName: string){
    this.memberService.deleteSkillFromMember(
      this.memberId, skillName
    ).subscribe(
      () => this.member.skills = this.member.skills.filter(skill => skill.name !== skillName)
    );
  }

  onSubmitSkills(){
    this.memberService.putMemberSkills(
      this.memberId, this.skillForm.getRawValue()
    ).subscribe(
      res => this.router.navigate(['member', this.memberId])
    );
  }

  get skills(){
    return this.skillForm.get('skills') as FormArray;
  }

  addSkill() {
    this.skills.push(this.formBuilder.group({
      name:  new FormControl(null, Validators.required),
      level:  new FormControl(null, Validators.pattern('[*]{1,10}'))
    }));
  }

  deleteSkill(index) {
    if (this.skills.length !== 1)
      this.skills.removeAt(index);
  }

}
