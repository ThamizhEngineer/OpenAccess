import { Routes } from '@angular/router';
import { OaaCreateComponent } from './components/oaa-create/oaa-create.component';

export const routes: Routes = [
{ path: '', 
  children: [
    { path: '**', redirectTo: 'oaa-create' },
	{ path: 'oaa-create', component: OaaCreateComponent,  pathMatch: 'full' }
  ]
}
];