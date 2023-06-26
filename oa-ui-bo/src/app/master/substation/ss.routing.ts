import { Routes } from '@angular/router';
import { SsListComponent } from './components/ss-list/ss-list.component';
import { SsDetailComponent } from './components/ss-detail/ss-detail.component';
//import { GeneratoranisationDetailComponent } from './components/generator-detail/generator-detail.component';

export const routes: Routes = [
{ path: '', 
  children: [
    { path: 'ss-list', component: SsListComponent,  pathMatch: 'full' },
    { path: 'ss-new', component: SsDetailComponent,  pathMatch: 'full' },
    { path: 'ss-edit/:_id',component: SsDetailComponent, pathMatch: 'full'},
    { path: '' ,redirectTo: 'ss-list' } 
  ]
}
];