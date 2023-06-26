import { Routes } from '@angular/router';
import { LogListComponent } from './components/log-list/log-list.component';
import { LogDetailComponent } from './components/log-detail/log-detail.component';

export const routes: Routes = [
{ path: '', 
  children: [
    { path: 'detail',component: LogDetailComponent,  pathMatch: 'full'},
//    { path: 'detail/:_id',component: LogDetailComponent,  pathMatch: 'full'},
    { path: '**',redirectTo: 'detail' }
  ]
}
];