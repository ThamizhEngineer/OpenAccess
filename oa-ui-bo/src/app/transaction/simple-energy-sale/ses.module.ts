import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms'; 
import { MatAutocompleteModule, MatButtonModule, MatButtonToggleModule, MatCardModule, MatCheckboxModule, MatChipsModule, MatStepperModule, MatDatepickerModule, MatDialogModule, MatExpansionModule, MatGridListModule, MatIconModule, MatInputModule, MatListModule, MatMenuModule, MatNativeDateModule, MatPaginatorModule, MatProgressBarModule, MatProgressSpinnerModule, MatRadioModule, MatRippleModule, MatSelectModule, MatSidenavModule, MatSliderModule, MatSlideToggleModule, MatSnackBarModule, MatSortModule, MatTableModule, MatTabsModule, MatToolbarModule, MatTooltipModule} from '@angular/material';
import { FlexLayoutModule } from '@angular/flex-layout';
import { NgxDatatableModule } from '@swimlane/ngx-datatable';

import { routes } from './ses.routing';
import { EsEvent } from './../energy-sale/services/es.event'; 
import { EsService } from './../energy-sale/services/es.service'; 
import { CommonService } from '../../shared/common/common.service';
import { SesListComponent } from './components/ses-list/ses-list.component';
//import { SesAddComponent } from './components/ses-add/ses-add.component';
import { GsService } from './../generation-statement/services/gs.service';
import { SesDetailComponent } from './components/ses-detail/ses-detail.component';
import { CompanyService } from './../../master/company/services/company.service';
import { CommonUtils} from '../../shared/common/common-utils';
import { TradeRelationshipService } from './../../master/trade-relationship/services/trade-relationship.service';
import { CustomDirective } from '../../shared/services/custom.directive';
import { HotTableModule } from 'angular-handsontable';
@NgModule({
  imports: [
    CommonModule,
    RouterModule.forChild(routes),
    MatAutocompleteModule, MatButtonModule, MatButtonToggleModule, MatCardModule, MatCheckboxModule, MatChipsModule, MatStepperModule, MatDatepickerModule, MatDialogModule, MatExpansionModule, MatGridListModule, MatIconModule, MatInputModule, MatListModule, MatMenuModule, MatNativeDateModule, MatPaginatorModule, MatProgressBarModule, MatProgressSpinnerModule, MatRadioModule, MatRippleModule, MatSelectModule, MatSidenavModule, MatSliderModule, MatSlideToggleModule, MatSnackBarModule, MatSortModule, MatTableModule, MatTabsModule, MatToolbarModule, MatTooltipModule,
    NgxDatatableModule,
    FormsModule,
    FlexLayoutModule,
	CustomDirective,HotTableModule
  ],
 // entryComponents: [SesAddComponent],
  declarations: [ 
    SesListComponent, SesDetailComponent 
    ],
  providers: [ CommonService,CompanyService,EsService,CommonUtils,TradeRelationshipService , GsService, EsEvent]
})

export class SesModule {
  public static routes = routes;
}
