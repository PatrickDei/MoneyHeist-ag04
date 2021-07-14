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
  }

  get skills(){
    return this.form.get('skills') as FormArray;
  }

  addSkill() {
    this.skills.push(this.formBuilder.group({
      name:  new FormControl(null, Validators.required),
      level:  new FormControl(null, Validators.pattern('[*]{1,10}')),
      members:  new FormControl(null, Validators.required),
    }));
  }

  deleteSkill(index) {
    if (this.skills.length !== 1)
      this.skills.removeAt(index);
  }

  create(){
    const heist = this.form.getRawValue();

    this.heistService.createHeist(heist)
      .subscribe(
        (res) => this.router.navigate([res.headers.get('Location')])
      );
  }

}
