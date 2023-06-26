import { GeneratorHisComponent } from './components/generator-his/generator-his.component';
import { Routes } from '@angular/router';

//import { OrganisationDetailComponent } from './components/org-detail/org-detail.component';

export const routes: Routes = [
{ path: '', 
  children: [
    { path: '', redirectTo: 'generator-his' },
    { path: 'generator-his', component: GeneratorHisComponent,  pathMatch: 'full' }
  ]
}
];