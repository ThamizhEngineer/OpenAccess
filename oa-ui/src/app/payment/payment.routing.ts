import { Routes } from '@angular/router';
import { PaymentListComponent } from './components/payment-list/payment-list.component';
import { PaymentDetailComponent } from './components/payment-detail/payment-detail.component';

export const routes: Routes = [
{ path: '',
  children: [
    { path: 'payment-list/:type', component: PaymentListComponent,  pathMatch: 'full' },
    { path: 'payment-new/:type', component: PaymentDetailComponent,  pathMatch: 'full' },
    { path: 'payment-edit/:_id/:type', component: PaymentDetailComponent,  pathMatch: 'full'},
    { path: ':type', redirectTo: 'payment-list/:type' },
    { path: '**', redirectTo: 'payment-list/sale' }
  ]
}
];