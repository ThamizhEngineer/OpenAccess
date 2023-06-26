import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms'; 
import { MatAutocompleteModule, MatButtonModule, MatButtonToggleModule, MatCardModule, MatCheckboxModule, MatChipsModule, MatStepperModule, MatDatepickerModule, MatDialogModule, MatExpansionModule, MatGridListModule, MatIconModule, MatInputModule, MatListModule, MatMenuModule, MatNativeDateModule, MatPaginatorModule, MatProgressBarModule, MatProgressSpinnerModule, MatRadioModule, MatRippleModule, MatSelectModule, MatSidenavModule, MatSliderModule, MatSlideToggleModule, MatSnackBarModule, MatSortModule, MatTableModule, MatTabsModule, MatToolbarModule, MatTooltipModule} from '@angular/material';
import { FlexLayoutModule } from '@angular/flex-layout';

import { NgxDatatableModule } from '@swimlane/ngx-datatable';
// import { MeterReadingDetailComponent } from './components/meter-reading-detail/meter-reading-detail.component';
import { CommonService } from '../../shared/common/common.service';

import {BankingBalanceListComponent } from './components/banking-balance-list/banking-balance-list.component';
import { BankingBalanceDetailComponent } from './components/banking-balance-detail/banking-balance-detail.component';


import { routes } from './banking-balance.routing'; 
import { BankingBalanceService } from './services/banking-balance.service'; 
import { CommonUtils } from '../../shared/common/common-utils';

@NgModule({
  imports: [
    CommonModule,
    RouterModule.forChild(routes),
    MatAutocompleteModule, MatButtonModule, MatButtonToggleModule, MatCardModule, MatCheckboxModule, MatChipsModule, MatStepperModule, MatDatepickerModule, MatDialogModule, MatExpansionModule, MatGridListModule, MatIconModule, MatInputModule, MatListModule, MatMenuModule, MatNativeDateModule, MatPaginatorModule, MatProgressBarModule, MatProgressSpinnerModule, MatRadioModule, MatRippleModule, MatSelectModule, MatSidenavModule, MatSliderModule, MatSlideToggleModule, MatSnackBarModule, MatSortModule, MatTableModule, MatTabsModule, MatToolbarModule, MatTooltipModule,
    NgxDatatableModule,
    FormsModule,
    FlexLayoutModule
  ],
    entryComponents: [BankingBalanceListComponent,BankingBalanceDetailComponent],

  declarations: [ 
    BankingBalanceListComponent,
    BankingBalanceDetailComponent
    ],
  providers: [BankingBalanceService,CommonService,CommonUtils]
})

export class BankingBalanceModule {
  public static routes = routes;
}
