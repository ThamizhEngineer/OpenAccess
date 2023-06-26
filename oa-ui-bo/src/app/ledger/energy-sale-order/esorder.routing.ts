import { Routes } from '@angular/router';
import { EsOrderListComponent } from './components/es-order-list/es-order-list.component';
import { EsOrderDetailComponent } from './components/es-order-detail/es-order-detail.component';

export const routes: Routes = [
{ path: '', 
  children: [
    { path: '', redirectTo: 'es-order-list' },
    // { path: 'es-new', component: EsDetailComponent,  pathMatch: 'full' },
    { path: 'es-order-list', component: EsOrderListComponent,  pathMatch: 'full' },
    { path: 'es-order-detail/:_id',component: EsOrderDetailComponent,  pathMatch: 'full'}
    
  ]
}
];