import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms'; 
import { MatAutocompleteModule, MatButtonModule, MatButtonToggleModule, MatCardModule, MatCheckboxModule, MatChipsModule, MatStepperModule, MatDatepickerModule, MatDialogModule, MatExpansionModule, MatGridListModule, MatIconModule, MatInputModule, MatListModule, MatMenuModule, MatNativeDateModule, MatPaginatorModule, MatProgressBarModule, MatProgressSpinnerModule, MatRadioModule, MatRippleModule, MatSelectModule, MatSidenavModule, MatSliderModule, MatSlideToggleModule, MatSnackBarModule, MatSortModule, MatTableModule, MatTabsModule, MatToolbarModule, MatTooltipModule} from '@angular/material';
import { FlexLayoutModule } from '@angular/flex-layout';
import { CustomDirective } from '../../shared/services/custom.directive';

import { NgxDatatableModule } from '@swimlane/ngx-datatable';
import { CommonService } from '../../shared/common/common.service';

import { CsListComponent } from './components/cs-list/cs-list.component';
import { CsDetailComponent } from './components/cs-detail/cs-detail.component';
import { IdentifyPowerplant } from './components/identify-powerplant/identify-powerplant';
import { InvestmentDetails } from './components/investment-details/investment-details';
import { QuantumAllocation } from './components/quantum-allocation/quantum-allocation';
import { CancelExistingAgreements } from './components/cancel-existing-agreements/cancel-existing-agreements.component';
import { CreateEsiAgreements } from './components/create-esi/create-esi.component';
import { Summary } from './components/summary/summary';


import { routes } from './cs.routing'; 
import { CsService } from './services/cs.service'; 
import { CsEvent } from './services/cs.event'; 

@NgModule({
  imports: [
    CommonModule,
    RouterModule.forChild(routes),
    MatAutocompleteModule, MatButtonModule, MatButtonToggleModule, MatCardModule, MatCheckboxModule, MatChipsModule, MatStepperModule, MatDatepickerModule, MatDialogModule, MatExpansionModule, MatGridListModule, MatIconModule, MatInputModule, MatListModule, MatMenuModule, MatNativeDateModule, MatPaginatorModule, MatProgressBarModule, MatProgressSpinnerModule, MatRadioModule, MatRippleModule, MatSelectModule, MatSidenavModule, MatSliderModule, MatSlideToggleModule, MatSnackBarModule, MatSortModule, MatTableModule, MatTabsModule, MatToolbarModule, MatTooltipModule,
    NgxDatatableModule,
    FormsModule,
    FlexLayoutModule,CustomDirective
  ],
    entryComponents: [CsListComponent],

  declarations: [ 
    CsListComponent,
    CsDetailComponent,
	IdentifyPowerplant,
	InvestmentDetails,
	QuantumAllocation,
	CancelExistingAgreements,
	CreateEsiAgreements,
	Summary
    ],
  providers: [CsService,CommonService, CsEvent]
})

export class CsModule {
  public static routes = routes;
}
