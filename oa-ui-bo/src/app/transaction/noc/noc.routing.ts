import { Routes } from '@angular/router';
import { NocListComponent } from './components/noc-list/noc-list.component';
import { NocDetailComponent } from './components/noc-detail/noc-detail.component';

// import { MeterReadingDetailComponent } from './components/meter-reading-detail/meter-reading-detail.component';

export const routes: Routes = [
{ path: '', 
  children: [
    { path: '', redirectTo: 'noc-list' },
    { path: 'noc-list', component: NocListComponent,  pathMatch: 'full' },
    { path: 'noc-new',component:NocDetailComponent,  pathMatch: 'full' },
    { path: 'noc-edit/:_id',component: NocDetailComponent,  pathMatch: 'full'},
    { path: '**',redirectTo: 'noc-list' }
  ]
}
];