import { Component, OnInit } from '@angular/core';
import {Heist} from '../models/heist.model';
import {HeistService} from '../services/heist.service';

@Component({
  selector: 'app-frontpage',
  templateUrl: './frontpage.component.html',
  styleUrls: ['./frontpage.component.css']
})
export class FrontpageComponent implements OnInit {

  heists: Heist[];

  constructor(private heistService: HeistService) { }

  ngOnInit(): void {
    this.heistService.getAllHeists()
      .subscribe(
        res => this.heists = res.filter(h => new Date(h.endTime) > new Date())
      );
  }

}
