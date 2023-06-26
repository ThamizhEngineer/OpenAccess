import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { MatAutocompleteModule, MatButtonModule, MatButtonToggleModule, MatCardModule, MatCheckboxModule, MatChipsModule, MatStepperModule, MatDatepickerModule, MatDialogModule, MatExpansionModule, MatGridListModule, MatIconModule, MatInputModule, MatListModule, MatMenuModule, MatNativeDateModule, MatPaginatorModule, MatProgressBarModule, MatProgressSpinnerModule, MatRadioModule, MatRippleModule, MatSelectModule, MatSidenavModule, MatSliderModule, MatSlideToggleModule, MatSnackBarModule, MatSortModule, MatTableModule, MatTabsModule, MatToolbarModule, MatTooltipModule } from '@angular/material';
import { FlexLayoutModule } from '@angular/flex-layout';
import { NgxDatatableModule } from '@swimlane/ngx-datatable';

import { AgreementListComponent } from './components/agreement-list/agreement-list.component';
import { AgreementConsentComponent } from './components/agreement-consent/agreement-consent.component';
import { AgreementNocComponent } from './components/agreement-noc/agreement-noc.component';
import { AgreementEwaComponent } from './components/agreement-ewa/agreement-ewa.component';
import { AgreementEpaComponent } from './components/agreement-epa/agreement-epa.component';
import { AgreementOaaComponent } from './components/agreement-oaa/agreement-oaa.component';
import { AgreementCompleteComponent } from './components/agreement-complete/agreement-complete.component';
import { AgreementDetailComponent } from './components/agreement-detail/agreement-detail.component';
import { routes } from './agreement.routing';

 import {AgreementService } from './services/agreement.service';
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
  declarations: [
   AgreementListComponent,
   AgreementConsentComponent,AgreementDetailComponent,
   AgreementNocComponent,
   AgreementEwaComponent,
   AgreementOaaComponent,
   AgreementCompleteComponent,
   AgreementEpaComponent
  ],
  providers: [CommonService,AgreementService],
})

export class AgreementModule {
  public static routes = routes;
}