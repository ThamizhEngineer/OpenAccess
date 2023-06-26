import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms'; 
import { MatAutocompleteModule, MatButtonModule, MatButtonToggleModule, MatCardModule, MatCheckboxModule, MatChipsModule, MatStepperModule, MatDatepickerModule, MatDialogModule, MatExpansionModule, MatGridListModule, MatIconModule, MatInputModule, MatListModule, MatMenuModule, MatNativeDateModule, MatPaginatorModule, MatProgressBarModule, MatProgressSpinnerModule, MatRadioModule, MatRippleModule, MatSelectModule, MatSidenavModule, MatSliderModule, MatSlideToggleModule, MatSnackBarModule, MatSortModule, MatTableModule, MatTabsModule, MatToolbarModule, MatTooltipModule} from '@angular/material';
import { FlexLayoutModule } from '@angular/flex-layout';
import { NgxDatatableModule } from '@swimlane/ngx-datatable';

import { routes } from './ea.routing';
import { EaService } from './services/ea.service'; 
import { CommonService } from '../../shared/common/common.service';
import { GsService } from './../generation-statement/services/gs.service';
import { CompanyService } from './../../master/company/services/company.service';
import { CommonUtils} from '../../shared/common/common-utils';
import { TradeRelationshipService } from './../../master/trade-relationship/services/trade-relationship.service';
import { HotTableModule } from 'angular-handsontable';
import { EaListComponent } from './components/ea-list/ea-list.component';
import {FormBuilder, FormGroup, Validators,ReactiveFormsModule} from '@angular/forms';
import { EaDetailComponent } from './components/ea-detail/ea-detail.component';
import {BulkDialogComponent} from './components/ea-detail/ea-bulk.component';
import { EaDialogComponent } from './components/ea-dialog/ea-dialog.component';
import { EaDetailHelper } from './components/ea-detail/ea-detail-helper';
import { EaProcessComponent } from './components/ea-process/ea-process.component';

@NgModule({
  imports: [
    CommonModule,
    RouterModule.forChild(routes),
    MatAutocompleteModule, MatButtonModule, MatButtonToggleModule, MatCardModule, MatCheckboxModule, MatChipsModule, MatStepperModule, MatDatepickerModule, MatDialogModule, MatExpansionModule, MatGridListModule, MatIconModule, MatInputModule, MatListModule, MatMenuModule, MatNativeDateModule, MatPaginatorModule, MatProgressBarModule, MatProgressSpinnerModule, MatRadioModule, MatRippleModule, MatSelectModule, MatSidenavModule, MatSliderModule, MatSlideToggleModule, MatSnackBarModule, MatSortModule, MatTableModule, MatTabsModule, MatToolbarModule, MatTooltipModule,
    NgxDatatableModule,
    FormsModule,
    FlexLayoutModule,
    HotTableModule,
    ReactiveFormsModule
  ],
  entryComponents: [BulkDialogComponent,EaDialogComponent],
  declarations: [ 
    EaListComponent,
    EaDetailComponent,BulkDialogComponent,EaDialogComponent,EaProcessComponent
    ],
  providers: [ CommonService,CompanyService,EaService,CommonUtils,TradeRelationshipService , GsService,EaDetailHelper]
})

export class EaModule {
  public static routes = routes;
}
