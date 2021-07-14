import { Component, OnInit } from '@angular/core';
import {Heist} from '../../../models/heist.model';
import {HeistService} from '../../../services/heist.service';
import {UserService} from '../../../services/user.service';

@Component({
  selector: 'app-heist',
  templateUrl: './heist.component.html',
  styleUrls: ['./heist.component.css']
})
export class HeistComponent implements OnInit {

  heists: Heist[];
  showingDetails: boolean[] = [];

  constructor(private heistService: HeistService,
              private userService: UserService) { }

  ngOnInit(): void {
    this.heistService.getAllHeists().subscribe(
      res => {
        this.heists = res;
        for (const h in this.heists)
          this.showingDetails.push(false);
      }
    );
  }

  showDetails(i){
    this.showingDetails[i] = !this.showingDetails[i];
    return this.showingDetails[i];
  }

  isOrganiser(): boolean{
    return this.userService.isRole('ROLE_ORGANISER');
  }
}
