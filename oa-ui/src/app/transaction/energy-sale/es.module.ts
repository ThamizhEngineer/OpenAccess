import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms'; 
import { MatAutocompleteModule, MatButtonModule, MatButtonToggleModule, MatCardModule, MatCheckboxModule, MatChipsModule, MatStepperModule, MatDatepickerModule, MatDialogModule, MatExpansionModule, MatGridListModule, MatIconModule, MatInputModule, MatListModule, MatMenuModule, MatNativeDateModule, MatPaginatorModule, MatProgressBarModule, MatProgressSpinnerModule, MatRadioModule, MatRippleModule, MatSelectModule, MatSidenavModule, MatSliderModule, MatSlideToggleModule, MatSnackBarModule, MatSortModule, MatTableModule, MatTabsModule, MatToolbarModule, MatTooltipModule} from '@angular/material';
import { FlexLayoutModule } from '@angular/flex-layout';
import { NgxDatatableModule } from '@swimlane/ngx-datatable';

import { routes } from './es.routing';
import { EsEvent } from './services/es.event'; 
import { EsService } from './services/es.service'; 
import { CommonService } from '../../shared/common/common.service';
import { EsListComponent } from './components/es-list/es-list.component';
import { EsAddComponent } from './components/es-add/es-add.component';
import { GsService } from './../generation-statement/services/gs.service';
import { EsDetailComponent, BulkDialogComponent } from './components/es-detail/es-detail.component';
import { EsDialogComponent } from './components/es-dialog/es-dialog.component';
import { CompanyService } from './../../master/company/services/company.service';
import { CommonUtils} from '../../shared/common/common-utils';
import { TradeRelationshipService } from './../../master/trade-relationship/services/trade-relationship.service';
import { HotTableModule } from 'angular-handsontable';
import { EsDetailAllocationHelper } from './components/es-detail/es-detail-allocation-helper';
@NgModule({
  imports: [
    CommonModule,
    RouterModule.forChild(routes),
    MatAutocompleteModule, MatButtonModule, MatButtonToggleModule, MatCardModule, MatCheckboxModule, MatChipsModule, MatStepperModule, MatDatepickerModule, MatDialogModule, MatExpansionModule, MatGridListModule, MatIconModule, MatInputModule, MatListModule, MatMenuModule, MatNativeDateModule, MatPaginatorModule, MatProgressBarModule, MatProgressSpinnerModule, MatRadioModule, MatRippleModule, MatSelectModule, MatSidenavModule, MatSliderModule, MatSlideToggleModule, MatSnackBarModule, MatSortModule, MatTableModule, MatTabsModule, MatToolbarModule, MatTooltipModule,
    NgxDatatableModule,
    FormsModule,
    FlexLayoutModule,
	HotTableModule
  ],
  entryComponents: [EsAddComponent, BulkDialogComponent,EsDialogComponent],
  declarations: [ 
    EsListComponent, EsAddComponent, EsDetailComponent, BulkDialogComponent,EsDialogComponent
    ],
  providers: [ CommonService,CompanyService,EsService,CommonUtils,TradeRelationshipService , GsService, EsEvent,EsDetailAllocationHelper]
})

export class EsModule {
  public static routes = routes;
}
