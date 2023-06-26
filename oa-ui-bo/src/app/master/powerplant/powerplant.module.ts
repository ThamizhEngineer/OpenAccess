import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms'; 
import { MatAutocompleteModule, MatButtonModule, MatCardModule, MatDatepickerModule, MatExpansionModule, MatIconModule, MatInputModule, MatSelectModule, MatListModule } from '@angular/material';
import { FlexLayoutModule } from '@angular/flex-layout';
import { CommonUtils} from '../../shared/common/common-utils';

import { NgxDatatableModule } from '@swimlane/ngx-datatable';

import { PowerplantListComponent } from './components/powerplant-list/powerplant-list.component';
import { PowerplantDetailComponent } from './components/powerplant-detail/powerplant-detail.component';
import { PowerplantViewComponent } from './components/powerplant-view/powerplant-view.component';
import { MatButtonToggleModule, MatCheckboxModule, MatChipsModule, MatStepperModule, MatDialogModule, MatGridListModule, MatMenuModule, MatNativeDateModule, MatPaginatorModule, MatProgressBarModule, MatProgressSpinnerModule, MatRadioModule, MatRippleModule, MatSidenavModule, MatSliderModule, MatSlideToggleModule, MatSnackBarModule, MatSortModule, MatTableModule, MatTabsModule, MatToolbarModule, MatTooltipModule} from '@angular/material';

import { routes } from './powerplant.routing';
//import { GeneratoranisationEvent } from './services/generator.event'; 
import { PowerplantService } from './services/powerplant.service'; 

@NgModule({
  imports: [
    CommonModule,
    RouterModule.forChild(routes),
    MatAutocompleteModule, MatButtonModule, MatButtonToggleModule, MatCardModule, MatCheckboxModule, MatChipsModule, MatStepperModule, MatDatepickerModule, MatDialogModule, MatExpansionModule, MatGridListModule, MatIconModule, MatInputModule, MatListModule, MatMenuModule, MatNativeDateModule, MatPaginatorModule, MatProgressBarModule, MatProgressSpinnerModule, MatRadioModule, MatRippleModule, MatSelectModule, MatSidenavModule, MatSliderModule, MatSlideToggleModule, MatSnackBarModule, MatSortModule, MatTableModule, MatTabsModule, MatToolbarModule, MatTooltipModule,
    MatAutocompleteModule, MatButtonModule, MatCardModule, MatDatepickerModule, MatExpansionModule, MatIconModule, MatInputModule, MatSelectModule, MatListModule,
    NgxDatatableModule,
    FormsModule,
    FlexLayoutModule
  ],
  declarations: [ 
    PowerplantListComponent,
    PowerplantDetailComponent,
	PowerplantViewComponent
    ],
  providers: [   PowerplantService,CommonUtils ]
})

export class PowerplantModule {
  public static routes = routes;
}
