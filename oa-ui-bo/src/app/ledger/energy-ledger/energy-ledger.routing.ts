import { Routes } from '@angular/router';
import { EnergyLedgerListComponent } from './components/energy-ledger-list/energy-ledger-list.component';
import {  EnergyLedgerDetailComponent } from './components/energy-ledger-detail/energy-ledger-detail.component';
//import { GeneratoranisationDetailComponent } from './components/generator-detail/generator-detail.component';

export const routes: Routes = [
{ path: '', 
  children: [
    //{ path: '', redirectTo: 'feeder-list' },
    { path: 'energy-ledger-list', component:EnergyLedgerListComponent,  pathMatch: 'full' },
    // { path: 'trade-relationship-new',component: TradeRelationshipDetailComponent,  pathMatch: 'full' },
   { path: 'energy-ledger-edit/:_id',component: EnergyLedgerDetailComponent,  pathMatch: 'full'},
    { path: '**', redirectTo: 'energy-ledger-list' }
  ]
}
];