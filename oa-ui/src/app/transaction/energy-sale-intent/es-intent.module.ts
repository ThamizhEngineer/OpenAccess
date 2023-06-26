import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms'; 
import { MatAutocompleteModule, MatButtonModule, MatButtonToggleModule, MatCardModule, MatCheckboxModule, MatChipsModule, MatStepperModule, MatDatepickerModule, MatDialogModule, MatExpansionModule, MatGridListModule, MatIconModule, MatInputModule, MatListModule, MatMenuModule, MatNativeDateModule, MatPaginatorModule, MatProgressBarModule, MatProgressSpinnerModule, MatRadioModule, MatRippleModule, MatSelectModule, MatSidenavModule, MatSliderModule, MatSlideToggleModule, MatSnackBarModule, MatSortModule, MatTableModule, MatTabsModule, MatToolbarModule, MatTooltipModule} from '@angular/material';
import { FlexLayoutModule } from '@angular/flex-layout';
import { NgxDatatableModule } from '@swimlane/ngx-datatable';

import { routes } from './es-intent.routing';

import { EsIntentService } from './services/es-intent.service'; 
import { CommonService } from '../../shared/common/common.service';
import {EsIntentListComponent,EsIntentFlowTypeComponent } from './components/es-intent-list/es-intent-list.component';
import {EsIntentIexConsumerComponent } from './components/es-intent-iex-consumer/es-intent-iex-consumer.component';
import {EsIntentIexGeneratorComponent } from './components/es-intent-iex-generator/es-intent-iex-generator.component';
import {EsIntentStbComponent } from './components/es-intent-stb/es-intent-stb.component';
import {EsIntentOaaListComponent } from './components/es-intent-oaa-list/es-intent-oaa-list.component';
import {EsIntentOaaDetailComponent } from './components/es-intent-oaa-detail/es-intent-oaa-detail.component';
import { EsIntentDetailComponent } from './components/es-intent-detail/es-intent-detail.component';
import { CompanyService } from './../../master/company/services/company.service';
import { CommonUtils} from '../../shared/common/common-utils';
import { CustomDirective } from '../../shared/services/custom.directive';

@NgModule({
  imports: [
    CommonModule,
    RouterModule.forChild(routes),
    MatAutocompleteModule, MatButtonModule, MatButtonToggleModule, MatCardModule, MatCheckboxModule, MatChipsModule, MatStepperModule, MatDatepickerModule, MatDialogModule, MatExpansionModule, MatGridListModule, MatIconModule, MatInputModule, MatListModule, MatMenuModule, MatNativeDateModule, MatPaginatorModule, MatProgressBarModule, MatProgressSpinnerModule, MatRadioModule, MatRippleModule, MatSelectModule, MatSidenavModule, MatSliderModule, MatSlideToggleModule, MatSnackBarModule, MatSortModule, MatTableModule, MatTabsModule, MatToolbarModule, MatTooltipModule,
    NgxDatatableModule,
    FormsModule,
    FlexLayoutModule,CustomDirective
  ],
  entryComponents: [EsIntentListComponent, EsIntentFlowTypeComponent],
  declarations: [ 
    EsIntentListComponent,EsIntentDetailComponent,EsIntentFlowTypeComponent,EsIntentIexConsumerComponent,EsIntentStbComponent,EsIntentIexGeneratorComponent,EsIntentOaaListComponent,EsIntentOaaDetailComponent
    ],
  providers: [ CommonService,CompanyService,CommonUtils,EsIntentService]
})

export class EsIntentModule {
  public static routes = routes;
}
