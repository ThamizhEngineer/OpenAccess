import 'rxjs/add/operator/switchMap';
import { Component, OnInit, HostBinding } from '@angular/core';
import { Router, ActivatedRoute, Params } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { CommonService } from '../../../../shared/common/common.service';
import { MatDatepickerModule } from '@angular/material';
import { MatNativeDateModule } from '@angular/material';
import { DatePipe } from '@angular/common';
import { EnergyLedgerService } from './../../services/energy-ledger.service';
import { EnergyLedger, EnegyLedgerForPrint } from './../../../vo/energy-ledger';

@Component({
  selector: 'app-energy-ledger-detail',
  templateUrl: './energy-ledger-detail.component.html',
  providers: [EnergyLedgerService, CommonService, MatDatepickerModule, MatNativeDateModule, DatePipe]
})
export class EnergyLedgerDetailComponent implements OnInit {
  energyLedger: EnergyLedger
  addScreen: boolean = true;
  disableControls: boolean = true;
  energyLedgerForPrint: Array<EnegyLedgerForPrint>;
  months = [];

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private service: EnergyLedgerService,
    private commonService: CommonService,
  ) {
    this.months = this.commonService.fetchMonths();

    //  this.fetchResults();
  }

  ngOnInit() {
    this.energyLedgerForPrint = [];

    this.energyLedger = new EnergyLedger();
    if (this.route.params['_value']['_id']) {
      //route.params['value'] will have the '_id' in it, during edit 
      this.addScreen = false;
      this.route.params
        .switchMap((params: Params) => this.service.getEnergyLedgerById(params['_id']))
        .subscribe((_energyLedger: EnergyLedger) => {
          this.energyLedger = _energyLedger;
          this.energyLedger.ledgerDate = this.energyLedger.ledgerDate.substr(0, 10);
          this.energyLedger.fromDate = this.energyLedger.fromDate.substr(0, 10);
          this.energyLedger.toDate = this.energyLedger.toDate.substr(0, 10);
          this.EnegyLedgerForPrints();

        });
    }

  }

  EnegyLedgerForPrints() {
    this.energyLedgerForPrint.push({
      "c1": this.energyLedger.c1,
      "c2": this.energyLedger.c1,
      "c3": this.energyLedger.c1,
      "c4": this.energyLedger.c1,
      "c5": this.energyLedger.c1
    })

  }

  listEnergyLedger() {
    this.router.navigateByUrl('/energy-ledger/energy-ledger-list');
  }



}
