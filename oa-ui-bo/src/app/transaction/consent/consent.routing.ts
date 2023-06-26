import { Routes } from '@angular/router';
import { ConsentListComponent } from './components/consent-list/consent-list.component';
import { ConsentDetailComponent } from './components/consent-detail/consent-detail.component';

export const routes: Routes = [
{ path: '', 
  children: [
	{ path: 'consent-list', component: ConsentListComponent,  pathMatch: 'full' },
    { path: 'consent-new',component: ConsentDetailComponent,  pathMatch: 'full' },
    { path: 'consent-edit/:_id',component: ConsentDetailComponent,  pathMatch: 'full'},
    { path: '**', redirectTo: 'consent-list' }
  ]
}
];