import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { MatAutocompleteModule, MatButtonModule, MatButtonToggleModule, MatCardModule, MatCheckboxModule, MatChipsModule, MatStepperModule, MatDatepickerModule, MatDialogModule, MatExpansionModule, MatGridListModule, MatIconModule, MatInputModule, MatListModule, MatMenuModule, MatNativeDateModule, MatPaginatorModule, MatProgressBarModule, MatProgressSpinnerModule, MatRadioModule, MatRippleModule, MatSelectModule, MatSidenavModule, MatSliderModule, MatSlideToggleModule, MatSnackBarModule, MatSortModule, MatTableModule, MatTabsModule, MatToolbarModule, MatTooltipModule } from '@angular/material';
import { FlexLayoutModule } from '@angular/flex-layout';

import { NgxDatatableModule } from '@swimlane/ngx-datatable';

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
import { EnergyAdjustedOrderReportComponent } from './components/energy-adjusted-order-report/energy-adjusted-order.component';
import { EnergyChargeReportComponent} from './components/energy-charge-report/energy-charge-report-list.component';

@NgModule({
  imports: [
    CommonModule,
    RouterModule.forChild(routes),
    MatAutocompleteModule, MatButtonModule, MatButtonToggleModule, MatCardModule, MatCheckboxModule, MatChipsModule, MatStepperModule, MatDatepickerModule, MatDialogModule, MatExpansionModule, MatGridListModule, MatIconModule, MatInputModule, MatListModule, MatMenuModule, MatNativeDateModule, MatPaginatorModule, MatProgressBarModule, MatProgressSpinnerModule, MatRadioModule, MatRippleModule, MatSelectModule, MatSidenavModule, MatSliderModule, MatSlideToggleModule, MatSnackBarModule, MatSortModule, MatTableModule, MatTabsModule, MatToolbarModule, MatTooltipModule,
    NgxDatatableModule,
    FormsModule,
    FlexLayoutModule,
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
    GeneratorWiseConsumerReportComponent,
    PeriodBasedApprovalReportComponent,
    WegGeneratorReportComponent,
    PeriodBasedAmendmentReportComponent,
    TotalTransactionDetailsComponent,
    EnergyChargeReportComponent,
    EnergyAdjustedOrderReportComponent,
    EnergyAllotmentReportComponent,UnimportedWegServicesComponent,WegWithBuyerReportComponent
  ],
  providers: [CommonService,ReportService]
})

export class ReportModule {
  public static routes = routes;
}
