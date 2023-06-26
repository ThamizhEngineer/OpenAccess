import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs/Rx';
import { GenericReportOutput } from '../../vo/GenericReportOutput';
import { GenericReportInput } from '../../vo/GenericReportInput';
import { ReportService } from '../../services/report.service';
import { CommonService } from '../../../shared/common/common.service';

@Component({
  selector: 'generator-wise-consumer-report',
  templateUrl: './generator-wise-consumer-report.component.html',
  styleUrls: ['./generator-wise-consumer-report.component.scss']
})
export class GeneratorWiseConsumerReportComponent implements OnInit {
  rows: Array<GenericReportOutput>;
  months = [];
  orgList = [];
  fuelTypes = [];
  voltages =[];
  
  selVoltage: String;
  selFuel: String;
  selAgreementMonth: String;
  selAgreementYear: String;
  selOrgName: String;

  constructor(private service:ReportService, private commonService:CommonService) { }

  ngOnInit() {
    console.log("Im in init");
    this.rows = [];
    this.months = this.commonService.fetchMonths();
    this.fetchEDC();
    this.fetchFuelTypes();
    this.fetchVoltages();
    console.log(this.voltages);
  }

  fetchEDC(){
    this.commonService.fetchEDCs()
    .subscribe(
      edcs => {this.orgList = edcs;},
      error => {
        console.log("Error loading EDCs");
        console.log(error);
      }
    );
  }

  fetchFuelTypes(){
    this.commonService.fetchCodes("FUEL_TYPE_CODE")
    .subscribe(
      fuelTypes => {this.fuelTypes = fuelTypes;},
      error => {
        console.error("Error loading Fuel Types");
        console.error(error);
      });
  }

  fetchVoltages() {
    this.commonService.fetchCodes("VOLTAGE_CODE")
    .subscribe(
      result => {
        this.voltages = result;
      },
 
      error => {
        console.error("Error loading Voltages");
        console.error(error);
      });
  }

  searchResults(){
    let genRptInput:GenericReportInput = new GenericReportInput();
    genRptInput.reportName = 'GENERATOR-WISE-CONSUMER-REPORT';
    
    if(this.selAgreementMonth != "" && 
    this.selAgreementMonth != null)
      genRptInput.ip1 = this.selAgreementMonth;

    if(this.selAgreementYear != "" && 
    this.selAgreementYear != null)
      genRptInput.ip2 = this.selAgreementYear;

    if(this.selOrgName != "" && 
    this.selOrgName != null)
      genRptInput.ip3 = this.selOrgName;

    if(this.selVoltage != "" && 
    this.selVoltage != null)
      genRptInput.ip4 = this.selVoltage;

    if(this.selFuel != "" && 
    this.selFuel != null)
      genRptInput.ip5 = this.selFuel;

    this.service.getGenericReport(genRptInput)
    .subscribe(items => this.rows = items);
    
    console.log(genRptInput.reportName);
    console.log(genRptInput.ip1);
    console.log(genRptInput.ip2);
    console.log(genRptInput.ip3);
    console.log(genRptInput.ip4);
    console.log(genRptInput.ip5);
  }

}
