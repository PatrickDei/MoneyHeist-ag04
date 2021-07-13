import { Component, OnInit } from '@angular/core';
import {FormArray, FormBuilder, FormControl, FormGroup, Validators} from '@angular/forms';
import {HeistService} from '../../../services/heist.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-heist-create',
  templateUrl: './heist-create.component.html',
  styleUrls: ['./heist-create.component.css']
})
export class HeistCreateComponent implements OnInit {

  form: FormGroup;

  minSkillLevel = 1;
  maxSkillLevel = 10;

  constructor(private heistService: HeistService,
              private router: Router,
              private formBuilder: FormBuilder) { }

  ngOnInit(): void {
    this.form = this.formBuilder.group({
      name: new FormControl(null, Validators.required),
      location: new FormControl(null, Validators.required),
      startTime: new FormControl(null, Validators.nullValidator),
      endTime: new FormControl(null, Validators.nullValidator),
      skills: this.formBuilder.array([this.formBuilder.group({
        name:  new FormControl(null, Validators.required),
        level:  new FormControl(null, Validators.pattern('[*]{1,10}')),
        members:  new FormControl(null, Validators.required),
      })])
    });


    /*this.form = new FormGroup({
      name: new FormControl(null, Validators.required),
      location: new FormControl(null, Validators.required),
      start: new FormControl(null, Validators.nullValidator),
      end: new FormControl(null, Validators.nullValidator)
    });*/
  }

  get skills(){
    return this.form.get('skills') as FormArray;
  }

  addSkill() {
    this.skills.push(this.formBuilder.group({
      name:  new FormControl(null, Validators.required),
      level:  new FormControl(null, [Validators.min(this.minSkillLevel), Validators.max(this.maxSkillLevel)]),
      members:  new FormControl(null, Validators.required),
    }));
  }

  deleteSkill(index) {
    if (this.skills.length !== 1)
      this.skills.removeAt(index);
  }

  create(){
    const heist = this.form.getRawValue();

    this.heistService.creteHeist(heist)
      .subscribe(
        () => this.router.navigate(['/heists'])
      );
  }

}
