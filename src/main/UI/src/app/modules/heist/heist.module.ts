import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HeistComponent } from './heist/heist.component';
import {HeistRoutingModule} from './heist-routing.module';
import { HeistDetailsComponent } from './heist-details/heist-details.component';
import { HeistCreateComponent } from './heist-create/heist-create.component';
import {ReactiveFormsModule} from '@angular/forms';



@NgModule({
  declarations: [HeistComponent, HeistDetailsComponent, HeistCreateComponent],
  imports: [
    CommonModule,
    HeistRoutingModule,
    ReactiveFormsModule
  ]
})
export class HeistModule { }
