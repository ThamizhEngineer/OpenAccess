import { Routes } from '@angular/router';
import { OrgListComponent } from './components/org-list/org-list.component';
//import { OrganisationDetailComponent } from './components/org-detail/org-detail.component';

export const routes: Routes = [
{ path: '', 
  children: [
    { path: '', redirectTo: 'org-list' },
    { path: 'org-list', component: OrgListComponent,  pathMatch: 'full' }
  ]
}
];