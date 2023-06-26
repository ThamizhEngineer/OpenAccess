import { Routes } from '@angular/router';
import { FuelListComponent } from './components/fuel-list/fuel-list.component';
import { FuelDetailComponent } from './components/fuel-detail/fuel-detail.component';

export const routes: Routes = [
    { path: '', 
      children: [
         { path: 'fuel-list', component: FuelListComponent,  pathMatch: 'full' },
         { path: 'fuel-new', component: FuelDetailComponent,  pathMatch: 'full' },
         { path: 'fuel-edit/:_id',component: FuelDetailComponent, pathMatch: 'full'},
         { path: '**' ,redirectTo: 'fuel-list' } 
      ]
    }
    ];