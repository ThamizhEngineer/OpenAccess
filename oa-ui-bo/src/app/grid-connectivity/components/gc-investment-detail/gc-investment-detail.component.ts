import { Component, OnInit, HostBinding } from '@angular/core';

import { Gc, Loan, CaptiveUserContribution,EquityShareVotingRights } from './../../services/gc';
import { CommonService } from '../../../shared/common/common.service';
import { GcService } from './../../services/gc.service';

@Component({
  selector: 'gc-investment-detail',
  templateUrl: './gc-investment-detail.component.html',
  //styleUrls: [],
  providers: [CommonService]

})
export class GcInvestmentDetailComponent implements OnInit {

    gc: Gc;
    idLoan: Loan;
    captiveUserContribution: CaptiveUserContribution;
    equityShareVotingRights: EquityShareVotingRights;
    loanRowIndex: number;
    captiveUserConRowIndex: number;
    EquityShareVotingRightsRowIndex: number;
    currencies = [];
    currencyList = [];
    isWind: boolean = false;
    ownershipValue: string;
    loanOrigins = [{
            "key": "Domestic",
            "name": "Domestic"
        },
        {
            "key": "International",
            "name": "International"
        }
    ];

    shareUnits = [{
            "key": "Unit",
            "name": "Unit"
        },
        {
            "key": "%",
            "name": "%"
        }
    ];
    isDisabled = false;
    isMandatory = false;

    constructor(private commonService: CommonService, private gcService: GcService) {
        this.gcService.gcEvent.subscribe(_gc => {
            this.gc = _gc;
        })
    }

    ngOnInit() {
        this.gc = new Gc();
        this.idLoan = new Loan();
        this.captiveUserContribution = new CaptiveUserContribution();
        this.equityShareVotingRights = new EquityShareVotingRights();
        this.initLoan();
        this.initCaptiveUserCon();
        this.initEquityShareVotingRights();
        this.fetchCurrencies();
    }

    filterCurrencies(val: string) {
        return val ? this.currencies.filter((s) => s.name.match(new RegExp(val, 'gi'))) : this.currencies;
    }

    fetchCurrencies() {
        this.commonService.fetchCurrencies().subscribe(
            result => {
                this.currencies = result;
            },
            error => {
                console.error('Error loading currencies!');
                console.error(error);
            }
        );
    }

    initLoan() {
        if (!this.gc.idLoans) {
            this.gc.idLoans = [];
        }

        this.idLoan = Object.assign({}, {
            loanOrigin: '',
            sourceName: '',
            sourceAddress: '',
            loanAmount: '',
            currency: '',
            exchangeRate: ''
        });
        this.loanRowIndex = -1;
    }

    addLoan() {
        if (!this.gc.idLoans) {
            this.gc.idLoans = [];
        }
        this.gc.idLoans.push(Object.assign({}, this.idLoan));
        this.gcService.setGc(this.gc);
        this.initLoan();
    }

    updateLoan() {
        let tempArray = [];
        for (let index = 0; index < this.gc.idLoans.length; index++) {
            if (this.loanRowIndex == index) {
                tempArray.push(Object.assign({}, this.idLoan));
            } else {
                tempArray.push(this.gc.idLoans[index]);
            }

        }
        this.gc.idLoans = tempArray;
        this.gcService.setGc(this.gc);
        this.initLoan();
    }


    editLoan(_index: number) {
        this.loanRowIndex = _index;
        this.idLoan = Object.assign({}, this.gc.idLoans[_index]);
    }

    deleteLoan(_index: number) {
        this.gc.idLoans.splice(_index, 1);
    }

    initEquityShareVotingRights() {
        if (!this.gc.idEquityShareVotingRights) {
            this.gc.idEquityShareVotingRights = [];
        }
        this.equityShareVotingRights = new EquityShareVotingRights();
        this.EquityShareVotingRightsRowIndex = -1;
    }

    addEquityShareVotingRights() {
        if (!this.gc.idEquityShareVotingRights) {
            this.gc.idEquityShareVotingRights = [];
        }

        this.gc.idEquityShareVotingRights.push(Object.assign({}, this.equityShareVotingRights));
        this.calculateOwnerShipShare();
        this.gcService.setGc(this.gc);

        this.initEquityShareVotingRights();
    }

    updateEquityShareVotingRights() {
        let tempArray = [];
        for (let index = 0; index < this.gc.idEquityShareVotingRights.length; index++) {
            if (this.EquityShareVotingRightsRowIndex == index) {
                tempArray.push(Object.assign({}, this.equityShareVotingRights));
            } else {
                tempArray.push(this.gc.idEquityShareVotingRights[index]);
            }
        }

        this.gc.idEquityShareVotingRights = tempArray;
        this.gcService.setGc(this.gc);

        this.initEquityShareVotingRights();
    }

    editEquityShareVotingRights(_index: number) {
        this.EquityShareVotingRightsRowIndex = _index;
        this.equityShareVotingRights = Object.assign({}, this.gc.idEquityShareVotingRights[_index]);
    }

    deleteEquityShareVotingRights(_index: number) {
        this.gc.idEquityShareVotingRights.splice(_index, 1);
    }

    calculateOwnerShipShare() {
        let check = 0;
        if (this.gc.idCaptiveUserContributions != null) {
            this.gc.idCaptiveUserContributions.forEach(element => {

                check = check + parseFloat(element.percentageHoldingInEquityShares);
            });
            if (check > 26) {
                this.ownershipValue = "More Than";
                // this.gc.checkOwnership = true;

            } else if (check < 26) {
                this.ownershipValue = "Less Than ";
                alert("Captive Users should have more than 26% !!!");
                // this.gc.checkOwnership = false;
            } else if (check = 26) {
                this.ownershipValue = "";
                // this.gc.checkOwnership = true;
            }
            this.gc.idOwnPercCaptive = check.toString();
        }
    }
    initCaptiveUserCon() {
        if (!this.gc.idCaptiveUserContributions) {
            this.gc.idCaptiveUserContributions = [];
        }
        this.captiveUserContribution = new CaptiveUserContribution();
        this.captiveUserConRowIndex = -1;
    }
    addCaptiveUserCon() {
        if (!this.gc.idCaptiveUserContributions) {
            this.gc.idCaptiveUserContributions = [];
        }

        this.gc.idCaptiveUserContributions.push(Object.assign({}, this.captiveUserContribution));
        this.calculateOwnerShipShare();
        this.gcService.setGc(this.gc);

        this.initCaptiveUserCon();
    }

    updateCaptiveUserCon() {
        let tempArray = [];
        for (let index = 0; index < this.gc.idCaptiveUserContributions.length; index++) {
            if (this.captiveUserConRowIndex == index) {
                tempArray.push(Object.assign({}, this.captiveUserContribution));
            } else {
                tempArray.push(this.gc.idCaptiveUserContributions[index]);
            }

        }

        this.gc.idCaptiveUserContributions = tempArray;
        this.calculateOwnerShipShare();
        this.gcService.setGc(this.gc);

        this.initCaptiveUserCon();
    }

    editCaptiveUserCon(_index: number) {
        this.captiveUserConRowIndex = _index;
        this.captiveUserContribution = Object.assign({}, this.gc.idCaptiveUserContributions[_index]);
    }

    deleteCaptiveUserCon(_index: number) {
        this.gc.idCaptiveUserContributions.splice(_index, 1);
        this.calculateOwnerShipShare();
    }
}