import {RouterModule, Routes} from '@angular/router';
import {NgModule} from '@angular/core';
import {HeistComponent} from './heist/heist.component';

const routes: Routes = [
  {path: '', component: HeistComponent}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class HeistRoutingModule { }
