import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms'; 
import { MatAutocompleteModule, MatButtonModule, MatButtonToggleModule, MatCardModule, MatCheckboxModule, MatChipsModule, MatStepperModule, MatDatepickerModule, MatDialogModule, MatExpansionModule, MatGridListModule, MatIconModule, MatInputModule, MatListModule, MatMenuModule, MatNativeDateModule, MatPaginatorModule, MatProgressBarModule, MatProgressSpinnerModule, MatRadioModule, MatRippleModule, MatSelectModule, MatSidenavModule, MatSliderModule, MatSlideToggleModule, MatSnackBarModule, MatSortModule, MatTableModule, MatTabsModule, MatToolbarModule, MatTooltipModule} from '@angular/material';
import { FlexLayoutModule } from '@angular/flex-layout';
import { NgxDatatableModule } from '@swimlane/ngx-datatable';

import { routes } from './esorder.routing';
// import { EsEvent } from './services/es.event'; 
import { EsorderService } from '../../ledger/energy-sale-order/components/services/esorder.service'; 
import { CommonService } from '../../shared/common/common.service';
import { EsOrderListComponent } from './components/es-order-list/es-order-list.component';
// import { EsAddComponent } from './components/es-add/es-add.component';
// import { GsService } from './../generation-statement/services/gs.service';
// import { EsDetailComponent } from './components/es-detail/es-detail.component';
// import { CompanyService } from './../../master/company/services/company.service';
import { CommonUtils} from '../../shared/common/common-utils';
// import { TradeRelationshipService } from './../../master/trade-relationship/services/trade-relationship.service';
import { EsOrderDetailComponent } from './components/es-order-detail/es-order-detail.component';
import { BankingBalanceService} from '../../transaction/banking-balance/services/banking-balance.service'

@NgModule({
  imports: [
    CommonModule,
    RouterModule.forChild(routes),
    MatAutocompleteModule, MatButtonModule, MatButtonToggleModule, MatCardModule, MatCheckboxModule, MatChipsModule, MatStepperModule, MatDatepickerModule, MatDialogModule, MatExpansionModule, MatGridListModule, MatIconModule, MatInputModule, MatListModule, MatMenuModule, MatNativeDateModule, MatPaginatorModule, MatProgressBarModule, MatProgressSpinnerModule, MatRadioModule, MatRippleModule, MatSelectModule, MatSidenavModule, MatSliderModule, MatSlideToggleModule, MatSnackBarModule, MatSortModule, MatTableModule, MatTabsModule, MatToolbarModule, MatTooltipModule,
    NgxDatatableModule,
    FormsModule,
    FlexLayoutModule
  ],
  entryComponents: [EsOrderListComponent],
  declarations: [ 
    EsOrderListComponent,EsOrderDetailComponent 
    ],
  providers: [ CommonService,EsorderService,CommonUtils,BankingBalanceService]
})

export class EsOrderModule {
  public static routes = routes;
}
