import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms'; 
import { MatAutocompleteModule, MatButtonModule, MatButtonToggleModule, MatCardModule, MatCheckboxModule, MatChipsModule, MatStepperModule, MatDatepickerModule, MatDialogModule, MatExpansionModule, MatGridListModule, MatIconModule, MatInputModule, MatListModule, MatMenuModule, MatNativeDateModule, MatPaginatorModule, MatProgressBarModule, MatProgressSpinnerModule, MatRadioModule, MatRippleModule, MatSelectModule, MatSidenavModule, MatSliderModule, MatSlideToggleModule, MatSnackBarModule, MatSortModule, MatTableModule, MatTabsModule, MatToolbarModule, MatTooltipModule} from '@angular/material';
import { FlexLayoutModule } from '@angular/flex-layout';
import { NgxDatatableModule } from '@swimlane/ngx-datatable';

import { routes } from './power-sale.routing';

import { CompanyService } from './../../master/company/services/company.service';
import { CommonUtils} from '../../shared/common/common-utils';
import { CustomDirective } from '../../shared/services/custom.directive';
import {PsFossilFuelCaptiveComponent } from './components/power-sale-fossil-fuel-captive/ps-fossil-fuel-captive.component';
import {PsIexGeneratorComponent } from './components/power-sale-iex-gen/ps-iex-generator.component';
import {PsListComponent } from './components/power-sale-list/ps-list.component';
import {PsNcesCaptiveComponent } from './components/power-sale-nces-captive/ps-nces-captive.component';

@NgModule({
  imports: [
    CommonModule,
    RouterModule.forChild(routes),
    MatAutocompleteModule, MatButtonModule, MatButtonToggleModule, MatCardModule, MatCheckboxModule, MatChipsModule, MatStepperModule, MatDatepickerModule, MatDialogModule, MatExpansionModule, MatGridListModule, MatIconModule, MatInputModule, MatListModule, MatMenuModule, MatNativeDateModule, MatPaginatorModule, MatProgressBarModule, MatProgressSpinnerModule, MatRadioModule, MatRippleModule, MatSelectModule, MatSidenavModule, MatSliderModule, MatSlideToggleModule, MatSnackBarModule, MatSortModule, MatTableModule, MatTabsModule, MatToolbarModule, MatTooltipModule,
    NgxDatatableModule,
    FormsModule,
    FlexLayoutModule,CustomDirective
  ],
  entryComponents: [PsListComponent,PsFossilFuelCaptiveComponent,PsIexGeneratorComponent,PsNcesCaptiveComponent],
  declarations: [ PsListComponent,PsFossilFuelCaptiveComponent,PsIexGeneratorComponent,PsNcesCaptiveComponent
 ],
  providers: [ CompanyService,CommonUtils]
})

export class PowerSaleModule {
  public static routes = routes;
}
