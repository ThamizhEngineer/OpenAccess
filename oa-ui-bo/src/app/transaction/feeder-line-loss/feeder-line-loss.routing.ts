import { Routes } from "@angular/router";
import { FeederLineLossComponent } from "./components/feeder-line-loss/feeder-line-loss.component";

export const routes: Routes = [
    { path: '', 
      children: [
        { path: '', redirectTo: 'feeder-line-loss' },
        {path: 'feeder-line-loss',component:FeederLineLossComponent, pathMatch:'full'}]
        // { path: 'trade-relationship-new',component: TradeRelationshipDetailComponent,  pathMatch: 'full' }  
    }
    ];