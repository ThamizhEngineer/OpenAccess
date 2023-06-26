import {Component, Input,OnInit} from '@angular/core'
import { Cs,CsLoan,CsCaptiveUserContribution,CsEquityShareVotingRights} from './../../../vo/cs'; 
import { CsService } from './../../services/cs.service';

import { CommonService } from './../../../../shared/common/common.service';
@Component({
  selector: 'investment-details',
  templateUrl: './investment-details.html',
  providers: [CommonService, CsService]
})

export class InvestmentDetails implements OnInit {
  @Input() cs: Cs;
  @Input() stepDisable: boolean;
  csLoan:CsLoan;
  captiveUserContribution: CsCaptiveUserContribution;
  equityShareVotingRights: CsEquityShareVotingRights;
  loanRowIndex: number;
  captiveUserConRowIndex: number;
  EquityShareVotingRightsRowIndex: number;
  loanOrigins = [
    { "key": "Domestic", "name": "Domestic" },
    { "key": "International", "name": "International" }
  ];
  ownershipValue: string;
  currencies = [];
  currencyList = [];
  constructor(
 
    private commonService: CommonService,
    private service: CsService,

  ) { 
 
  }

  ngOnInit() {
    this.csLoan = new CsLoan();
    this.initLoan();
    this.initCaptiveUserCon();
    this.initEquityShareVotingRights();
    this.fetchCurrencies();
  }
  // add(){
	// this.cs.csLoans.push({'loanOrigin':'aaa', editable: true});
	
  // }


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
        if (!this.cs.csLoans) {
          this.cs.csLoans = [];
        }
    
