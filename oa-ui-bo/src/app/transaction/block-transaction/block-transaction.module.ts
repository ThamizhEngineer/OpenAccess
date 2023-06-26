import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms'; 
import { MatAutocompleteModule, MatButtonModule, MatButtonToggleModule, MatCardModule, MatCheckboxModule, MatChipsModule, MatStepperModule, MatDatepickerModule, MatDialogModule, MatExpansionModule, MatGridListModule, MatIconModule, MatInputModule, MatListModule, MatMenuModule, MatNativeDateModule, MatPaginatorModule, MatProgressBarModule, MatProgressSpinnerModule, MatRadioModule, MatRippleModule, MatSelectModule, MatSidenavModule, MatSliderModule, MatSlideToggleModule, MatSnackBarModule, MatSortModule, MatTableModule, MatTabsModule, MatToolbarModule, MatTooltipModule} from '@angular/material';
import { FlexLayoutModule } from '@angular/flex-layout';
import { CustomDirective } from '../../shared/services/custom.directive';

import { NgxDatatableModule } from '@swimlane/ngx-datatable';
import { CommonService } from '../../shared/common/common.service';
import { routes } from './block-transaction.routing';
import { BlockTransactionListComponent } from './components/block-transaction-list/block-transaction-list.component';
import { BlockTransactionService } from './block-transaction.service';
import { CommonUtils } from '../../shared/common/common-utils';
import { BlockTransactionDetailComponent } from './components/block-transaction-detail/block-transaction-detail.component';


@NgModule({
  imports: [
    CommonModule,
    RouterModule.forChild(routes),
    MatAutocompleteModule, MatButtonModule, MatButtonToggleModule, MatCardModule, MatCheckboxModule, MatChipsModule, MatStepperModule, MatDatepickerModule, MatDialogModule, MatExpansionModule, MatGridListModule, MatIconModule, MatInputModule, MatListModule, MatMenuModule, MatNativeDateModule, MatPaginatorModule, MatProgressBarModule, MatProgressSpinnerModule, MatRadioModule, MatRippleModule, MatSelectModule, MatSidenavModule, MatSliderModule, MatSlideToggleModule, MatSnackBarModule, MatSortModule, MatTableModule, MatTabsModule, MatToolbarModule, MatTooltipModule,
    NgxDatatableModule,
    FormsModule,
    FlexLayoutModule,CustomDirective
  ],
    entryComponents: [BlockTransactionListComponent,BlockTransactionDetailComponent],

  declarations: [ 
    
    BlockTransactionListComponent,
    BlockTransactionDetailComponent
    ],
  providers: [BlockTransactionService,CommonService,CommonUtils]
})

export class BlockTransactionModule {
  public static routes = routes;
}