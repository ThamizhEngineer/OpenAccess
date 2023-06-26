import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms'; 
import { MatAutocompleteModule, MatButtonModule, MatButtonToggleModule, MatCardModule, MatCheckboxModule, MatChipsModule, MatStepperModule, MatDatepickerModule, MatDialogModule, MatExpansionModule, MatGridListModule, MatIconModule, MatInputModule, MatListModule, MatMenuModule, MatNativeDateModule, MatPaginatorModule, MatProgressBarModule, MatProgressSpinnerModule, MatRadioModule, MatRippleModule, MatSelectModule, MatSidenavModule, MatSliderModule, MatSlideToggleModule, MatSnackBarModule, MatSortModule, MatTableModule, MatTabsModule, MatToolbarModule, MatTooltipModule} from '@angular/material';
import { FlexLayoutModule } from '@angular/flex-layout';

import { NgxDatatableModule } from '@swimlane/ngx-datatable';
import { CommonService } from '../../shared/common/common.service';
import{TenOneSSLossService} from './services/ten-one-ss-loss.service'
import { TenOneSSLossList } from '../ten-one-ss-loss/components/ten-one-ss-loss-list.component';
import{TenOneSSLossAdd} from '../ten-one-ss-loss/components/ten-one-ss-add/ten-one-ss-loss-add.component';
import{TenOneSSLossDetail} from '../ten-one-ss-loss/components/ten-one-ss-detail/ten-one-ss-loss-detail.component';
import { TenOneSSLossView } from './components/ten-one-ss-view/ten-one-ss-loss-view.component';
import { TenOneSSLossSub } from './components/ten-one-ss-sub/ten-one-ss-loss-sub.component';
import { CustomDirective } from '../../shared/services/custom.directive';
import { routes } from './ten-one-ss.loss.routing'; 
import { CommonUtils } from '../../shared/common/common-utils';


@NgModule({
  imports: [
    CommonModule,
    RouterModule.forChild(routes),
    MatAutocompleteModule, MatButtonModule, MatButtonToggleModule, MatCardModule, MatCheckboxModule, MatChipsModule, MatStepperModule, MatDatepickerModule, MatDialogModule, MatExpansionModule, MatGridListModule, MatIconModule, MatInputModule, MatListModule, MatMenuModule, MatNativeDateModule, MatPaginatorModule, MatProgressBarModule, MatProgressSpinnerModule, MatRadioModule, MatRippleModule, MatSelectModule, MatSidenavModule, MatSliderModule, MatSlideToggleModule, MatSnackBarModule, MatSortModule, MatTableModule, MatTabsModule, MatToolbarModule, MatTooltipModule,
    NgxDatatableModule,
    FormsModule,
    FlexLayoutModule,CustomDirective
  ],
  entryComponents: [TenOneSSLossAdd,],

  declarations: [ 
    TenOneSSLossList ,
    TenOneSSLossAdd,
    TenOneSSLossDetail,
    TenOneSSLossView,
    TenOneSSLossSub
    ],
  providers: [TenOneSSLossService,CommonService,CommonUtils]
})

export class TenOneSSLossModule {

}
