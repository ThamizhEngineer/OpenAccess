import { Routes } from '@angular/router';
import { CsListComponent } from './components/cs-list/cs-list.component';
import { CsDetailComponent } from './components/cs-detail/cs-detail.component';

// import { MeterReadingDetailComponent } from './components/meter-reading-detail/meter-reading-detail.component';

export const routes: Routes = [
{ path: '', 
  children: [
    { path: '', redirectTo: 'cs-list' },
    { path: 'cs-list', component: CsListComponent,  pathMatch: 'full' },
    { path: 'cs-new',component:CsDetailComponent,  pathMatch: 'full' },
    { path: 'cs-edit/:_id',component: CsDetailComponent,  pathMatch: 'full'},
    { path: '**',redirectTo: 'cs-list' }
  ]
}
];