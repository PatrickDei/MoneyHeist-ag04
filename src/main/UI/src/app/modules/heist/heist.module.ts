import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HeistComponent } from './heist/heist.component';
import {HeistRoutingModule} from './heist-routing.module';



@NgModule({
  declarations: [HeistComponent],
  imports: [
    CommonModule,
    HeistRoutingModule
  ]
})
export class HeistModule { }