        this.csLoan = Object.assign({}, {
          loanOrigin: '', sourceName: '', sourceAddress: '', loanAmount: '', currency: '', exchangeRate: ''
        });
        this.loanRowIndex = -1;
      }
    
    
      addLoan() {
        if (!this.cs.csLoans) {
          this.cs.csLoans = [];
        }
        this.cs.csLoans.push(Object.assign({}, this.csLoan));
        this.service.setCs(this.cs);
        this.initLoan();
      }
    
      updateLoan() {
    
        // this.company.addresses[this.addressRowIndex-1] = Object.assign({},this.address);
        // the above code wasnt updating a row in the table due to somebug
        // as a workaround, creating a new array with same rows except the edited row.
        var tempArray = [];
        for (var index = 0; index < this.cs.csLoans.length; index++) {
          if (this.loanRowIndex == index) {
            tempArray.push(Object.assign({}, this.csLoan));
          }
          else {
            tempArray.push(this.cs.csLoans[index]);
          }
    
        }
        this.cs.csLoans = tempArray;
        this.service.setCs(this.cs);
        this.initLoan();
      }
    
    
      editLoan(_index: number) {
        this.loanRowIndex = _index;
        this.csLoan = Object.assign({}, this.cs.csLoans[_index]);
      }
    
      deleteLoan(_index: number) {
        this.cs.csLoans.splice(_index, 1);
      }
    
      initEquityShareVotingRights() {
        if (!this.cs.csEquityShareVotingRights) {
          this.cs.csEquityShareVotingRights = [];
        }
      //  console.log("In init cap start")
        this.equityShareVotingRights = new CsEquityShareVotingRights();
        this.EquityShareVotingRightsRowIndex = -1;
      }
    
      addEquityShareVotingRights() {
        if (!this.cs.csEquityShareVotingRights) {
          this.cs.csEquityShareVotingRights= [];
        }
      //  console.log("In add cap start")
    
        this.cs.csEquityShareVotingRights.push(Object.assign({}, this.equityShareVotingRights));
        // this.calculateOwnerShipShare();
        this.service.setCs(this.cs);
    
        console.log("In add cap end")
        this.initEquityShareVotingRights();
      }
    
      updateEquityShareVotingRights() {
    
        // this.company.addresses[this.addressRowIndex-1] = Object.assign({},this.address);
        // the above code wasnt updating a row in the table due to somebug
        // as a workaround, creating a new array with same rows except the edited row.
        var tempArray = [];
        for (var index = 0; index < this.cs.csEquityShareVotingRights.length; index++) {
          if (this.EquityShareVotingRightsRowIndex == index) {
            tempArray.push(Object.assign({}, this.equityShareVotingRights));
          }
          else {
            tempArray.push(this.cs.csEquityShareVotingRights[index]);
          }
    
        }
    
        this.cs.csEquityShareVotingRights = tempArray;
      //  this.calculateOwnerShipShare();
      this.service.setCs(this.cs);
    
        this.initEquityShareVotingRights();
      }
    
    
      editEquityShareVotingRights(_index: number) {
        this.EquityShareVotingRightsRowIndex = _index;
        this.equityShareVotingRights= Object.assign({}, this.cs.csEquityShareVotingRights[_index]);
      }
    
      deleteEquityShareVotingRights(_index: number) {
        this.cs.csEquityShareVotingRights.splice(_index, 1);
      //  this.calculateOwnerShipShare();
      }
    
    
    
      calculateOwnerShipShare() {
        var check = 0;
    
       
        if (this.cs.csCaptiveUserContributions != null) {
          this.cs.csCaptiveUserContributions.forEach(element => {
            console.log("loop.percentageHoldingInEquitySharese")
            console.log(element)
            console.log(element.percentageHoldingInEquityShares)
    
           check = check + parseFloat(element.percentageHoldingInEquityShares);
           console.log("check" + check)
          });
          if (check > 26) {
            this.ownershipValue = "More Than";
            // this.cs.checkOwnership = true;
    
          } else if (check < 26) {
            this.ownershipValue = "Less Than ";
            alert("Captive Users should have more than 26% !!!");
            // this.cs.checkOwnership = false;
          } else if (check = 26) {
            this.ownershipValue = "";
            // this.cs.checkOwnership = true;
          }
          this.cs.csOwnPercCaptive = check.toString();
        }
    
    
      }
      initCaptiveUserCon() {
        if (!this.cs.csCaptiveUserContributions) {
          this.cs.csCaptiveUserContributions = [];
        }
        console.log("In init cap start")
        this.captiveUserContribution = new CsCaptiveUserContribution();
        this.captiveUserConRowIndex = -1;
      }
      addCaptiveUserCon() {
        if (!this.cs.csCaptiveUserContributions) {
          this.cs.csCaptiveUserContributions = [];
        }
        console.log("In add cap start")
    
        this.cs.csCaptiveUserContributions.push(Object.assign({}, this.captiveUserContribution));
        this.calculateOwnerShipShare();
        this.service.setCs(this.cs);
    
        console.log("In add cap end")
        this.initCaptiveUserCon();
      }
    
      updateCaptiveUserCon() {
    
        // this.company.addresses[this.addressRowIndex-1] = Object.assign({},this.address);
        // the above code wasnt updating a row in the table due to somebug
        // as a workaround, creating a new array with same rows except the edited row.
        var tempArray = [];
        for (var index = 0; index < this.cs.csCaptiveUserContributions.length; index++) {
          if (this.captiveUserConRowIndex == index) {
            tempArray.push(Object.assign({}, this.captiveUserContribution));
          }
          else {
            tempArray.push(this.cs.csCaptiveUserContributions[index]);
          }
    
        }
    
        this.cs.csCaptiveUserContributions = tempArray;
        this.calculateOwnerShipShare();
        this.service.setCs(this.cs);
    
        this.initCaptiveUserCon();
      }
    
    
      editCaptiveUserCon(_index: number) {
        this.captiveUserConRowIndex = _index;
        this.captiveUserContribution = Object.assign({}, this.cs.csCaptiveUserContributions[_index]);
      }
    
      deleteCaptiveUserCon(_index: number) {
        this.cs.csCaptiveUserContributions.splice(_index, 1);
        this.calculateOwnerShipShare();
      }
}