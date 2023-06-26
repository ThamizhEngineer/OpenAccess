import { Routes } from '@angular/router';

import { AdminLayoutComponent } from './layouts/admin/admin-layout.component';
import { AuthLayoutComponent } from './layouts/auth/auth-layout.component';
import { RouteGuardComponent } from './shared/route-guard/route-guard.component';
import { GscModule } from './transaction/generation-statment-consumer-end/gsc.module'
import { EscOrderModule } from './ledger/energy-sale-order-consumer-end/escorder.module'
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
			path: 'company',
			loadChildren: './master/company/company.module#CompanyModule',
			canActivate: [RouteGuardComponent]
		},
		{
			path: 'buyer-company',
			loadChildren: './master/buyer-company/buyer-company.module#BuyerCompanyModule',
			canActivate: [RouteGuardComponent]
		},
		{
			path: 'gc',
			loadChildren: './grid-connectivity/gc.module#GcModule',
			canActivate: [RouteGuardComponent]
		}, {
			path: 'oaa',
			loadChildren: './transaction/oaa/oaa.module#OaaModule',
			canActivate: [RouteGuardComponent]
		}, {
			path: 'gs',
			loadChildren: './transaction/generation-statement/gs.module#GsModule',
			canActivate: [RouteGuardComponent]
		},
		{
			path: 'gsc',
			loadChildren: './transaction/generation-statment-consumer-end/gsc.module#GscModule',
			canActivate: [RouteGuardComponent]
		}, {
			path: 'ea',
			loadChildren: './transaction/energy-allotment/ea.module#EaModule',
			canActivate: [RouteGuardComponent]
		},
		{
			path: 'es-intent',
			loadChildren: './transaction/energy-sale-intent/es-intent.module#EsIntentModule',
			canActivate: [RouteGuardComponent]
		},
		{
			path: 'es-order',
			loadChildren: './ledger/energy-sale-order/esorder.module#EsOrderModule',
			canActivate: [RouteGuardComponent]
		},
		{
			path: 'esc-order',
			loadChildren: './ledger/energy-sale-order-consumer-end/escorder.module#EscOrderModule',
			canActivate: [RouteGuardComponent]
		},
		{
			path: 'operations',
			loadChildren: './transaction/operations/operation.module#OperationModule',
			canActivate: [RouteGuardComponent]
		},

		{
			path: 'consent',
			loadChildren: './transaction/consent/consent.module#ConsentModule'
		},
		{
			path: 'delete-transaction',
			loadChildren: './transaction/delete-transaction/delete-transaction.module#DeleteTransactionModule'
		},
		{
			path:'block-transaction',
			loadChildren:'./transaction/block-transaction/block-transaction.module#BlockTransactionModule'	
		},
		{
			path:'transcoinvoices',
			loadChildren:'./transaction/transcoinvoice/transcoinvoices.module#TranscoinvoicesModule'
		},

		{
			path:'un-allocated',
			loadChildren:'./transaction/unallocated-remarks/un-allocated.module#UnAllocatedModule'
		},
		{
			path: 'feeder-line-loss',
			loadChildren: './transaction/feeder-line-loss/feeder-line-loss.module#FeederLineLossModule',
				
		},
		{
			path: 'feeder-loss-list',
			loadChildren: './transaction/feeder-loss-list/feeder-loss-list.module#FeederLossListModule',		
		},
		{
			path: 'unitwise',
			loadChildren: './unitwise/unitwise.module#UnitwiseModule'
		},
		{
			path: 'ses',
			loadChildren: './transaction/simple-energy-sale/ses.module#SesModule'
		},
		{
			path: 'payment',
			loadChildren: './payment/payment.module#PaymentModule'
		}, 
		{
			path: 'powerplant',
			loadChildren: './master/powerplant/powerplant.module#PowerplantModule'
		}, 
		{
			path: 'org',
			loadChildren: './master/organisation/org.module#OrgModule'
		}, 
		{
			path: 'generator-his',
			loadChildren: './master/generatorhis/generatorhis.module#GeneratorhisModule'
		}, 
		{
			path: 'tariff',
			loadChildren: './tariff/tariff.module#TariffModule'
		}, 
		{
			path: 'ewa',
			loadChildren: './transaction/ewa/ewa.module#EwaModule'
		},
		{
			path: 'epa',
			loadChildren: './transaction/epa/epa.module#EpaModule'
		},
		{
			path: 'reports',
			loadChildren: './report/report.module#ReportModule'
		},
		{
			path: 'sldcnocs',
			loadChildren: './sldcnoc/sldcnoc.module#SldcnocModule'
		},
		{
			path: 'sldcnocapproval',
			loadChildren: './sldcnoc/sldcnoc.module#SldcnocModule'
		}, 
		{
			path: 'ss',
			loadChildren: './master/substation/ss.module#SsModule'
		}, {
			path: 'fuel',
			loadChildren: './master/fuel/fuel.module#FuelModule'
		}, {
			path: 'feeder',
			loadChildren: './master/feeder/feeder.module#FeederModule'
		},
		{
			path: 'meter-reading-import',
			loadChildren: './transaction/meter-reading-import/meter-reading-import.module#MeterReadingImportModule'
		},
		{
			path: 'noc',
			loadChildren: './transaction/noc/noc.module#NocModule'
		},
		{
			path: 'iex-noc',
			loadChildren: './transaction/iex-noc/iex-noc.module#IexNocModule'
		},
		{
			path: 'noc-generator',
			loadChildren: './transaction/noc-generator/noc-generator.module#NocGeneratorModule'
		},
		{
			path: 'standing-clearence',
			loadChildren: './transaction/standing-clearence/standing-clearence.module#StandingClearenceModule'
		},
		{
			path: 'feeder-loss',
			loadChildren: './transaction/feeder-loss/feeder-loss.module#FeederLossModule'
		},
        {
			path:'commercial',
			loadChildren:'./transaction/commercial/commercial.module#CommercialModule'
		},
		{
			path:'importseller',
			loadChildren:'./transaction/importseller/importseller.module#ImportsellerModule'
		},
		{
			path:'meter-change',
			loadChildren:'./transaction/meter-change/meterChange.module#MeterChangeModule'
		},
		{
			path: 'ten-one-ss-loss',
			loadChildren: './transaction/ten-one-ss-loss/ten-one-ss-loss.module#TenOneSSLossModule'
		},
		{
			path: 'cs',
			loadChildren: './transaction/cs/cs.module#CsModule'
		},
		{
			path: 'company-meter-change',
			loadChildren: './transaction/company-meter-change/company-meter-change.module#CompanyMeterChangeModule'
		},
		{
			path: 'banking-balance',
			loadChildren: './transaction/banking-balance/banking-balance.module#BankingBalanceModule'
		},
		{
			path: 'gen-other-charges',
			loadChildren: './transaction/gen-other-charges/gen-other-charges.module#GenOtherChargesModule'
		},
		{
			path: 'company-name-change',
			loadChildren: './transaction/company-name-change/company-name-change.module#CompanyNameChangeModule'
		},
		{
			path: 'meter-reading',
			loadChildren: './transaction/meter-reading/meter-reading.module#MeterReadingModule'
		},
		{
			path: 'agreement',
			loadChildren: './transaction/agreement/agreement.module#AgreementModule'
		},
		{
			path: 'stb',
			loadChildren: './transaction/sale-to-board/stb.module#StbModule'
		}, {
			path: 'trade-relationship',
			loadChildren: './master/trade-relationship/trade-relationship.module#TradeRelationshipModule'
		},
		{
			path: 'energy-ledger',
			loadChildren: './ledger/energy-ledger/energy-ledger.module#EnergyLedgerModule'
		}, {
			path: 'ipaa',
			loadChildren: './transaction/ipaa/ipaa.module#IpaaModule',
			canActivate: [RouteGuardComponent]
		},
		{
			path: 'signup',
			loadChildren: './master/signup/signup.module#SignupModule'
		},
		{
			path: 'settings',
			loadChildren: './settings/settings.module#SettingsModule'
		},
		{
			path: 'log',
			loadChildren: './transaction/log/log.module#LogModule'
		},
		{
			path: 'sale',
			loadChildren: './transaction/sale/sale.module#SaleModule'
		},
		{
			path: 'power-sale',
			loadChildren: './transaction/power-sale/power-sale.module#PowerSaleModule'
		},
		{
			path: 'oaa-app',
			loadChildren: './transaction/oaa-app/oaa-app.module#OaaAppModule'
		},

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
