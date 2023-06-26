import { Routes } from '@angular/router';
import { StbListComponent } from './components/stb-list/stb-list.component';
import { StbDetailComponent } from './components/stb-detail/stb-detail.component';
import { StbPrintComponent } from './components/stb-print/stb-print.component';

export const routes: Routes = [
{ path: '', 
  children: [
    { path: '', redirectTo: 'stb-list' },
    { path: 'stb-list', component: StbListComponent,  pathMatch: 'full' },
     //{ path: 'stb-edit',component: StbDetailComponent,  pathMatch: 'full'},
     { path: 'stb-edit/:_id',component: StbDetailComponent,  pathMatch: 'full'},
     { path: 'stb-print/:_id',component: StbPrintComponent,  pathMatch: 'full'},
    // { path: 'gs-blockview/:_id',component: GsBlockviewComponent,  pathMatch: 'full'}
    
  ]
}
];