import { Routes } from '@angular/router';
import { EpaListComponent } from './components/epa-list/epa-list.component';
import { EpaDetailComponent } from './components/epa-detail/epa-detail.component';

export const routes: Routes = [
{ path: '', 
  children: [
	{ path: 'epa-list', component: EpaListComponent,  pathMatch: 'full' },
    { path: 'epa-new',component: EpaDetailComponent,  pathMatch: 'full' },
    { path: 'epa-edit/:_id',component: EpaDetailComponent,  pathMatch: 'full'},
    { path: '**', redirectTo: 'epa-list' }
  ]
}
];