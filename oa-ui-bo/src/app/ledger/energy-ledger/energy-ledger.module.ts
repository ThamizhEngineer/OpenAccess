import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule,ReactiveFormsModule } from '@angular/forms'; 
import { MatAutocompleteModule, MatButtonModule, MatButtonToggleModule, MatCardModule, MatCheckboxModule, MatChipsModule, MatStepperModule, MatDatepickerModule, MatDialogModule, MatExpansionModule, MatGridListModule, MatIconModule, MatInputModule, MatListModule, MatMenuModule, MatNativeDateModule, MatPaginatorModule, MatProgressBarModule, MatProgressSpinnerModule, MatRadioModule, MatRippleModule, MatSelectModule, MatSidenavModule, MatSliderModule, MatSlideToggleModule, MatSnackBarModule, MatSortModule, MatTableModule, MatTabsModule, MatToolbarModule, MatTooltipModule} from '@angular/material';
import { FlexLayoutModule } from '@angular/flex-layout';
import { NgxDatatableModule } from '@swimlane/ngx-datatable';
import { EnergyLedgerListComponent } from './components/energy-ledger-list/energy-ledger-list.component';
import {  EnergyLedgerDetailComponent } from './components/energy-ledger-detail/energy-ledger-detail.component';
import { CommonUtils} from '../../shared/common/common-utils';
import { DatePipe } from '@angular/common';

import { routes } from './energy-ledger.routing';
//import { FeederanisationEvent } from './services/feeder.event'; 
import { EnergyLedgerService } from './services/energy-ledger.service';
//import { CommonService } from '../../shared/common/common.service';
//import { CustomDirective } from '../shared/services/custom.directive'; 

@NgModule({
  imports: [
    CommonModule,
    RouterModule.forChild(routes),
    MatAutocompleteModule, MatButtonModule,MatDatepickerModule,MatButtonToggleModule, MatCardModule, MatCheckboxModule, MatChipsModule, MatStepperModule, MatDatepickerModule, MatDialogModule, MatExpansionModule, MatGridListModule, MatIconModule, MatInputModule, MatListModule, MatMenuModule, MatNativeDateModule, MatPaginatorModule, MatProgressBarModule, MatProgressSpinnerModule, MatRadioModule, MatRippleModule, MatSelectModule, MatSidenavModule, MatSliderModule, MatSlideToggleModule, MatSnackBarModule, MatSortModule, MatTableModule, MatTabsModule, MatToolbarModule, MatTooltipModule,
    NgxDatatableModule,
    FormsModule, ReactiveFormsModule,
    FlexLayoutModule
  ],
  declarations: [ 
    EnergyLedgerListComponent,
    EnergyLedgerDetailComponent 
    ],
  providers: [EnergyLedgerService,CommonUtils,MatDatepickerModule,DatePipe]
})

export class EnergyLedgerModule {
  public static routes = routes;
}
