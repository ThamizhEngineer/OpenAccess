import { Routes } from '@angular/router';
import { CompanyNameChangeListComponent } from './components/company-name-change-list/company-name-change-list.component';
import { CompanyNameChangeDetailComponent } from './components/company-name-change-detail/company-name-change-detail.component';
import { CaptiveChangeDetailComponent } from './components/captive-change/captive-change-detail.component';
export const routes: Routes = [
{ path: '', 
  children: [
	{ path: 'company-name-change-list', component: CompanyNameChangeListComponent,  pathMatch: 'full' },
    { path: 'company-name-change-new',component: CompanyNameChangeDetailComponent,  pathMatch: 'full' },
    { path: 'company-name-change-edit',component: CompanyNameChangeDetailComponent,  pathMatch: 'full'},
    { path: 'captive-change-new',component: CaptiveChangeDetailComponent,  pathMatch: 'full' },
    { path: 'captive-change-edit',component: CaptiveChangeDetailComponent,  pathMatch: 'full' },
    { path: '**', redirectTo: 'company-name-change-list' }
  ]
}
];