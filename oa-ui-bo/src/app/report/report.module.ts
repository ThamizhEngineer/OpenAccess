import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { CommonModule, DatePipe } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { MatAutocompleteModule, MatButtonModule, MatButtonToggleModule, MatCardModule, MatCheckboxModule, MatChipsModule, MatStepperModule, MatDatepickerModule, MatDialogModule, MatExpansionModule, MatGridListModule, MatIconModule, MatInputModule, MatListModule, MatMenuModule, MatNativeDateModule, MatPaginatorModule, MatProgressBarModule, MatProgressSpinnerModule, MatRadioModule, MatRippleModule, MatSelectModule, MatSidenavModule, MatSliderModule, MatSlideToggleModule, MatSnackBarModule, MatSortModule, MatTableModule, MatTabsModule, MatToolbarModule, MatTooltipModule } from '@angular/material';
import { FlexLayoutModule } from '@angular/flex-layout';

import { NgxDatatableModule } from '@swimlane/ngx-datatable';
import { ExcelExportService } from  '../shared/services/excelExport';

import { PwuCriteriaComponent } from './components/purchased-weg-units-report/purchased-weg-units-report.component';
import { PwuDisplayEdcUnitsComponent } from './components/purchased-weg-units-report/purchased-weg-units-report.component';
import { PwuDisplayServiceUnitsComponent } from './components/purchased-weg-units-report/purchased-weg-units-report.component';
import { CaptiveCriteriaComponent } from './components/captive-wheeling-report/captive-wheeling-report.component';
import { CaptiveDisplayEdcUnitsComponent } from './components/captive-wheeling-report/captive-wheeling-report.component';
import { CaptiveDisplayServiceUnitsComponent } from './components/captive-wheeling-report/captive-wheeling-report.component';
import {GeneratorBasedConsumerUsageComponent} from './components/generator-based-consumer-usage/generator-based-consumer-usage.component';

import { routes } from './report.routing';
import { ReportService } from './services/report.service';
import { CommonService } from '../shared/common/common.service';
import { CustomDirective } from '../shared/services/custom.directive';
import { OrgReportComponent } from './components/org-report/org-report.component';
import { GenerationReportComponent } from './components/generation-report/generation-report.component';
import { GeneratorWiseConsumerReportComponent } from './components/generator-wise-consumer-report/generator-wise-consumer-report.component';
import { ConsumerReportComponent } from './components/consumer-report/consumer-report.component';
import { PeriodBasedApprovalReportComponent } from './components/period-based-approval-report/period-based-approval-report.component';
import { PeriodBasedAmendmentReportComponent } from './components/period-based-amendment-report/period-based-amendment-report.component';
import { WegGeneratorReportComponent } from './components/weg-generator-report/weg-generator-report.component';
import { EnergyAllotmentReportComponent } from './components/energy-allotment-report/energy-allotment-report.component';
import { UnimportedWegServicesComponent } from './components/unimported-weg-services/unimported-weg-services.component';
import { TotalTransactionDetailsComponent } from './components/total-transaction-details/total-transaction-details.component';
import { WegWithBuyerReportComponent } from './components/weg-with-buyer-report/weg-with-buyer-report.component';
import { WegTransactionStatusReportComponent } from './components/weg-transaction-status-report/weg-transaction-status-report.component';
import { EnergyAdjustedOrderReportComponent } from './components/energy-adjusted-order-report/energy-adjusted-order.component';
import { EnergyChargeReportComponent} from './components/energy-charge-report/energy-charge-report.component';
import { GenerationSummaryReportComponent } from './components/generation-summary-report/generation-summary-report.component';

import { FinUnutilBankReportComponent } from './components/financial-unutil-banking/fin-unutil-banking-report.component';
import { CeeReportComponent } from './components/cee-report/cee-report.component';
// import { EnergyAllocationReportComponent } from './components/energy-allocation-report/energy-allocation-report.component';
import { SrcpReportComponent } from './components/srcp-report/srcp-report.component';
import { ProgressReportComponent } from './components/progress-report/progress-report.component';
import { EnergyAuditReportListComponent } from './components/energy-audit-report/energy-audit-list/energy-audit-list.component';
import { EnergyAuditDetailComponent } from './components/energy-audit-report/energy-audit-detail/energy-audit-detail.component';
import { NilGenerationReportComponent } from './components/nil-generation-report/nil-generation-report.component';
import { UnAllocatedGenStmtReportComponent } from './components/unallocated-gen-stmt-report/unallocated-gen-stmt-report.component';
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
import { DeleteTransactionReportComponent } from './components/delete-transaction-report/delete-transaction-report.component';
import { TantranscoGeneratorInfo } from './components/tantransco-generatorInfo-report/tantransco-generatorInfo-report.component';
import { TantranscoPaymentReport } from './components/tantransco-payment-report/tantransco-payment-report.component';

