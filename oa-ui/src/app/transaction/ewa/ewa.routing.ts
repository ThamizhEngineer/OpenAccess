import { Routes } from '@angular/router';
import { EwaListComponent } from './components/ewa-list/ewa-list.component';
import { EwaDetailComponent } from './components/ewa-detail/ewa-detail.component';

export const routes: Routes = [
{ path: '', 
  children: [
	{ path: 'ewa-list', component: EwaListComponent,  pathMatch: 'full' },
    { path: 'ewa-new',component: EwaDetailComponent,  pathMatch: 'full' },
    { path: 'ewa-edit/:_id',component: EwaDetailComponent,  pathMatch: 'full'},
    { path: '**', redirectTo: 'ewa-list' }
  ]
}
];