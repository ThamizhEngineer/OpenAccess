import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MatAutocompleteModule, MatButtonModule, MatCardModule, MatCheckboxModule, MatDatepickerModule, MatNativeDateModule,  MatDialogModule, MatIconModule, MatInputModule, MatRadioModule, MatSelectModule, MatTabsModule } from '@angular/material';

import { FlexLayoutModule } from '@angular/flex-layout';

import { NgxDatatableModule } from '@swimlane/ngx-datatable';

import { GcListComponent } from './components/gc-list/gc-list.component';
import { GcDetailComponent } from './components/gc-detail/gc-detail.component';
import { GcGeneratorUnitsComponent } from './components/gc-gen-units/gc-gen-units.component';
import { GcTransformersComponent } from './components/gc-transformers/gc-transformers.component';
import { GcInvestmentDetailComponent } from './components/gc-investment-detail/gc-investment-detail.component';
import { GcQuantumAllocationComponent } from './components/gc-quantum-allocation/gc-quantum-allocation.component';
import { GcChecklistComponent } from './components/gc-checklist/gc-checklist.component';
import { GcAddComponent } from './components/gc-add/gc-add.component';
import { GcServiceProvisioningComponent } from './components/gc-service-provisioning/gc-service-provisioning.component';
import { GcInfraComponent } from './components/gc-infra/gc-infra.component';
import { GcTariffComponent } from './components/gc-tariff/gc-tariff.component';
import { GcStatusComponent } from './components/gc-status/gc-status.component';

import { routes } from './gc.routing';

import { GcService } from './services/gc.service';
import { CommonService } from '../shared/common/common.service';
import { CompanyService } from './../master/company/services/company.service';

@NgModule({
  imports: [
    CommonModule,
    RouterModule.forChild(routes),
    MatAutocompleteModule, MatButtonModule, MatCardModule, MatCheckboxModule, MatDatepickerModule, MatNativeDateModule, MatDialogModule, MatIconModule, MatInputModule, MatRadioModule, MatSelectModule, MatTabsModule,
    NgxDatatableModule,
    FormsModule,
    ReactiveFormsModule,
    FlexLayoutModule
  ],
  declarations: [
    GcListComponent,
    GcDetailComponent,
    GcGeneratorUnitsComponent,
    GcTransformersComponent,
    GcInvestmentDetailComponent,
    GcChecklistComponent,
    GcServiceProvisioningComponent,
    GcInfraComponent,
    GcTariffComponent, GcQuantumAllocationComponent,
	GcStatusComponent,
    GcAddComponent],
  entryComponents: [GcAddComponent],
  providers: [CommonService, CompanyService, GcService,GcInvestmentDetailComponent, GcQuantumAllocationComponent]
})

export class GcModule {
  public static routes = routes;
}
