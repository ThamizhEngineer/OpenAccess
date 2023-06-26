import { Routes } from '@angular/router';
import { AgreementListComponent } from './components/agreement-list/agreement-list.component';
import { AgreementDetailComponent } from './components/agreement-detail/agreement-detail.component';
import { AgreementConsentComponent } from './components/agreement-consent/agreement-consent.component';
import { AgreementNocComponent } from './components/agreement-noc/agreement-noc.component';
import { AgreementEwaComponent } from './components/agreement-ewa/agreement-ewa.component';
import { AgreementEpaComponent } from './components/agreement-epa/agreement-epa.component';
import { AgreementOaaComponent } from './components/agreement-oaa/agreement-oaa.component';
import { AgreementCompleteComponent } from './components/agreement-complete/agreement-complete.component';

export const routes: Routes = [
{ path: '', 
  children: [
	 { path: 'agreement-list', component: AgreementListComponent,  pathMatch: 'full' },
   { path: 'agreement-list/Consent',component: AgreementListComponent,  pathMatch: 'full'},
   { path: 'agreement-consent',component: AgreementConsentComponent,  pathMatch: 'full'},
   { path: 'agreement-list/Noc',component: AgreementListComponent,  pathMatch: 'full'},
   { path: 'agreement-noc',component: AgreementNocComponent,  pathMatch: 'full'},

    { path: 'agreement-list/Ewa',component: AgreementListComponent,  pathMatch: 'full'},
    { path: 'agreement-ewa',component: AgreementEwaComponent,  pathMatch: 'full'},
  
    { path: 'agreement-list/Oaa',component: AgreementListComponent,  pathMatch: 'full'},
    { path: 'agreement-oaa',component: AgreementOaaComponent,  pathMatch: 'full'},
    { path: 'agreement-list/Epa',component: AgreementListComponent,  pathMatch: 'full'},
    { path: 'agreement-epa',component: AgreementEpaComponent,  pathMatch: 'full'},
    { path: 'agreement-complete',component: AgreementCompleteComponent,  pathMatch: 'full'},
    { path: 'agreement-detail/:_id', component: AgreementDetailComponent,  pathMatch: 'full' },
   
    { path: '**', redirectTo: 'agreement-list' }
  ]
}
];