
import 'rxjs/add/operator/switchMap';
import { Component, OnInit, HostBinding } from '@angular/core';
import { Router, ActivatedRoute, Params } from '@angular/router';
import { Agreement } from './../../../vo/agreement';
import { Ewa } from './../../../vo/ewa';
import { AgreementService } from './../../services/agreement.service';
import { CommonService } from './../../../../shared/common/common.service';

import { DatePipe } from '@angular/common';
import { CommonUtils } from '../../../../shared/common/common-utils';
import { MatAutocompleteModule, MatButtonModule, MatButtonToggleModule, MatCardModule, MatCheckboxModule, MatChipsModule, MatStepperModule, MatDatepickerModule, MatDialogModule, MatExpansionModule, MatGridListModule, MatIconModule, MatInputModule, MatListModule, MatMenuModule, MatNativeDateModule, MatPaginatorModule, MatProgressBarModule, MatProgressSpinnerModule, MatRadioModule, MatRippleModule, MatSelectModule, MatSidenavModule, MatSliderModule, MatSlideToggleModule, MatSnackBarModule, MatSortModule, MatTableModule, MatTabsModule, MatToolbarModule, MatTooltipModule } from '@angular/material';
import { MatDialog, MatDialogConfig, MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';
import { AgreementCompleteComponent } from '../../components/agreement-complete/agreement-complete.component';

@Component({
  selector: 'agreement-ewa',
  templateUrl: './agreement-ewa.component.html',
  //   styleUrls: ['./gs-list.component.scss'],
  providers: [DatePipe, CommonUtils]
})
export class AgreementEwaComponent implements OnInit {
  agreement: Agreement;
  ewa: Ewa;
  rows: Array<Ewa>;
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
    console.log("In Ewa component")
  }

  ngOnInit() {
    this.agreement = new Agreement();
    this.ewa = new Ewa();
    console.log("In Ewa component");
    this.service.getAllEwas("APPROVED").subscribe(res => {
      this.rows = res;
      console.log(this.rows);
    });
  }

  completeEwa(id: string, buyerOrgName: string, buyerServiceNum: string, buyerCompName: string, sellerCompName: string, agmtPeriodCode: string, fromDate: string, toDate: string) {
    console.log(id, buyerOrgName, buyerServiceNum, buyerCompName, sellerCompName, agmtPeriodCode, fromDate, toDate)


    this.agreement.id = id;
    this.agreement.buyerOrgName = buyerOrgName;
    this.agreement.buyerCompanyServiceNumber = buyerServiceNum;
    this.agreement.buyerCompanyName = buyerCompName;
    this.agreement.sellerCompanyName = sellerCompName;
    this.agreement.agreementPeriodCode = agmtPeriodCode;
    this.agreement.fromDate = fromDate;
    this.agreement.toDate = toDate;
    this.agreement.type = "ewa";
    try {
      this.config.data = { "agreement": this.agreement };
      this.dialogRef = this.dialog.open(AgreementCompleteComponent, this.config);
    }
    catch (e) {
      console.log(e);
    }
  }






}
