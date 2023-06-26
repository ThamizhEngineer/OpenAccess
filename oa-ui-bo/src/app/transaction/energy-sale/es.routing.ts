import { Routes } from '@angular/router';
import { EsListComponent } from './components/es-list/es-list.component';
import { EsDetailComponent } from './components/es-detail/es-detail.component';

export const routes: Routes = [
{ path: '', 
  children: [
    { path: '', redirectTo: 'es-list' },
    { path: 'es-new', component: EsDetailComponent,  pathMatch: 'full' },
    { path: 'es-list', component: EsListComponent,  pathMatch: 'full' },
    { path: 'es-edit/:_id',component: EsDetailComponent,  pathMatch: 'full'}
    
  ]
}
];