
import 'rxjs/add/operator/switchMap';
import { Component, OnInit, HostBinding } from '@angular/core';
import { Router, ActivatedRoute, Params } from '@angular/router';
import { Agreement } from './../../../vo/agreement';
import { Consent } from './../../../vo/consent';
import { AgreementService } from './../../services/agreement.service';
import { CommonService } from './../../../../shared/common/common.service';

import { DatePipe } from '@angular/common';
import { CommonUtils } from '../../../../shared/common/common-utils';
import { MatAutocompleteModule, MatButtonModule, MatButtonToggleModule, MatCardModule, MatCheckboxModule, MatChipsModule, MatStepperModule, MatDatepickerModule, MatDialogModule, MatExpansionModule, MatGridListModule, MatIconModule, MatInputModule, MatListModule, MatMenuModule, MatNativeDateModule, MatPaginatorModule, MatProgressBarModule, MatProgressSpinnerModule, MatRadioModule, MatRippleModule, MatSelectModule, MatSidenavModule, MatSliderModule, MatSlideToggleModule, MatSnackBarModule, MatSortModule, MatTableModule, MatTabsModule, MatToolbarModule, MatTooltipModule } from '@angular/material';
import { MatDialog, MatDialogConfig, MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';
import { AgreementCompleteComponent } from '../../components/agreement-complete/agreement-complete.component';


@Component({
  selector: 'agreement-consent',
  templateUrl: './agreement-consent.component.html',
  //   styleUrls: ['./gs-list.component.scss'],
  providers: [DatePipe, CommonUtils]
})
export class AgreementConsentComponent implements OnInit {
  agreement: Agreement;
  consent: Consent;
  rows: Array<Consent>;
  agmtDate: string;
  dialogRef: MatDialogRef<AgreementCompleteComponent>;
  config: MatDialogConfig = {
    disableClose: false
  };

  constructor(
    private commonUtils: CommonUtils,
    private route: ActivatedRoute,
    private router: Router,
    private commonService: CommonService,
    private service: AgreementService,
    private datePipe: DatePipe,
    public dialog: MatDialog


  ) {
    console.log("In consent component")
  }

  ngOnInit() {
    this.consent = new Consent();
    this.agreement = new Agreement();
    console.log("In consent component");
    this.service.getAllConsents("APPROVED").subscribe(res => {
      this.rows = res;
      console.log(this.rows);
    });
  }

  completeConsent(id: string, buyerOrgName: string, buyerServiceNum: string, buyerCompName: string, sellerCompName: string, agmtPeriodCode: string, fromDate: string, toDate: string) {
    console.log(id, buyerOrgName, buyerServiceNum, buyerCompName, sellerCompName, agmtPeriodCode, fromDate, toDate)

    this.agreement.id = id;
    this.agreement.buyerOrgName = buyerOrgName;
    this.agreement.buyerCompanyServiceNumber = buyerServiceNum;
    this.agreement.buyerCompanyName = buyerCompName;
    this.agreement.sellerCompanyName = sellerCompName;
    this.agreement.agreementPeriodCode = agmtPeriodCode;
    this.agreement.fromDate = fromDate;
    this.agreement.toDate = toDate;
    this.agreement.type = "consent";
    try {
      this.config.data = { "agreement": this.agreement };
      this.dialogRef = this.dialog.open(AgreementCompleteComponent, this.config);
    }
    catch (e) {
      console.log(e);
    }
  }






}
