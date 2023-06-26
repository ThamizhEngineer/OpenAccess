import { Routes } from '@angular/router';
import { LoginComponent } from './components/login/login.component';
import { LogoutComponent } from './components/logout/logout.component';

export const routes: Routes = [
{ path: '', 
  children: [
    { path: '', redirectTo: 'login' },
    { path: 'login', component: LoginComponent,  pathMatch: 'full' },
    { path: 'logout',component: LogoutComponent,  pathMatch: 'full' }
  ]
}
]; 