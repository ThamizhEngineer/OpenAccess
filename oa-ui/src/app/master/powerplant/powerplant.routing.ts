import { Routes } from '@angular/router';
import { PowerplantViewComponent } from './components/powerplant-view/powerplant-view.component';

export const routes: Routes = [{
    path: '',
    children: [
        {
            path: 'view',
            component: PowerplantViewComponent,
            pathMatch: 'full'
        },
        {
            path: '**',
            redirectTo: 'view'
        }
    ]
}];