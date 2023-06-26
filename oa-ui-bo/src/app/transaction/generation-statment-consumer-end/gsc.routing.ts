import { Routes } from '@angular/router';
import { GscDetailComponent } from './components/gs-consumer-detail/gs-consumer-detail.component';
import { GsConsumerListComponent } from './components/gs-consumer-list/gs-consumer-list.component';
// import { GsSlotviewComponent } from './components/gs-slotview/gs-slotview.component';
// import { GsBlockviewComponent } from './components/gs-blockview/gs-blockview.component';

export const routes: Routes = [
{ path: '', 
  children: [
    { path: '', redirectTo: 'gs-consumer-list' },
    { path: 'gs-consumer-list', component: GsConsumerListComponent,  pathMatch: 'full' },
    { path: 'gs-edits/:_id',component: GscDetailComponent,  pathMatch: 'full'},
  //  { path: 'gs-edit/:_id/view/:_viewType',component: GscDetailComponent,  pathMatch: 'full'},
  //   { path: 'gs-slotview/:_id',component: GsSlotviewComponent,  pathMatch: 'full'},
  //   { path: 'gs-blockview/:_id',component: GsBlockviewComponent,  pathMatch: 'full'}
    
  ]
}
];