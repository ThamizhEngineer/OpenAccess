import { Routes } from '@angular/router';
import { FeederListComponent } from './components/feeder-list/feeder-list.component';
import { FeederDetailComponent } from './components/feeder-detail/feeder-detail.component';
//import { GeneratoranisationDetailComponent } from './components/generator-detail/generator-detail.component';

export const routes: Routes = [
{ path: '', 
  children: [
    //{ path: '', redirectTo: 'feeder-list' },
    { path: 'feeder-list', component: FeederListComponent,  pathMatch: 'full' },
    { path: 'feeder-new',component: FeederDetailComponent,  pathMatch: 'full' },
    { path: 'feeder-edit/:_id',component: FeederDetailComponent,  pathMatch: 'full'},
    { path: '**', redirectTo: 'feeder-list' }
  ]
}
]; 