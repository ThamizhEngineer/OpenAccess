import { Routes } from '@angular/router';
import { DeleteTransactionListComponent } from './components/delete-transaction-list/delete-transaction-list.component';
import { DeleteTransactionDetailComponent } from './components/delete-transaction-detail/delete-transaction-detail.component';


export const routes: Routes = [
{ path: '', 
  children: [
    { path: 'delete-transaction-list', component: DeleteTransactionListComponent,  pathMatch: 'full' },
    { path: 'delete-transaction-new',component: DeleteTransactionDetailComponent,  pathMatch: 'full' },
    { path: 'delete-transaction-detail/:_genServiceNumber/:_readingMonth/:_readingYear',component: DeleteTransactionDetailComponent,  pathMatch: 'full'},
    { path: '**',redirectTo: 'delete-transaction-list' }
  ]
}
];