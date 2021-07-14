import {Component, OnInit} from '@angular/core';
import {FormArray, FormBuilder, FormControl, FormGroup, Validators} from '@angular/forms';
import {Router} from '@angular/router';
import {MemberService} from '../../../services/member.service';

@Component({
  selector: 'app-member-create',
  templateUrl: './member-create.component.html',
  styleUrls: ['./member-create.component.css']
})
export class MemberCreateComponent implements OnInit {

  form: FormGroup;

  constructor(private memberService: MemberService,
              private router: Router,
              private formBuilder: FormBuilder) { }

  ngOnInit(): void {
    this.form = this.formBuilder.group({
      name: new FormControl(null, Validators.required),
      sex: new FormControl(null, Validators.pattern("F|M")),
      email: new FormControl(null, Validators.email),
      skills: this.formBuilder.array([this.formBuilder.group({
        name:  new FormControl(null, Validators.required),
        level:  new FormControl(null, Validators.pattern('[*]{1,10}'))
      })]),
      mainSkill: new FormControl(null),
      status: new FormControl('AVAILABLE', Validators.required)
    });
  }

  get skills(){
    return this.form.get('skills') as FormArray;
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

  onCreate(){
    const member = this.form.getRawValue();

    this.memberService.createMember(member)
      .subscribe(
        (res) => this.router.navigate([res.headers.get('Location')])
      );
  }
}
