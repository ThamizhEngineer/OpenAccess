import { Routes } from '@angular/router';
import { MeterChangeComponent } from './components/meterChange/meterChange';

export const routes: Routes = [
    {
        path: '',
        children: [
            { path: 'meter-change', component: MeterChangeComponent, pathMatch: 'full' },

            { path: '**', redirectTo: 'meter-change' }
        ]
    }

];