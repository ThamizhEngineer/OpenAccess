import { Routes } from '@angular/router';
import { NocGeneratorListComponent } from './components/noc-generator-list/noc-generator-list.component';
import { NocGeneratorDetailComponent } from './components/noc-generator-detail/noc-generator-detail.component';

// import { MeterReadingDetailComponent } from './components/meter-reading-detail/meter-reading-detail.component';

export const routes: Routes = [
{ path: '', 
  children: [
    { path: '', redirectTo: 'noc-generator-list' },
    { path: 'noc-generator-list', component: NocGeneratorListComponent,  pathMatch: 'full' },
    { path: 'noc-generator-new',component:NocGeneratorDetailComponent,  pathMatch: 'full' },
    { path: 'noc-generator-edit/:_id',component: NocGeneratorDetailComponent,  pathMatch: 'full'},
    { path: '**',redirectTo: 'noc-generator-list' }
  ]
}
];