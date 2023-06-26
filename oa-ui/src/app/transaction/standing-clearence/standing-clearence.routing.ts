import { Routes } from '@angular/router';
import { StandingClearenceListComponent } from './components/standing-clearence-list/standing-clearence-list.component';
import { StandingClearenceDetailComponent } from './components/standing-clearence-detail/standing-clearence-detail.component';


export const routes: Routes = [
{ path: '', 
  children: [
    { path: '', redirectTo: 'standing-clearence-list' },
    { path: 'standing-clearence-list', component: StandingClearenceListComponent,  pathMatch: 'full' },
    { path: 'standing-clearence-new',component:StandingClearenceDetailComponent,  pathMatch: 'full' },
    { path: 'standing-clearence-edit/:_id',component:StandingClearenceDetailComponent,  pathMatch: 'full'},
    { path: '**',redirectTo: 'standing-clearence-list' }
  ]
}
];