import { Routes } from '@angular/router';
import { EscOrderListComponent } from './components/esc-order-list/esc-order-list.component';
import { EscOrderDetailComponent } from './components/esc-order-detail/esc-order-detail.component';

export const routes: Routes = [
{ path: '', 
  children: [
    { path: '', redirectTo: 'esc-order-list' },
    // { path: 'es-new', component: EsDetailComponent,  pathMatch: 'full' },
    { path: 'esc-order-list', component: EscOrderListComponent,  pathMatch: 'full' },
    { path: 'esc-order-detail/:_id',component: EscOrderDetailComponent,  pathMatch: 'full'}
    
  ]
}
];