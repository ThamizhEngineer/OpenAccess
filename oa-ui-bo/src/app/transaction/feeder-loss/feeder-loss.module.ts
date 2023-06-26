import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms'; 
import { MatAutocompleteModule, MatButtonModule, MatButtonToggleModule, MatCardModule, MatCheckboxModule, MatChipsModule, MatStepperModule, MatDatepickerModule, MatDialogModule, MatExpansionModule, MatGridListModule, MatIconModule, MatInputModule, MatListModule, MatMenuModule, MatNativeDateModule, MatPaginatorModule, MatProgressBarModule, MatProgressSpinnerModule, MatRadioModule, MatRippleModule, MatSelectModule, MatSidenavModule, MatSliderModule, MatSlideToggleModule, MatSnackBarModule, MatSortModule, MatTableModule, MatTabsModule, MatToolbarModule, MatTooltipModule} from '@angular/material';
import { FlexLayoutModule } from '@angular/flex-layout';

import { NgxDatatableModule } from '@swimlane/ngx-datatable';
import { CommonService } from '../../shared/common/common.service';
import { CustomDirective } from '../../shared/services/custom.directive';
import { CommonUtils } from '../../shared/common/common-utils';
import { routes } from './feeder-loss.routing';
import { FeederLossComponent } from './component/feeder-loss.component';
import { FeederLossService } from './service/feeder-loss.service';
import { FeederLossViewComponent } from './component/feeder-loss-view.component';
import { FeederLossDialogComponent } from './component/feeder-loss-dialog.component';
import { FeederLossOptionsComponent } from './component/feeder-loss-options.component';


@NgModule({
  imports: [
    CommonModule,
    RouterModule.forChild(routes),
    MatAutocompleteModule, MatButtonModule, MatButtonToggleModule, MatCardModule, MatCheckboxModule, MatChipsModule, MatStepperModule, MatDatepickerModule, MatDialogModule, MatExpansionModule, MatGridListModule, MatIconModule, MatInputModule, MatListModule, MatMenuModule, MatNativeDateModule, MatPaginatorModule, MatProgressBarModule, MatProgressSpinnerModule, MatRadioModule, MatRippleModule, MatSelectModule, MatSidenavModule, MatSliderModule, MatSlideToggleModule, MatSnackBarModule, MatSortModule, MatTableModule, MatTabsModule, MatToolbarModule, MatTooltipModule,
    NgxDatatableModule,
    FormsModule,
    FlexLayoutModule,CustomDirective
  ],
  entryComponents: [],

  declarations: [ 
    FeederLossComponent,
    FeederLossViewComponent,
    FeederLossDialogComponent,
    FeederLossOptionsComponent
    ],
  providers: [FeederLossService,CommonService,CommonUtils]
})

export class FeederLossModule {

}
