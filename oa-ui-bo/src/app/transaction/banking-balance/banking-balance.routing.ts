import { Routes } from '@angular/router';
import {BankingBalanceListComponent } from './components/banking-balance-list/banking-balance-list.component';
import { BankingBalanceDetailComponent } from './components/banking-balance-detail/banking-balance-detail.component';
// import { MeterReadingDetailComponent } from './components/meter-reading-detail/meter-reading-detail.component';

export const routes: Routes = [
{ path: '', 
  children: [
    { path: '', redirectTo: 'banking-balance-list' },
    { path: 'banking-balance-list', component: BankingBalanceListComponent,  pathMatch: 'full' },
    { path: 'banking-balance-detail/:_id',component:BankingBalanceDetailComponent,  pathMatch: 'full' },
    { path: 'banking-balance-new',component: BankingBalanceDetailComponent,  pathMatch: 'full'},
    { path: '**',redirectTo: 'banking-balance-list' }
  ]
}
];