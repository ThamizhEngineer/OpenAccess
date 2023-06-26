import { Routes } from '@angular/router';
import { PwuCriteriaComponent } from './components/purchased-weg-units-report/purchased-weg-units-report.component';
import { PwuDisplayEdcUnitsComponent } from './components/purchased-weg-units-report/purchased-weg-units-report.component';
import { PwuDisplayServiceUnitsComponent } from './components/purchased-weg-units-report/purchased-weg-units-report.component';
import { CaptiveCriteriaComponent } from './components/captive-wheeling-report/captive-wheeling-report.component';
import { CaptiveDisplayEdcUnitsComponent } from './components/captive-wheeling-report/captive-wheeling-report.component';
import { CaptiveDisplayServiceUnitsComponent } from './components/captive-wheeling-report/captive-wheeling-report.component';
import { GeneratorBasedConsumerUsageComponent} from './components/generator-based-consumer-usage/generator-based-consumer-usage.component';
import { OrgReportComponent } from './components/org-report/org-report.component';
import { GenerationReportComponent } from './components/generation-report/generation-report.component';
import { ConsumerReportComponent } from './components/consumer-report/consumer-report.component';
import { GeneratorWiseConsumerReportComponent } from './components/generator-wise-consumer-report/generator-wise-consumer-report.component';
import { PeriodBasedApprovalReportComponent } from './components/period-based-approval-report/period-based-approval-report.component';
import { PeriodBasedAmendmentReportComponent } from './components/period-based-amendment-report/period-based-amendment-report.component';
import { WegGeneratorReportComponent } from './components/weg-generator-report/weg-generator-report.component';
import { EnergyAllotmentReportComponent } from './components/energy-allotment-report/energy-allotment-report.component';
import { TotalTransactionDetailsComponent } from './components/total-transaction-details/total-transaction-details.component';
import { UnimportedWegServicesComponent } from './components/unimported-weg-services/unimported-weg-services.component';
import { WegWithBuyerReportComponent } from './components/weg-with-buyer-report/weg-with-buyer-report.component';
import { EnergyAdjustedOrderReportComponent } from './components/energy-adjusted-order-report/energy-adjusted-order.component';
import { EnergyChargeReportComponent} from './components/energy-charge-report/energy-charge-report-list.component';

export const routes: Routes = [
  {
    path: '',
    children: [
      { path: 'generator-based-consumer-usage', component: GeneratorBasedConsumerUsageComponent, pathMatch: 'full' },
      { path: 'pwu-criteria', component: PwuCriteriaComponent, pathMatch: 'full' },
      { path: 'pwu-display-edc-units', component: PwuDisplayEdcUnitsComponent, pathMatch: 'full' },
      { path: 'pwu-display-service-units', component: PwuDisplayServiceUnitsComponent, pathMatch: 'full' },
      { path: 'captive-criteria', component: CaptiveCriteriaComponent, pathMatch: 'full' },
      { path: 'captive-display-edc-units', component: CaptiveDisplayEdcUnitsComponent, pathMatch: 'full' },
      { path: 'captive-display-service-units', component: CaptiveDisplayServiceUnitsComponent, pathMatch: 'full' },
      { path: 'org-report', component: OrgReportComponent, pathMatch: 'full' },
      { path: 'generation-report', component: GenerationReportComponent, pathMatch: 'full' },
      { path: 'consumer-report', component: ConsumerReportComponent, pathMatch: 'full' },
      { path: 'generator-wise-consumer-report', component: GeneratorWiseConsumerReportComponent, pathMatch: 'full' },
      { path: 'period-based-approval-report', component: PeriodBasedApprovalReportComponent, pathMatch: 'full' },
      { path: 'period-based-amendment-report', component: PeriodBasedAmendmentReportComponent, pathMatch: 'full'},
      { path: 'weg-generator-report', component: WegGeneratorReportComponent, pathMatch: 'full'},
      { path: 'total-transaction-details', component: TotalTransactionDetailsComponent, pathMatch: 'full'},
      { path: 'energy-allotment-report', component: EnergyAllotmentReportComponent, pathMatch: 'full'},
      { path: 'unimported-weg-services', component: UnimportedWegServicesComponent, pathMatch: 'full'},
      { path: 'weg-with-buyer-report', component: WegWithBuyerReportComponent, pathMatch: 'full'},
      { path: 'energy-adjusted-order', component: EnergyAdjustedOrderReportComponent, pathMatch: 'full'},
      { path: 'energy-charge-report', component: EnergyChargeReportComponent, pathMatch: 'full'},
      { path: '**', redirectTo: 'org-report' }
    ]
  }
];