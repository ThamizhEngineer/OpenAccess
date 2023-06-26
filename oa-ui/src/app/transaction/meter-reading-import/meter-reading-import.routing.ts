import { Routes } from '@angular/router';
import { MeterReadingImportListComponent } from './components/meter-reading-import-list/meter-reading-import-list.component';
import { MeterReadingImportDetailComponent } from './components/meter-reading-import-detail/meter-reading-import-detail.component';
import { MeterReadingImportProcessComponent } from './components/meter-reading-import-process/meter-reading-import-process.component'; 
import { MeterReadingImportDisplayComponent } from './components/meter-reading-import-display/meter-reading-import-display.component'; 
export const routes: Routes = [
{ path: '', 
  children: [
    // { path: '', redirectTo: 'meter-reading-import-list' },
    { path: '', redirectTo: 'meter-reading-import-process' },
    
    { path: 'meter-reading-import-list', component: MeterReadingImportListComponent,  pathMatch: 'full' },
    { path: 'meter-reading-import-new',component:MeterReadingImportDetailComponent,  pathMatch: 'full' },
    { path: 'meter-reading-import-edit/:_id',component: MeterReadingImportDetailComponent,  pathMatch: 'full'},
    { path: 'meter-reading-import-process',component: MeterReadingImportProcessComponent,  pathMatch: 'full'},
    { path: 'meter-reading-import-display',component: MeterReadingImportDisplayComponent,  pathMatch: 'full'},
    // { path: '**',redirectTo: 'meter-reading-import-list' },
    { path: '**',redirectTo: 'meter-reading-import-process' }
  ]
}
];