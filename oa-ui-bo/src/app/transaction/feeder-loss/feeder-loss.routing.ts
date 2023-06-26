import { Routes } from '@angular/router';
import { FeederLossDialogComponent } from './component/feeder-loss-dialog.component';
import { FeederLossOptionsComponent } from './component/feeder-loss-options.component';
import { FeederLossViewComponent } from './component/feeder-loss-view.component';
import { FeederLossComponent } from './component/feeder-loss.component';


export const routes: Routes = [
    { path: '', 
    children: [
      { path: '', redirectTo: 'feeder-loss' },
      { path: 'feeder-loss', component: FeederLossComponent,  pathMatch: 'full' },
      { path: 'feeder-loss-dialog', component: FeederLossDialogComponent,  pathMatch: 'full' },
      { path: 'feeder-loss-options', component: FeederLossOptionsComponent,  pathMatch: 'full' },
      { path: 'feeder-loss-view/:_id',component:FeederLossViewComponent,  pathMatch: 'full'},
      { path: '**',redirectTo: 'feeder-loss' }
    ]
  }
  ];