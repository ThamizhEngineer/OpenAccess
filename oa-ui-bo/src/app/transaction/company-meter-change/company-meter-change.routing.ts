import { Routes } from '@angular/router';
import { CompanyMeterChangeListComponent } from './components/company-meter-change-list/company-meter-change-list.component';
import { CompanyMeterChangeDetailComponent } from './components/company-meter-change-detail/company-meter-change-detail.component';

export const routes: Routes = [
{ path: '', 
  children: [
	{ path: 'company-meter-change-list', component: CompanyMeterChangeListComponent,  pathMatch: 'full' },
    { path: 'company-meter-change-new',component: CompanyMeterChangeDetailComponent,  pathMatch: 'full' },
    { path: 'company-meter-change-edit/:_id',component: CompanyMeterChangeDetailComponent,  pathMatch: 'full'},
    { path: '**', redirectTo: 'company-meter-change-list' }
  ]
}
];