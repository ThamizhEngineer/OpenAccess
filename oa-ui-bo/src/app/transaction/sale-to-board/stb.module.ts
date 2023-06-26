import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms'; 
import { MatAutocompleteModule, MatButtonModule, MatButtonToggleModule, MatCardModule, MatCheckboxModule, MatChipsModule, MatStepperModule, MatDatepickerModule, MatDialogModule, MatExpansionModule, MatGridListModule, MatIconModule, MatInputModule, MatListModule, MatMenuModule, MatNativeDateModule, MatPaginatorModule, MatProgressBarModule, MatProgressSpinnerModule, MatRadioModule, MatRippleModule, MatSelectModule, MatSidenavModule, MatSliderModule, MatSlideToggleModule, MatSnackBarModule, MatSortModule, MatTableModule, MatTabsModule, MatToolbarModule, MatTooltipModule} from '@angular/material';
import { FlexLayoutModule } from '@angular/flex-layout';
import { NgxDatatableModule } from '@swimlane/ngx-datatable';

import { routes } from './stb.routing';
import { StbEvent } from './services/stb.event'; 
import { StbService } from './services/stb.service'; 
import { CommonService } from '../../shared/common/common.service';
import { StbListComponent } from './components/stb-list/stb-list.component';
import { StbDetailComponent } from './components/stb-detail/stb-detail.component';
import { StbPrintComponent } from './components/stb-print/stb-print.component';
// import { GsBlockviewComponent } from './components/gs-blockview/gs-blockview.component';


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
     StbListComponent,
    StbDetailComponent,
StbPrintComponent
    // GsSlotviewComponent,
    // GsBlockviewComponent 
    ],
  providers: [ CommonService,
    StbService,StbEvent
    
]
})

export class StbModule {
  public static routes = routes;
}
