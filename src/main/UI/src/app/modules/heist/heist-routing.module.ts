import {RouterModule, Routes} from '@angular/router';
import {NgModule} from '@angular/core';
import {HeistComponent} from './heist/heist.component';
import {HeistDetailsComponent} from './heist-details/heist-details.component';
import {HeistCreateComponent} from './heist-create/heist-create.component';
import {HeistEditComponent} from './heist-edit/heist-edit.component';

const routes: Routes = [
  {path: '', component: HeistComponent},
  {path: 'create', component: HeistCreateComponent},
  {path: ':id', component: HeistDetailsComponent},
  {path: ':id/edit', component: HeistEditComponent}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class HeistRoutingModule { }
