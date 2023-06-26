import { Routes } from '@angular/router';
import { TradeRelationshipListComponent } from './components/trade-relationship-list/trade-relationship-list.component';
import { TradeRelationshipDetailComponent } from './components/trade-relationship-detail/trade-relationship-detail.component';
//import { GeneratoranisationDetailComponent } from './components/generator-detail/generator-detail.component';

export const routes: Routes = [
{ path: '', 
  children: [
    //{ path: '', redirectTo: 'feeder-list' },
    { path: 'trade-relationship-list', component:TradeRelationshipListComponent,  pathMatch: 'full' },
    { path: 'trade-relationship-new',component: TradeRelationshipDetailComponent,  pathMatch: 'full' },
    { path: 'trade-relationship-edit/:_id',component: TradeRelationshipDetailComponent,  pathMatch: 'full'},
    { path: '**', redirectTo: 'trade-relationship-list' }
  ]
}
]; 