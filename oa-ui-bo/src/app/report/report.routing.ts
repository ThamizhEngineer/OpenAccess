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
import { WegTransactionStatusReportComponent } from './components/weg-transaction-status-report/weg-transaction-status-report.component';
import { EnergyAdjustedOrderReportComponent } from './components/energy-adjusted-order-report/energy-adjusted-order.component';
import { EnergyChargeReportComponent} from './components/energy-charge-report/energy-charge-report.component';
import { CeeReportComponent } from './components/cee-report/cee-report.component';
// import { EnergyAllocationReportComponent } from './components/energy-allocation-report/energy-allocation-report.component';
import { SrcpReportComponent } from './components/srcp-report/srcp-report.component';
import { ProgressReportComponent } from './components/progress-report/progress-report.component';
import { NilGenerationReportComponent } from './components/nil-generation-report/nil-generation-report.component';
import { UnAllocatedGenStmtReportComponent } from './components/unallocated-gen-stmt-report/unallocated-gen-stmt-report.component';
import { FinUnutilBankReportComponent } from './components/financial-unutil-banking/fin-unutil-banking-report.component';
import { GenerationSummaryReportComponent } from './components/generation-summary-report/generation-summary-report.component';
import { EnergyLedgerReportComponent } from './components/energy-ledger-report/energy-ledger-report.component';
import { RemarksBasedBankingReportComponent } from './components/remarks-based-banking-report/remarks-based-banking-report.component';
import { EnergyGenerationChargesReportComponent } from './components/energy-generation-charges-report/energy-generation-charges-report.component';
import { ContactOfGeneratorReportComponent } from './components/contact-of-generator-report/contact-of-generator-report.component';
import { TnercNetGenerationReportComponent } from './components/tnerc-net-generation-report/tnerc-net-generation-report.component';
import { TnercEnergySummaryReportComponent } from './components/tnerc-energy-summary-report/tnerc-energy-summary-report.component';
import { ConsEnergyAdjustedReportComponent } from './components/consolidate-energy-adjustment-report/consolidate-energy-adjustment-report.component';
import { ConsEnergyAdjustedChargeComponent } from './components/consolidate-energy-adjusted-charge/consolidate-energy-adjusted-charge.component';
import { GeneratorWiseGenerationReportComponent } from './components/generator-wise-generation-report/generator-wise-generation-report.component';
import { ConsumerWiseEnergyAdjustmentOrderReportComponent } from './components/consumer-wise-energy-adjustment-order-report/consumer-wise-energy-adjustment-order-report';
import { UnutilisedEnergyReportComponent } from './components/unutilised-energy-report/unutilised-energy-report.component';
import { SaleToBoardLedgerComponent } from './components/sale-to-board-ledger/sale-to-board-ledger.component';
import { EnergyAuditReportListComponent } from './components/energy-audit-report/energy-audit-list/energy-audit-list.component';
import { EnergyAuditDetailComponent } from './components/energy-audit-report/energy-audit-detail/energy-audit-detail.component';
import { GenWiseChargeAllocToHtComponent } from './components/gen-wise-charge-alloc-to-ht/gen-wise-charge-alloc-to-ht.component';
import { UnutilisedBankingNewReportComponent } from './components/unutilised-banking-new-report/unutilised-banking-new-report.component';
import { ProgressReportNewComponent } from './components/progress-report-new/progress-report-new.component';
import { GeneratorSummaryComponent } from './components/generator-summary-report/generator-summary-report.component';
import { WegProgressReportComponent } from './components/weg-progress-report/weg-progress-report.component';
import { MeterChangeReportComponent } from './components/meter-change-report/meter-change-report.component';
import { GenSummaryChargesComponent } from './components/generation-summary-charges/generation-summary-charges.component';
import { AmrDownlodeComponent } from './components/amr-downlode-status-report/amr-downlode-status-report.component';
import { UtilityReportComponent } from './components/utility-report/utility-report.component';
import { MasterReportComponent } from './components/master-report/master-report.component';
import { Weg10Component } from './components/10(1)SS-weg-report/weg-10.component';
import { TechnicalInfo } from './components/Technical-info-report/technical-info-component';
import { DeleteTransactionReportComponent} from './components/delete-transaction-report/delete-transaction-report.component';
import { TantranscoGeneratorInfo } from './components/tantransco-generatorInfo-report/tantransco-generatorInfo-report.component';
import { TantranscoPaymentReport } from './components/tantransco-payment-report/tantransco-payment-report.component';

