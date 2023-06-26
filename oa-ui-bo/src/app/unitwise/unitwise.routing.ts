import { Routes } from '@angular/router';
import { UnitwiseDetailComponent } from './components/unitwise-detail/unitwise-detail.component';

export const routes: Routes = [
  {
    path: '',
    children: [
      { path: ':type', component: UnitwiseDetailComponent, pathMatch: 'full' },
      { path: '**', redirectTo: 'generation' }
    ]
  }
];