import { Routes } from '@angular/router';
import { MeterReadingListComponent } from './components/meter-reading-list/meter-reading-list.component';
// import { MeterReadingImportDetailComponent } from './components/meter-reading-import-detail/meter-reading-import-detail.component';
import { MeterReadingDetailComponent } from './components/meter-reading-detail/meter-reading-detail.component';

export const routes: Routes = [
{ path: '', 
  children: [
    { path: '', redirectTo: 'meter-reading-list' },
    { path: 'meter-reading-list', component: MeterReadingListComponent,  pathMatch: 'full' },
    { path: 'meter-reading-detail/:_id',component:MeterReadingDetailComponent,  pathMatch: 'full' },
    // { path: 'meter-reading-import-edit/:_id',component: MeterReadingImportDetailComponent,  pathMatch: 'full'},
    { path: '**',redirectTo: 'meter-reading-list' }
  ]
}
];