import { TnercBankingReport092022 } from './components/tnerc-banking-report09-2022/tnerc-banking-report09-2022.component';
import { ChartsModule } from 'ng2-charts';
import { SolarfeederEdcwiseComponent } from './components/solarfeeder-edcwise-report/solarfeeder-edcwise-report.component';
import { MeterReadingcalcComponent } from './components/amr-status/meter-readingcalc-report/meter-readingcalc-report.component';
import { MeterReadingcalcdetailComponent } from './components/amr-status/meter-readcalc-report-detail/meter-readingcalc-report-detail.component';
import { FiscalYearReportComponent } from './components/fiscal-year-report/fiscal-year-report.component';
import { MasterWindReportComponent } from './components/master-wind-component/master-wind-report.component';
import { ReportPageComponent } from './components/report-page/report-page-component/report-page.component';
import { GemerationstmtReportComponent } from './components/generationstmt-component/generationstmt-report.component';
@NgModule({
  imports: [
    CommonModule,
    RouterModule.forChild(routes),
    MatAutocompleteModule, MatButtonModule, MatButtonToggleModule, MatCardModule, MatCheckboxModule, MatChipsModule, MatStepperModule, MatDatepickerModule, MatDialogModule, MatExpansionModule, MatGridListModule, MatIconModule, MatInputModule, MatListModule, MatMenuModule, MatNativeDateModule, MatPaginatorModule, MatProgressBarModule, MatProgressSpinnerModule, MatRadioModule, MatRippleModule, MatSelectModule, MatSidenavModule, MatSliderModule, MatSlideToggleModule, MatSnackBarModule, MatSortModule, MatTableModule, MatTabsModule, MatToolbarModule, MatTooltipModule,
    NgxDatatableModule,
    FormsModule,
    FlexLayoutModule,ChartsModule,
    CustomDirective
  ],
  declarations: [
    PwuCriteriaComponent,
    PwuDisplayEdcUnitsComponent,
    PwuDisplayServiceUnitsComponent,
    CaptiveCriteriaComponent,
    CaptiveDisplayEdcUnitsComponent,
    CaptiveDisplayServiceUnitsComponent,
    GeneratorBasedConsumerUsageComponent,
    OrgReportComponent,
    ConsumerReportComponent,
    GenerationReportComponent,
    GenerationSummaryReportComponent,
    GeneratorWiseConsumerReportComponent,
    PeriodBasedApprovalReportComponent,
    WegGeneratorReportComponent,
    PeriodBasedAmendmentReportComponent,
    TotalTransactionDetailsComponent,
    TnercNetGenerationReportComponent,
    TnercEnergySummaryReportComponent,
    ConsEnergyAdjustedReportComponent,
    UnutilisedEnergyReportComponent,
    SaleToBoardLedgerComponent,
    EnergyAllotmentReportComponent,UnimportedWegServicesComponent,
    WegWithBuyerReportComponent,WegTransactionStatusReportComponent,EnergyAdjustedOrderReportComponent,
    EnergyChargeReportComponent,FinUnutilBankReportComponent,CeeReportComponent,SrcpReportComponent,
    ProgressReportComponent,EnergyAuditReportListComponent,EnergyAuditDetailComponent,NilGenerationReportComponent,UnAllocatedGenStmtReportComponent,
    ProgressReportComponent,NilGenerationReportComponent,UnAllocatedGenStmtReportComponent,
    EnergyLedgerReportComponent,RemarksBasedBankingReportComponent,EnergyGenerationChargesReportComponent,
    ContactOfGeneratorReportComponent,ConsEnergyAdjustedChargeComponent,GeneratorWiseGenerationReportComponent,
    ConsumerWiseEnergyAdjustmentOrderReportComponent,
    GenWiseChargeAllocToHtComponent,
    UnutilisedBankingNewReportComponent,
    ProgressReportNewComponent,
    WegProgressReportComponent,
    GeneratorSummaryComponent,MeterChangeReportComponent,GemerationstmtReportComponent,
    GenSummaryChargesComponent,FiscalYearReportComponent,MasterWindReportComponent,ReportPageComponent,
    AmrDownlodeComponent,UtilityReportComponent,MasterReportComponent,MeterReadingcalcComponent,SolarfeederEdcwiseComponent,MeterReadingcalcdetailComponent,
    Weg10Component,TechnicalInfo,DeleteTransactionReportComponent,TantranscoGeneratorInfo,TantranscoPaymentReport,TnercBankingReport092022
    // EnergyAllocationReportComponent
  ],
  providers: [CommonService,ReportService,ExcelExportService,DatePipe]

})

export class ReportModule {
  public static routes = routes;
}
