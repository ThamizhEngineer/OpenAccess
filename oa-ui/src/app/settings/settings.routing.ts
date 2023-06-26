import { Routes } from '@angular/router';
import { SettingsAccountComponent } from './components/account/account.component';

export const SettingsRoutes: Routes = [
{ path: '', 
  children: [
    { path: '', redirectTo: 'account' },
    { path: 'account', component: SettingsAccountComponent,  pathMatch: 'full' },
  ]
}
]; 