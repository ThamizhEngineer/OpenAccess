import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms'; 
import { MatAutocompleteModule, MatButtonModule, MatButtonToggleModule, MatCardModule, MatCheckboxModule, MatChipsModule, MatStepperModule, MatDatepickerModule, MatDialogModule, MatExpansionModule, MatGridListModule, MatIconModule, MatInputModule, MatListModule, MatMenuModule, MatNativeDateModule, MatPaginatorModule, MatProgressBarModule, MatProgressSpinnerModule, MatRadioModule, MatRippleModule, MatSelectModule, MatSidenavModule, MatSliderModule, MatSlideToggleModule, MatSnackBarModule, MatSortModule, MatTableModule, MatTabsModule, MatToolbarModule, MatTooltipModule} from '@angular/material';
import { FlexLayoutModule } from '@angular/flex-layout';
import { DatePipe } from '@angular/common';

import { NgxDatatableModule } from '@swimlane/ngx-datatable';

import { MeterReadingImportListComponent } from './components/meter-reading-import-list/meter-reading-import-list.component';
import { MeterReadingImportDetailComponent } from './components/meter-reading-import-detail/meter-reading-import-detail.component'; 
import { MeterReadingImportProcessComponent } from './components/meter-reading-import-process/meter-reading-import-process.component'; 
import { MeterReadingImportDisplayComponent } from './components/meter-reading-import-display/meter-reading-import-display.component'; 

import { routes } from './meter-reading-import.routing'; 
import { MeterReadingImportService } from './services/meter-reading-import.service'; 

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
    MeterReadingImportListComponent,
    MeterReadingImportDetailComponent,
    MeterReadingImportProcessComponent,MeterReadingImportDisplayComponent
    ],
  providers: [MeterReadingImportService,MatDatepickerModule,DatePipe]
})

export class MeterReadingImportModule {
  public static routes = routes;
}
