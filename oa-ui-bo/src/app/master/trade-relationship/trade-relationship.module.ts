import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule,ReactiveFormsModule } from '@angular/forms'; 
import { MatAutocompleteModule, MatButtonModule, MatButtonToggleModule, MatCardModule, MatCheckboxModule, MatChipsModule, MatStepperModule, MatDatepickerModule, MatDialogModule, MatExpansionModule, MatGridListModule, MatIconModule, MatInputModule, MatListModule, MatMenuModule, MatNativeDateModule, MatPaginatorModule, MatProgressBarModule, MatProgressSpinnerModule, MatRadioModule, MatRippleModule, MatSelectModule, MatSidenavModule, MatSliderModule, MatSlideToggleModule, MatSnackBarModule, MatSortModule, MatTableModule, MatTabsModule, MatToolbarModule, MatTooltipModule} from '@angular/material';
import { FlexLayoutModule } from '@angular/flex-layout';

import { NgxDatatableModule } from '@swimlane/ngx-datatable';

import { TradeRelationshipListComponent } from './components/trade-relationship-list/trade-relationship-list.component';
import { TradeRelationshipDetailComponent } from './components/trade-relationship-detail/trade-relationship-detail.component';
import { CommonUtils} from '../../shared/common/common-utils';

import { routes } from './trade-relationship.routing';
//import { FeederanisationEvent } from './services/feeder.event'; 
import { TradeRelationshipService } from './services/trade-relationship.service';
//import { CommonService } from '../../shared/common/common.service';
//import { CustomDirective } from '../shared/services/custom.directive'; 

@NgModule({
  imports: [
    CommonModule,
    RouterModule.forChild(routes),
    MatAutocompleteModule, MatButtonModule, MatButtonToggleModule, MatCardModule, MatCheckboxModule, MatChipsModule, MatStepperModule, MatDatepickerModule, MatDialogModule, MatExpansionModule, MatGridListModule, MatIconModule, MatInputModule, MatListModule, MatMenuModule, MatNativeDateModule, MatPaginatorModule, MatProgressBarModule, MatProgressSpinnerModule, MatRadioModule, MatRippleModule, MatSelectModule, MatSidenavModule, MatSliderModule, MatSlideToggleModule, MatSnackBarModule, MatSortModule, MatTableModule, MatTabsModule, MatToolbarModule, MatTooltipModule,
    NgxDatatableModule,
    FormsModule, ReactiveFormsModule,
    FlexLayoutModule
  ],
  declarations: [ 
    TradeRelationshipListComponent ,TradeRelationshipDetailComponent
    ],
  providers: [   TradeRelationshipService,CommonUtils ]
})

export class TradeRelationshipModule {
  public static routes = routes;
}
