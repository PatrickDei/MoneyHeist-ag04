import { Component, OnInit } from '@angular/core';
import {Heist} from '../../../models/heist.model';
import {Member} from '../../../models/member.model';
import {FormArray, FormBuilder, FormControl, FormGroup, Validators} from '@angular/forms';
import {ActivatedRoute, Params, Router} from '@angular/router';
import {HeistService} from '../../../services/heist.service';

@Component({
  selector: 'app-heist-edit',
  templateUrl: './heist-edit.component.html',
  styleUrls: ['./heist-edit.component.css']
})
export class HeistEditComponent implements OnInit {

  heistId: number;
  heist: Heist;
  eligibleMembers: Member[];

  memberForm: FormGroup;
  skillForm: FormGroup;

  constructor(private route: ActivatedRoute,
              private heistService: HeistService,
              private formBuilder: FormBuilder,
              private router: Router) {
  }

  ngOnInit(): void{
    this.route.params.subscribe(
      (p: Params) => {
        this.heistId = p.id;
        this.heistService.getHeistById(this.heistId)
          .subscribe(
            res => this.heist = res
          );
        this.heistService.getEligibleMembers(this.heistId)
          .subscribe(
            res => this.eligibleMembers = res.members
          );
      }
    );

    this.memberForm = this.formBuilder.group({
      members: new FormArray([])
    });

    this.skillForm = this.formBuilder.group({
      skills: this.formBuilder.array([this.formBuilder.group({
        name:  new FormControl(null, Validators.required),
        level:  new FormControl(null, Validators.pattern('[*]{1,10}')),
        members:  new FormControl(null, Validators.required),
      })])
    });
  }

  onCheckChange(event) {
    const formArray: FormArray = this.memberForm.get('members') as FormArray;

    /* Selected */
    if (event.target.checked){
      // Add a new control in the arrayForm
      formArray.push(new FormControl(event.target.value));
    }

    /* unselected */
    else{
      // find the unselected element
      let i = 0;

      formArray.controls.forEach((ctrl: FormControl) => {
        if(ctrl.value == event.target.value) {
          // Remove the unselected element from the arrayForm
          formArray.removeAt(i);
          return;
        }
        i++;
      });
    }
  }

  onSubmitMembers(){
    this.heistService.applyMembersForHeist(
      this.memberForm.getRawValue(), this.heistId
    ).subscribe(
      res => this.router.navigate(['heist', this.heistId])
    );
  }

  onSubmitRequirements(){
    this.heistService.updateSkills(
      this.skillForm.getRawValue(), this.heistId
    ).subscribe(
      res => this.router.navigate(['heist', this.heistId])
    );
  }

  get skills(){
    return this.skillForm.get('skills') as FormArray;
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
}
