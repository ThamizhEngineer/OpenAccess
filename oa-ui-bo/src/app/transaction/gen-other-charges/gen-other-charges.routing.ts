import { Routes } from '@angular/router';
import {GenOtherChargesDetailComponent } from './components/gen-other-charges-detail/gen-other-charges-detail.component';
import { GenOtherChargesListComponent } from './components/gen-other-charges-list/gen-other-charges-list.component';

export const routes: Routes = [
{ path: '', 
  children: [
    { path: '', redirectTo: 'gen-other-charges-list' },
    { path: 'gen-other-charges-list', component: GenOtherChargesListComponent,  pathMatch: 'full' },
    { path: 'gen-other-charges-detail/:_id',component:GenOtherChargesDetailComponent,  pathMatch: 'full' },
    { path: 'gen-other-charges-new',component: GenOtherChargesDetailComponent,  pathMatch: 'full'},
    { path: '**',redirectTo: 'gen-other-charges-list' }
  ]
}
];