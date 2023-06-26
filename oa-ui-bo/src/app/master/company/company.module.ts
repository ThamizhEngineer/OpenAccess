import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms'; 
import { MatAutocompleteModule, MatButtonModule, MatButtonToggleModule, MatCardModule, MatCheckboxModule, MatChipsModule, MatStepperModule, MatDatepickerModule, MatDialogModule, MatExpansionModule, MatGridListModule, MatIconModule, MatInputModule, MatListModule, MatMenuModule, MatNativeDateModule, MatPaginatorModule, MatProgressBarModule, MatProgressSpinnerModule, MatRadioModule, MatRippleModule, MatSelectModule, MatSidenavModule, MatSliderModule, MatSlideToggleModule, MatSnackBarModule, MatSortModule, MatTableModule, MatTabsModule, MatToolbarModule, MatTooltipModule} from '@angular/material';

import { FlexLayoutModule } from '@angular/flex-layout';

import { NgxDatatableModule } from '@swimlane/ngx-datatable';

import { CompanyListComponent } from './components/company-list/company-list.component';
import { CompanyDetailComponent } from './components/company-detail/company-detail.component';
import { CompanyAddressesComponent } from './components/company-addresses/company-addresses.component';
import { CompanyContactsComponent } from './components/company-contacts/company-contacts.component';
import { CompanyEmployeesComponent } from './components/company-employees/company-employees.component';
import { CompanyShareHoldersComponent } from './components/company-share-holders/company-share-holders.component';
import { CompanyServicesComponent } from './components/company-services/company-services.component';
import { CompanyMetersComponent } from './components/company-meters/company-meters.component';
import { routes } from './company.routing';
import { CompanyService } from './services/company.service'; 
import { CompanyServiceV1 } from './services/company.service.v1'; 
import { CommonService } from '../../shared/common/common.service';
import { CommonUtils} from '../../shared/common/common-utils';

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
    CompanyListComponent,
    CompanyDetailComponent, 
    CompanyAddressesComponent, 
    CompanyContactsComponent, 
    CompanyEmployeesComponent, 
    CompanyShareHoldersComponent,
    CompanyServicesComponent,
    CompanyMetersComponent,
  
    ],
  providers: [ CommonService, CompanyService,CommonUtils, CompanyServiceV1 ]
})

export class CompanyModule {
  public static routes = routes;
}
