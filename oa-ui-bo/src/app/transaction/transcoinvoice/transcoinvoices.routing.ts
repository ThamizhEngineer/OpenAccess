import { Routes } from '@angular/router';

import { TranscoinvoiceDetailComponent } from './components/transcoinvoice-detail/transcoinvoice-detail.component';
import { TranscoinvoiceListComponent } from './components/transcoinvoice-list/transcoinvoice-list.component';






export const routes: Routes = [
    { path: '', 
      children: [
        { path: 'transcoinvoice-detail', component:TranscoinvoiceDetailComponent,  pathMatch: 'full' },
        { path: 'transcoinvoice-list', component:TranscoinvoiceListComponent,  pathMatch: 'full' },
        {path: 'transcoinvoice-detail/:month/:year/:invoiceId/:dispservicenumber',component:TranscoinvoiceDetailComponent, pathMatch:'full'},
        { path: '**',redirectTo:'transcoinvoice-list' }

      ]
    }
      
    ];