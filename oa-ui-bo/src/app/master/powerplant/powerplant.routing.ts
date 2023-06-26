import { Routes } from '@angular/router';
import { PowerplantListComponent } from './components/powerplant-list/powerplant-list.component';
import { PowerplantDetailComponent } from './components/powerplant-detail/powerplant-detail.component';
import { PowerplantViewComponent } from './components/powerplant-view/powerplant-view.component';

export const routes: Routes = [{
    path: '',
    children: [
        {
            path: 'my-powerplant',
            component: PowerplantListComponent,
            pathMatch: 'full'
        },
		{
            path: 'nces-powerplant',
            component: PowerplantListComponent,
            pathMatch: 'full'
        },
		{
            path: 'powerplant-list',
            component: PowerplantListComponent,
            pathMatch: 'full'
        },
        {
            path: 'powerplant-list/:_id',
            component: PowerplantListComponent,
            pathMatch: 'full'
        },
        {
            path: 'powerplant-new',
            component: PowerplantDetailComponent,
            pathMatch: 'full'
        },
        {
            path: 'powerplant-edit/:_id',
            component: PowerplantDetailComponent,
            pathMatch: 'full'
        },
        {
            path: 'powerplant-view/:_id',
            component: PowerplantViewComponent,
            pathMatch: 'full'
        },
        {
            path: '**',
            redirectTo: 'powerplant-list'
        }
    ]
}];