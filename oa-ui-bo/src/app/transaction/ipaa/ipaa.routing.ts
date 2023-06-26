import { Routes } from '@angular/router';
import {IpaaListComponent } from './components/ipaa-list/ipaa-list.component';
import { IpaaDetailComponent } from './components/ipaa-detail/ipaa-detail.component';

export const routes: Routes = [
{ path: '', 
  children: [
    { path: '', redirectTo: 'ipaa-list' },
    { path: 'ipaa-list', component: IpaaListComponent,  pathMatch: 'full' },
    { path: 'ipaa-edit/:_id',component: IpaaDetailComponent,  pathMatch: 'full'},
    { path: 'ipaa-new',component: IpaaDetailComponent,  pathMatch: 'full'}
  
    
  ]
}
];