import { NgModule } from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {RezepteListViewComponent} from './rezepte-list-view/rezepte-list-view.component';
import {ZutatenListeComponent} from './zutaten-liste/zutaten-liste.component';
import {GroupComponent} from './group/group.component';

const routes: Routes = [
  {path: '', redirectTo: 'zutaten', pathMatch: 'full'},
  {path: 'zutaten', component: ZutatenListeComponent},
  {path: 'recipe', component: RezepteListViewComponent},
  {path: 'recipe/search', component: RezepteListViewComponent},
  {path: 'group/:groupID', component: GroupComponent},
  {path: '**', redirectTo: 'zutaten'}
];

@NgModule({
  imports: [
    RouterModule.forRoot(routes)
  ],
  exports: [RouterModule]
})
export class AppRoutingModule { }
