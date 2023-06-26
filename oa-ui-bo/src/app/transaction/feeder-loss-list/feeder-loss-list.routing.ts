import { Routes } from '@angular/router';
import { FeederLossListComponent } from './component/feeder-loss-list.component';



export const routes: Routes = [
    { path: '', 
    children: [
      { path: '', redirectTo: 'feeder-loss-list' },
      { path: 'feeder-loss-list', component: FeederLossListComponent,  pathMatch: 'full' }
    ]
  }
  ];