import { Routes } from '@angular/router';
import { IexNocDetailComponent } from './components/iex-noc-detail/iex-noc-detail.component';
import { IexNocListComponent } from './components/iex-noc-list/iex-noc-list.component';

export const routes: Routes = [
{ path: '', 
  children: [
    { path: '', redirectTo: 'iex-noc-list' },
    { path: 'iex-noc-list', component: IexNocListComponent,  pathMatch: 'full' },
    { path: 'iex-noc-new',component:IexNocDetailComponent,  pathMatch: 'full' },
    { path: 'iex-noc-edit/:_id',component: IexNocDetailComponent,  pathMatch: 'full'},
    { path: '**',redirectTo: 'iex-noc-list' }
  ]
}
];