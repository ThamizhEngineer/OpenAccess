import { Routes } from '@angular/router';

import { AdminLayoutComponent } from './layouts/admin/admin-layout.component';
import { AuthLayoutComponent } from './layouts/auth/auth-layout.component';
import { RouteGuardComponent } from './shared/route-guard/route-guard.component';
export const AppRoutes: Routes = [
	{
		path: '',
		redirectTo: 'login',
		pathMatch: 'full',
	}, {
		path: '',
		component: AdminLayoutComponent,
		children: [{
				path: 'home',
				loadChildren: './dashboard/dashboard.module#DashboardModule',
				canActivate: [RouteGuardComponent]
			}, {
				path: 'my-powerplant',
				loadChildren: './master/powerplant/powerplant.module#PowerplantModule'
			},
			{
				path: 'meter-reading',
				loadChildren: './transaction/meter-reading/meter-reading.module#MeterReadingModule'
			},
			{
				path: 'es',
				loadChildren: './transaction/energy-sale/es.module#EsModule',
				canActivate: [RouteGuardComponent]
			},	
			{
				path: 'gs',
				loadChildren: './transaction/generation-statement/gs.module#GsModule',
				canActivate: [RouteGuardComponent]
			},
			{
				path: 'feeder-line-loss',
				loadChildren: './feeder-line-loss/feeder-line-loss.module#FeederLineLossModule',
				canActivate: [RouteGuardComponent]
			},
			
			{
				path: 'ea',
				loadChildren: './transaction/energy-allotment/ea.module#EaModule',
				canActivate: [RouteGuardComponent]
			},
			{
				path: 'es-order',
				loadChildren: './ledger/energy-sale-order/esorder.module#EsOrderModule',
				canActivate: [RouteGuardComponent]
			},
			{
				path:'transcoinvoices',
				loadChildren:'./transaction/transcoinvoice/transcoinvoices.module#TranscoinvoicesModule',
				canActivate: [RouteGuardComponent]
			},
			{
				path: 'generator-his',
				loadChildren: './master/generatorhis/generatorhis.module#GeneratorhisModule',
				canActivate: [RouteGuardComponent]
			}, 
			{
				path: 'reports',
				loadChildren: './report/report.module#ReportModule'
			},
			{
				path: 'settings',
				loadChildren: './settings/settings.module#SettingsModule'
			}
		]
	}, {
		path: '',
		component: AuthLayoutComponent,
		children: [{
			path: 'login',
			loadChildren: './login/login.module#LoginModule'
		}, {
			path: 'logout',
			loadChildren: './login/login.module#LoginModule'
		}]
	}, {
		path: '**',
		redirectTo: 'session/404'
	}
];
