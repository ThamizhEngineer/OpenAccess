import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms'; 
import { MatAutocompleteModule, MatButtonModule, MatButtonToggleModule, MatCardModule, MatCheckboxModule, MatChipsModule, MatStepperModule, MatDatepickerModule, MatDialogModule, MatExpansionModule, MatGridListModule, MatIconModule, MatInputModule, MatListModule, MatMenuModule, MatNativeDateModule, MatPaginatorModule, MatProgressBarModule, MatProgressSpinnerModule, MatRadioModule, MatRippleModule, MatSelectModule, MatSidenavModule, MatSliderModule, MatSlideToggleModule, MatSnackBarModule, MatSortModule, MatTableModule, MatTabsModule, MatToolbarModule, MatTooltipModule} from '@angular/material';
import { FlexLayoutModule } from '@angular/flex-layout';

import { NgxDatatableModule } from '@swimlane/ngx-datatable';
// import { MeterReadingDetailComponent } from './components/meter-reading-detail/meter-reading-detail.component';
import { CommonService } from '../../shared/common/common.service';

import {GenOtherChargesDetailComponent } from './components/gen-other-charges-detail/gen-other-charges-detail.component';
import { GenOtherChargesListComponent } from './components/gen-other-charges-list/gen-other-charges-list.component';


import { routes } from './gen-other-charges.routing'; 
import { GenOtherChargesService } from './services/gen-other-charges.service'; 
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
    entryComponents: [GenOtherChargesListComponent,GenOtherChargesDetailComponent],

  declarations: [ 
    GenOtherChargesListComponent,
    GenOtherChargesDetailComponent
    ],
  providers: [GenOtherChargesService,CommonService,CommonUtils]
})

export class GenOtherChargesModule {
  public static routes = routes;
}
