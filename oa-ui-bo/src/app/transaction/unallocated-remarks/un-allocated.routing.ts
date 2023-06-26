import { Routes } from '@angular/router';
import { UnAllocatedDetailComponent } from './components/un-allocated-detail/un-allocated-detail.component';
import { UnAllocatedListComponent } from './components/un-allocated-list/un-allocated-list.component';





export const routes: Routes = [
    { path: '', 
      children: [
        { path: 'un-allocated-list', component:UnAllocatedListComponent,  pathMatch: 'full' },
        { path: 'un-allocated-detail', component:UnAllocatedDetailComponent,  pathMatch: 'full' },
        {path: 'un-allocated-detail/:dispServicenumber/:month/:year/:remarks/:disporgname',component:UnAllocatedDetailComponent, pathMatch:'full'},
        { path: '**',redirectTo: 'un-allocated-list' }
      ]
    }
      
    ];