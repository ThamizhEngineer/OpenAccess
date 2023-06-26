import { Routes } from '@angular/router';
import { CompanyListComponent } from './components/company-list/company-list.component';
import { CompanyDetailComponent } from './components/company-detail/company-detail.component';

export const routes: Routes = [
{ path: '', 
  children: [
    { path: '', redirectTo: 'company-list' },
    { path: 'company-list', component: CompanyListComponent,  pathMatch: 'full' },
    { path: 'company-edit/:_id',component: CompanyDetailComponent,  pathMatch: 'full' },
    { path: 'company-new',component: CompanyDetailComponent,  pathMatch: 'full' }
  ]
}
];