import { TnercBankingReport092022 } from './components/tnerc-banking-report09-2022/tnerc-banking-report09-2022.component';
import { SolarfeederEdcwiseComponent } from './components/solarfeeder-edcwise-report/solarfeeder-edcwise-report.component';
import { MeterReadingcalcComponent } from './components/amr-status/meter-readingcalc-report/meter-readingcalc-report.component';
import { MeterReadingcalcdetailComponent } from './components/amr-status/meter-readcalc-report-detail/meter-readingcalc-report-detail.component';
import { FiscalYearReportComponent } from './components/fiscal-year-report/fiscal-year-report.component';
import { MasterWindReportComponent } from './components/master-wind-component/master-wind-report.component';
import { ReportPageComponent } from './components/report-page/report-page-component/report-page.component';
import { GemerationstmtReportComponent } from './components/generationstmt-component/generationstmt-report.component';

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
      { path: 'generator-wise-generation-report', component: GeneratorWiseGenerationReportComponent, pathMatch: 'full' },
      { path: 'consumer-wise-energy-adjustment-order-report', component: ConsumerWiseEnergyAdjustmentOrderReportComponent, pathMatch: 'full' },
      { path: 'period-based-approval-report', component: PeriodBasedApprovalReportComponent, pathMatch: 'full' },
      { path: 'period-based-amendment-report', component: PeriodBasedAmendmentReportComponent, pathMatch: 'full'},
      { path: 'weg-generator-report', component: WegGeneratorReportComponent, pathMatch: 'full'},
      { path: 'total-transaction-details', component: TotalTransactionDetailsComponent, pathMatch: 'full'},
      { path: 'energy-allotment-report', component: EnergyAllotmentReportComponent, pathMatch: 'full'},
      { path: 'unimported-weg-services', component: UnimportedWegServicesComponent, pathMatch: 'full'},
      { path: 'weg-with-buyer-report', component: WegWithBuyerReportComponent, pathMatch: 'full'},
      { path: 'weg-transaction-status-report', component:WegTransactionStatusReportComponent, pathMatch: 'full'},
      { path: 'consolidate-energy-adjusted-charge', component: ConsEnergyAdjustedChargeComponent, pathMatch: 'full'},
      { path: 'energy-adjusted-order', component: EnergyAdjustedOrderReportComponent, pathMatch: 'full'},
      { path: 'energy-charge-report', component: EnergyChargeReportComponent, pathMatch: 'full'},
      { path: 'energy-ledger-report', component: EnergyLedgerReportComponent, pathMatch: 'full'},
      { path: 'energy-generation-charges-report', component: EnergyGenerationChargesReportComponent, pathMatch: 'full'},
      { path: 'unutil-banking-report', component: FinUnutilBankReportComponent, pathMatch: 'full'},
      { path: 'cee-report', component: CeeReportComponent, pathMatch: 'full'},
      { path: 'tnerc-net-generation-report', component: TnercNetGenerationReportComponent, pathMatch: 'full' },
      { path: 'tnerc-energy-summary-report', component: TnercEnergySummaryReportComponent, pathMatch: 'full'},
      { path: 'consolidate-energy-adjustment-report', component: ConsEnergyAdjustedReportComponent, pathMatch: 'full'},
      { path: 'unutilised-energy-report', component: UnutilisedEnergyReportComponent, pathMatch: 'full'},
      { path: 'sale-to-board-ledger', component: SaleToBoardLedgerComponent, pathMatch: 'full'},
      { path: 'gen-wise-charge-alloc-to-ht', component: GenWiseChargeAllocToHtComponent, pathMatch: 'full'},
      { path: 'srcp-report', component: SrcpReportComponent, pathMatch: 'full'},
      { path: 'progress-report', component: ProgressReportComponent, pathMatch: 'full'},
      { path: 'contact-of-generator-report', component: ContactOfGeneratorReportComponent, pathMatch: 'full'},
      { path: 'remarks-based-banking-report', component: RemarksBasedBankingReportComponent, pathMatch: 'full'},
      { path: 'nil-generation-report', component: NilGenerationReportComponent, pathMatch: 'full'},
      { path: 'unallocated-gen-stmt-report', component: UnAllocatedGenStmtReportComponent, pathMatch: 'full'},
      { path: 'generation-summary-report', component: GenerationSummaryReportComponent, pathMatch: 'full'},
      { path: 'energy-audit-report', component: EnergyAuditReportListComponent, pathMatch: 'full'},
      { path: 'energy-audit-list/:genServNumber/:searchfromMonth/:searchFromYear/:searchtoMonth/:searchToYear', component: EnergyAuditReportListComponent, pathMatch: 'full'},
      { path: 'energy-audit-detail/:genServNumber/:month/:year/:searchfromMonth/:searchFromYear/:searchtoMonth/:searchToYear',component: EnergyAuditDetailComponent,  pathMatch: 'full'},
      { path: 'unutilised-banking-new-report', component: UnutilisedBankingNewReportComponent, pathMatch: 'full'},
      { path: 'progress-report-new', component: ProgressReportNewComponent, pathMatch: 'full'},
      { path: 'unutilised-banking-new-report', component: UnutilisedBankingNewReportComponent, pathMatch: 'full'},
      { path: 'progress-report-new', component: ProgressReportNewComponent, pathMatch: 'full'},
      { path: 'weg-progress-report', component: WegProgressReportComponent, pathMatch: 'full'},
      { path: 'meter-change-report', component: MeterChangeReportComponent, pathMatch: 'full'},
      { path: 'generator-summary-report', component: GeneratorSummaryComponent, pathMatch: 'full'},
      { path: 'generation-summary-charges', component: GenSummaryChargesComponent, pathMatch: 'full'},
      {path: 'amr-downlode-component', component:AmrDownlodeComponent, pathMatch: 'full'},
      {path: 'utility-report', component:UtilityReportComponent, pathMatch: 'full'},
      { path: 'master-report', component: MasterReportComponent,pathMatch:'full'},
      {path: 'weg-10(1)ss-report', component:Weg10Component, pathMatch: 'full'},
      {path: 'technical-info-report', component:TechnicalInfo, pathMatch: 'full'},
      { path: 'delete-transaction-report', component: DeleteTransactionReportComponent, pathMatch:'full'},
      { path: 'tantransco-generatorInfo-report', component:TantranscoGeneratorInfo, pathMatch:'full'},
      { path: 'tantransco-payment-report', component:TantranscoPaymentReport, pathMatch:'full'},
      {path: 'meter-readingcalc-component', component:MeterReadingcalcComponent, pathMatch: 'full'},
      {path: 'meter-readingcalc-report-detail/:dispOrgCode/:readMonth/:readYear/:fuelTypeCode/:type', component:MeterReadingcalcdetailComponent, pathMatch: 'full'},
      {path: 'solarfeeder-edcwise-component', component:SolarfeederEdcwiseComponent, pathMatch: 'full'},
      {path: 'tnerc-banking-report09-2022-component', component:TnercBankingReport092022, pathMatch: 'full'},
      {path:'fiscal-year-report',component:FiscalYearReportComponent,pathMatch:'full'},
      {path:'master-wind-report',component:MasterWindReportComponent,pathMatch:'full'},
      {path:'report-page',component:ReportPageComponent,pathMatch:'full'},
      {path:'generationstmt-report',component:GemerationstmtReportComponent,pathMatch:'full'},
      { path: '**', redirectTo: 'org-report' }
    ]
  }
];
