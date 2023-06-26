import { Routes } from '@angular/router';
import { SaleCreateComponent } from './components/sale-create/sale-create.component';
import { SaleDetailComponent } from './components/sale-detail/sale-detail.component';
import { SaleListComponent } from './components/sale-list/sale-list.component';

export const routes: Routes = [
{ path: '', 
  children: [
	{ path: 'sale-create', component: SaleCreateComponent,  pathMatch: 'full' },
    { path: 'sale-new',component: SaleDetailComponent,  pathMatch: 'full' },
    { path: 'sale-detail/:_type',component: SaleDetailComponent,  pathMatch: 'full'},
    { path: 'list/:_type',component: SaleListComponent,  pathMatch: 'full'},
    { path: '**', redirectTo: 'sale-create' }
  ]
}
];