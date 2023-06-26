import { Routes } from '@angular/router';
import { TariffDetailComponent } from './components/tariff-detail/tariff-detail.component';

export const routes: Routes = [
  {
    path: '',
    children: [
      { path: '**', component: TariffDetailComponent, pathMatch: 'full'  }
    ]
  }
];