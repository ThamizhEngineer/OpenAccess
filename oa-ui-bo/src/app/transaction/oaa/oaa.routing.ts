import { Routes } from '@angular/router';
import { OaaListComponent } from './components/oaa-list/oaa-list.component';
import { OaaDetailComponent } from './components/oaa-detail/oaa-detail.component';

export const routes: Routes = [
{ path: '', 
  children: [
    { path: '', redirectTo: 'oaa-list' },
    { path: 'oaa-list', component: OaaListComponent,  pathMatch: 'full' },
    { path: 'oaa-new',component: OaaDetailComponent,  pathMatch: 'full' },
    { path: 'oaa-edit/:_id',component: OaaDetailComponent,  pathMatch: 'full'}
    
  ]
}
];