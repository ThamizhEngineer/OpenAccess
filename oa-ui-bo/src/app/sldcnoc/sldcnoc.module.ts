import { NgModule,CUSTOM_ELEMENTS_SCHEMA  } from '@angular/core';
import { RouterModule } from '@angular/router';
import { CommonModule, DatePipe } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { MatAutocompleteModule, MatButtonModule, MatButtonToggleModule, MatCardModule, MatCheckboxModule, MatChipsModule, MatStepperModule, MatDatepickerModule, MatDialogModule, MatExpansionModule, MatGridListModule, MatIconModule, MatInputModule, MatListModule, MatMenuModule, MatNativeDateModule, MatPaginatorModule, MatProgressBarModule, MatProgressSpinnerModule, MatRadioModule, MatRippleModule, MatSelectModule, MatSidenavModule, MatSliderModule, MatSlideToggleModule, MatSnackBarModule, MatSortModule, MatTableModule, MatTabsModule, MatToolbarModule, MatTooltipModule } from '@angular/material';
import { FlexLayoutModule } from '@angular/flex-layout';
import { NgxDatatableModule } from '@swimlane/ngx-datatable';
import { sldcroutes } from './sldcnoc.routing';
import { CustomDirective } from '../shared/services/custom.directive';
import { GsService } from '../transaction/generation-statement/services/gs.service';
import { CommonUtils } from '../shared/common/common-utils';
import { SldcNocComponent } from './components/sldc-noc/sldc-noc.component';
import { SldcNocFormComponent } from './components/sldc-noc-form/sldc-noc-form.component';
import { NocApprovalDirectorList } from './components/noc-approval-director/noc-approval-director-list.component';
import { NocApprovalDirectorDetail } from './components/noc-approval-director/noc-approval-director-detail.component';
import { NocApprovalCeList } from './components/noc-approval-ce/noc-approval-ce-list.component';
import { NocApprovalCeDetail } from './components/noc-approval-ce/noc-approval-ce-detail.component';
import { SamastImportComponent } from './components/samast-import/samast-import.component'
import { SdlcNocService } from './sldc-noc.service'
import { SamastApplnComponent } from './components/samast-appln-report/samast-appln.component';
import { NocApprovalList } from './components/noc-approval-list/noc-approval-list.component';

@NgModule({
    imports: [
        CommonModule,
        RouterModule.forChild(sldcroutes),
        MatAutocompleteModule, MatButtonModule, MatButtonToggleModule, MatCardModule, MatCheckboxModule, MatChipsModule, MatStepperModule, MatDatepickerModule, MatDialogModule, MatExpansionModule, MatGridListModule, MatIconModule, MatInputModule, MatListModule, MatMenuModule, MatNativeDateModule, MatPaginatorModule, MatProgressBarModule, MatProgressSpinnerModule, MatRadioModule, MatRippleModule, MatSelectModule, MatSidenavModule, MatSliderModule, MatSlideToggleModule, MatSnackBarModule, MatSortModule, MatTableModule, MatTabsModule, MatToolbarModule, MatTooltipModule,
        NgxDatatableModule,
        FormsModule,
        FlexLayoutModule,
        CustomDirective
      ],
      declarations: [SldcNocComponent,SldcNocFormComponent,SamastImportComponent,NocApprovalDirectorList,
        NocApprovalDirectorDetail,NocApprovalCeList,NocApprovalCeDetail,SamastApplnComponent,NocApprovalList],
      providers: [GsService,CommonUtils,SdlcNocService,DatePipe],
      schemas: [ CUSTOM_ELEMENTS_SCHEMA ]

})

export class SldcnocModule {
    public static routes = sldcroutes;
}