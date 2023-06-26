import { Routes } from '@angular/router';
import { SesListComponent } from './components/ses-list/ses-list.component';
import { SesDetailComponent } from './components/ses-detail/ses-detail.component';

export const routes: Routes = [
{ path: '', 
  children: [
    { path: '', redirectTo: 'ses-list' },
    { path: 'ses-new', component: SesDetailComponent,  pathMatch: 'full' },
    { path: 'ses-list', component: SesListComponent,  pathMatch: 'full' },
    { path: 'ses-edit/:_id',component: SesDetailComponent,  pathMatch: 'full'}
    
  ]
}
];