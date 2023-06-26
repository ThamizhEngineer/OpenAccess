import { Routes } from '@angular/router';
import { EaListComponent } from './components/ea-list/ea-list.component';
import { EaDetailComponent } from './components/ea-detail/ea-detail.component';
import { EaProcessComponent } from './components/ea-process/ea-process.component';

export const routes: Routes = [
{ path: '', 
  children: [
    { path: '', redirectTo: 'ea-list' },
    { path: 'ea-list', component: EaListComponent,  pathMatch: 'full' },
    { path: 'ea-process', component: EaProcessComponent,  pathMatch: 'full' },
    { path: 'ea-detail/:_id', component: EaDetailComponent,  pathMatch: 'full' },
    { path: 'ea-edit/:_id',component: EaDetailComponent,  pathMatch: 'full'}
    
  ]
}
];