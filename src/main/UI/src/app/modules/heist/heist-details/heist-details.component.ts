import {Component, OnInit} from '@angular/core';
import {Heist} from '../../../models/heist.model';
import {ActivatedRoute, Params, Router} from '@angular/router';
import {HeistService} from '../../../services/heist.service';
import {UserService} from '../../../services/user.service';

@Component({
  selector: 'app-heist-details',
  templateUrl: './heist-details.component.html',
  styleUrls: ['./heist-details.component.css']
})
export class HeistDetailsComponent implements OnInit {

  heistId: number;
  heist: Heist;

  constructor(private route: ActivatedRoute,
              private heistService: HeistService,
              private userService: UserService,
              private router: Router) {
  }

  ngOnInit(): void {
    this.route.params.subscribe(
      (p: Params) => {
        this.heistId = p.id;

        this.heistService.getHeistById(this.heistId)
          .subscribe(
            res => this.heist = res
          );

        this.heistService.getHeistMembers(this.heistId)
          .subscribe(
            res => this.heist.members = res
          );

        this.heistService.getHeistOutcome(this.heistId)
          .subscribe(
            res => this.heist.outcome = res.outcome
          );
      }
    );
  }

  isOrganiser(): boolean{
    return this.userService.isRole('ROLE_ORGANISER');
  }

  onEdit(): void{
    this.router.navigate(['heist', this.heistId, 'edit']);
  }

  onStart(): void{
    this.heistService.startHeist(this.heistId)
      .subscribe(
        // ignored redirection header - no inspiration what to make that url into
        res => {
          console.log(res);
          this.router.navigate(['/heist']);
        }
      );
  }
}
