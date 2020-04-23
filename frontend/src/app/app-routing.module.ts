import { NgModule } from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {RezeptViewComponent} from './rezept-view/rezept-view.component';
import {ZutatenListeComponent} from './zutaten-liste/zutaten-liste.component';

const routes: Routes = [
  {path: '', redirectTo: 'zutaten', pathMatch: 'full'},
  {path: 'zutaten', component: ZutatenListeComponent},
  {path: 'rezept/:zutaten', component: RezeptViewComponent}
];

@NgModule({
  imports: [
    RouterModule.forRoot(routes)
  ],
  exports: [RouterModule]
})
export class AppRoutingModule { }
