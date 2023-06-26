import { Routes } from '@angular/router';
import { SignupListComponent } from './components/signup-list/signup-list.component';
import { SignupDetailComponent } from './components/signup-detail/signup-detail.component';

export const routes: Routes = [
{ path: '', 
  children: [
    
	  { path: 'signup-list', component: SignupListComponent,  pathMatch: 'full' },
    { path: 'signup-new',  component: SignupDetailComponent,  pathMatch: 'full' },
    { path: 'signup-edit/:_id',component: SignupDetailComponent,  pathMatch: 'full'},
    { path: '**', redirectTo: 'signup-list' }
  ]
}
];