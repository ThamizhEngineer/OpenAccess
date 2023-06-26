import { Routes } from '@angular/router';
import { ImportsellerComponent } from './components/importsellers/importseller.component';


export const routes: Routes = [
  {
    path: '',
    children: [
      { path: 'importseller', component: ImportsellerComponent, pathMatch: 'full' },

      { path: '**', redirectTo: 'importseller' }
    ]
  }

];