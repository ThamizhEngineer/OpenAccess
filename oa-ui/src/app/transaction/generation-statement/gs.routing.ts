import { Routes } from '@angular/router';
import { GsListComponent } from './components/gs-list/gs-list.component';
import { GsDetailComponent } from './components/gs-detail/gs-detail.component';
import { GsSlotviewComponent } from './components/gs-slotview/gs-slotview.component';
import { GsBlockviewComponent } from './components/gs-blockview/gs-blockview.component';

export const routes: Routes = [
{ path: '', 
  children: [
    { path: '', redirectTo: 'gs-list' },
    { path: 'gs-list', component: GsListComponent,  pathMatch: 'full' },
    { path: 'gs-edit/:_id',component: GsDetailComponent,  pathMatch: 'full'},
   // { path: 'gs-edit/:_id/view/:_viewType',component: GsDetailComponent,  pathMatch: 'full'},
    { path: 'gs-slotview/:_id',component: GsSlotviewComponent,  pathMatch: 'full'},
    { path: 'gs-blockview/:_id',component: GsBlockviewComponent,  pathMatch: 'full'}
    
  ]
}
];