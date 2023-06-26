import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms'; 
import { MatAutocompleteModule, MatButtonModule, MatButtonToggleModule, MatCardModule, MatCheckboxModule, MatChipsModule, MatStepperModule, MatDatepickerModule, MatDialogModule, MatExpansionModule, MatGridListModule, MatIconModule, MatInputModule, MatListModule, MatMenuModule, MatNativeDateModule, MatPaginatorModule, MatProgressBarModule, MatProgressSpinnerModule, MatRadioModule, MatRippleModule, MatSelectModule, MatSidenavModule, MatSliderModule, MatSlideToggleModule, MatSnackBarModule, MatSortModule, MatTableModule, MatTabsModule, MatToolbarModule, MatTooltipModule} from '@angular/material';
import { FlexLayoutModule } from '@angular/flex-layout';
import { NgxDatatableModule } from '@swimlane/ngx-datatable';
import { routes } from './gsc.routing';
import { GsEvent } from './services/gs.event'; 
import { GsService } from './services/gs.service'; 
import { CommonService } from '../../shared/common/common.service';
// import { GsListComponent } from './components/gs-list/gs-list.component';
// import { GsDetailComponent } from './components/gs-detail/gs-detail.component';
// import { GsSlotviewComponent } from './components/gs-slotview/gs-slotview.component';
// import { GsBlockviewComponent } from './components/gs-blockview/gs-blockview.component';
import { CompanyServiceV1 } from '../../master/company/services/company.service.v1'; 

import { CommonUtils} from '../../shared/common/common-utils';
import { GsConsumerListComponent } from './components/gs-consumer-list/gs-consumer-list.component';
import { GscDetailComponent } from './components/gs-consumer-detail/gs-consumer-detail.component';

@NgModule({
  imports: [
    CommonModule,
    RouterModule.forChild(routes),
    MatAutocompleteModule, MatButtonModule, MatButtonToggleModule, MatCardModule, MatCheckboxModule, MatChipsModule, MatStepperModule, MatDatepickerModule, MatDialogModule, MatExpansionModule, MatGridListModule, MatIconModule, MatInputModule, MatListModule, MatMenuModule, MatNativeDateModule, MatPaginatorModule, MatProgressBarModule, MatProgressSpinnerModule, MatRadioModule, MatRippleModule, MatSelectModule, MatSidenavModule, MatSliderModule, MatSlideToggleModule, MatSnackBarModule, MatSortModule, MatTableModule, MatTabsModule, MatToolbarModule, MatTooltipModule,
    NgxDatatableModule,
    FormsModule,
    FlexLayoutModule
  ],
  declarations: [ 
    GsConsumerListComponent,
    // GsListComponent,
    GscDetailComponent
    // GsSlotviewComponent,
    // GsBlockviewComponent 
    ],
  providers: [ CommonService,GsService,CommonUtils, GsEvent,CompanyServiceV1]
})

export class GscModule {
  public static routes = routes;
}
