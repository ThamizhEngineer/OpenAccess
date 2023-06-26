import { Routes } from '@angular/router';
import { GcListComponent } from './components/gc-list/gc-list.component';
import { GcDetailComponent } from './components/gc-detail/gc-detail.component';

export const routes: Routes = [
{ path: '', 
  children: [
    { path: '', redirectTo: 'gc-list' },
    { path: 'gc-list', component: GcListComponent,  pathMatch: 'full' },
   // { path: 'gc-new',component: GcDetailComponent,  pathMatch: 'full' },
    { path: 'gc-edit/:_id',component: GcDetailComponent,  pathMatch: 'full' }
    
  ]
}
]; 