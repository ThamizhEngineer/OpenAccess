import { Routes } from '@angular/router';
import { OperationListComponent } from './components/operation-list/operation-list.component';

export const routes: Routes = [
{ path: '', 
  children: [
    { path: '', redirectTo: 'operation-list' },
    // { path: 'es-new', component: EsDetailComponent,  pathMatch: 'full' },
    { path: 'operation-list', component: OperationListComponent,  pathMatch: 'full' },
    
  ]
}
];