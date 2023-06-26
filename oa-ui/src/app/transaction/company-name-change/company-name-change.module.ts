import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { MatAutocompleteModule, MatButtonModule, MatButtonToggleModule, MatCardModule, MatCheckboxModule, MatChipsModule, MatStepperModule, MatDatepickerModule, MatDialogModule, MatExpansionModule, MatGridListModule, MatIconModule, MatInputModule, MatListModule, MatMenuModule, MatNativeDateModule, MatPaginatorModule, MatProgressBarModule, MatProgressSpinnerModule, MatRadioModule, MatRippleModule, MatSelectModule, MatSidenavModule, MatSliderModule, MatSlideToggleModule, MatSnackBarModule, MatSortModule, MatTableModule, MatTabsModule, MatToolbarModule, MatTooltipModule } from '@angular/material';
import { FlexLayoutModule } from '@angular/flex-layout';

import { NgxDatatableModule } from '@swimlane/ngx-datatable';

import { CompanyNameChangeListComponent,CompanyNameChangeFlowTypeComponent } from './components/company-name-change-list/company-name-change-list.component';
import { CompanyNameChangeDetailComponent } from './components/company-name-change-detail/company-name-change-detail.component';
import { CompanyDetails } from './components/company-details/company-details.component';
import { CancelExistingAgreements } from './components/cancel-existing-agreements/cancel-existing-agreements.component';
import { ApproveCaptiveChange } from './components/approve-captive-change/approve-captive-change.component';
import { CaptiveChangeDetailComponent } from './components/captive-change/captive-change-detail.component';
import { routes } from './company-name-change.routing';
import { CompanyNameChangeService } from './services/company-name-change.service';
import { CommonService } from '../../shared/common/common.service';
import { CustomDirective } from '../../shared/services/custom.directive';

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
  entryComponents: [CompanyNameChangeListComponent, CompanyNameChangeFlowTypeComponent],
  declarations: [
    CompanyNameChangeListComponent,
    CompanyNameChangeDetailComponent,CompanyNameChangeFlowTypeComponent,
    CompanyDetails,
    CancelExistingAgreements,
    ApproveCaptiveChange,CaptiveChangeDetailComponent
  ],
  providers: [CommonService,CompanyNameChangeService]
  // exports:[GeneratorDetail, ConsumerDetail],
  // entryComponents: [GeneratorDetail, ConsumerDetail],
})

export class CompanyNameChangeModule {
  public static routes = routes;
}
