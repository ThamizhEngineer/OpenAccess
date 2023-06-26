import { Routes } from '@angular/router';
import { CommercialComponent } from './components/commercials/commercial.component';

export const routes: Routes = [
  {
    path: '',
    children: [
      { path: 'commercial', component: CommercialComponent, pathMatch: 'full' },

      { path: '**', redirectTo: 'commercial' }
    ]
  }

];