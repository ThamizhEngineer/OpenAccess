
import 'rxjs/add/operator/switchMap';
import 'rxjs/add/operator/startWith';
import { Component, OnInit, HostBinding, Inject } from '@angular/core';
import { Router, ActivatedRoute, Params } from '@angular/router';
import { Agreement } from './../../../vo/agreement';
import { Noc} from './../../../vo/noc'; 
import { Consent } from './../../../vo/consent';
import { Ewa } from './../../../vo/ewa';
import { Epa } from './../../../vo/epa';
import { Oaa } from './../../../vo/oaa';
import { AgreementService } from './../../services/agreement.service';
import { CommonService } from './../../../../shared/common/common.service';

import { DatePipe } from '@angular/common';
import { CommonUtils } from '../../../../shared/common/common-utils';
import { MatAutocompleteModule, MatButtonModule, MatButtonToggleModule, MatCardModule, MatCheckboxModule, MatChipsModule, MatStepperModule, MatDatepickerModule, MatDialogModule, MatExpansionModule, MatGridListModule, MatIconModule, MatInputModule, MatListModule, MatMenuModule, MatNativeDateModule, MatPaginatorModule, MatProgressBarModule, MatProgressSpinnerModule, MatRadioModule, MatRippleModule, MatSelectModule, MatSidenavModule, MatSliderModule, MatSlideToggleModule, MatSnackBarModule, MatSortModule, MatTableModule, MatTabsModule, MatToolbarModule, MatTooltipModule } from '@angular/material';
import { MatDialog, MatDialogRef, MatDialogConfig, MAT_DIALOG_DATA } from '@angular/material';

@Component({
    selector: 'agreement-complete',
    templateUrl: './agreement-complete.component.html',
    //   styleUrls: ['./gs-list.component.scss'],
    providers: [DatePipe, CommonUtils]
})
export class AgreementCompleteComponent implements OnInit {
    agreement: Agreement;
    consent: Consent;
    ewa: Ewa;
    oaa: Oaa;
    noc: Noc;
    epa: Epa;
    constructor(
        private route: ActivatedRoute,
        private router: Router,
        private datePipe: DatePipe,
        private service: AgreementService,
        public dialogRef: MatDialogRef<AgreementCompleteComponent>,
        @Inject(MAT_DIALOG_DATA) public data: any,

    ) {


    }


    ngOnInit() {
        this.agreement = new Agreement();
        this.consent = new Consent();
        this.epa = new Epa();
        this.ewa = new Ewa();
        this.oaa = new Oaa();
        this.noc = new Noc();
        this.agreement = this.data.agreement;
        console.log("complete agreement")

        console.log(this.agreement);
        console.log(this.agreement.buyerCompanyName);

    }

    complete() {
        if (this.agreement.type == "consent") {
            this.agreement.agreementDate = this.datePipe.transform(this.agreement.agreementDate, 'yyyy-MM-dd');
            console.log(this.agreement);

            this.consent.id = this.agreement.id;
            this.consent.agreementDate = this.agreement.agreementDate;
            console.log(this.consent)
            this.service.completeConsent(this.consent.id, this.consent).subscribe(
                result => {
                    this.router.navigateByUrl('agreement/agreement-list');
                },
                error => {
                    console.error('Error Completing consent!');
                    console.error(error);
                }
            );
        }
        if (this.agreement.type == "noc") {
            this.agreement.agreementDate = this.datePipe.transform(this.agreement.agreementDate, 'yyyy-MM-dd');
            console.log(this.agreement);

            this.noc.id = this.agreement.id;
            this.noc.agreementDate = this.agreement.agreementDate;
            console.log(this.noc)
            this.service.completeNoc(this.noc.id, this.noc).subscribe(
                result => {
                    this.router.navigateByUrl('agreement/agreement-list');
                },
                error => {
                    console.error('Error Completing Noc!');
                    console.error(error);
                }
            );
        }

        if (this.agreement.type == "ewa") {
            this.agreement.agreementDate = this.datePipe.transform(this.agreement.agreementDate, 'yyyy-MM-dd');
            console.log(this.agreement);
            this.ewa.id = this.agreement.id;
            this.ewa.agreementDate = this.agreement.agreementDate;
            console.log(this.ewa)
            this.service.completeEwa(this.ewa.id, this.ewa).subscribe(
                result => {
                    this.router.navigateByUrl('agreement/agreement-list');
                },
                error => {
                    console.error('Error Completing Ewa!');
                    console.error(error);
                }
            );
        }
        if (this.agreement.type == "epa") {
            this.agreement.agreementDate = this.datePipe.transform(this.agreement.agreementDate, 'yyyy-MM-dd');
            console.log(this.agreement);
            this.epa.id = this.agreement.id;
            this.epa.agreementDate = this.agreement.agreementDate;
            console.log(this.ewa)
            this.service.completeEpa(this.epa.id, this.epa).subscribe(
                result => {
                    this.router.navigateByUrl('agreement/agreement-list');
                },
                error => {
                    console.error('Error Completing Epa!');
                    console.error(error);
                }
            );
        }
        if (this.agreement.type == "oaa") {
            this.agreement.agreementDate = this.datePipe.transform(this.agreement.agreementDate, 'yyyy-MM-dd');
            console.log(this.agreement);

            this.oaa.id = this.agreement.id;
            this.oaa.agreementDate = this.agreement.agreementDate;
            console.log(this.oaa)
            this.service.completeOaa(this.oaa.id, this.oaa).subscribe(
                result => {
                    this.router.navigateByUrl('agreement/agreement-list');
                },
                error => {
                    console.error('Error Completing Oaa!');
                    console.error(error);
                }
            );
        }

    }
}
