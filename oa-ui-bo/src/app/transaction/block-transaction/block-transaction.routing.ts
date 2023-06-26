import { Routes } from '@angular/router';
import { BlockTransactionDetailComponent } from './components/block-transaction-detail/block-transaction-detail.component';
import { BlockTransactionListComponent } from './components/block-transaction-list/block-transaction-list.component';

export const routes: Routes = [
{ path: '', 
  children: [
    { path: 'block-transaction-list', component:BlockTransactionListComponent,  pathMatch: 'full' },
    { path: 'block-transaction-detail', component:BlockTransactionDetailComponent,  pathMatch: 'full' },
    {path: 'block-transaction-detail/:mCompServiceId/:month/:year/:remarks/:transactionType/:status',component: BlockTransactionDetailComponent,  pathMatch: 'full'},
    
    { path: '**',redirectTo: 'block-transaction-list' }
  ]
}

];