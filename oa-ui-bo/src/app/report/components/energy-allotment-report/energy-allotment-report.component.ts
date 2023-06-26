import { Component, OnInit } from '@angular/core';
import { GenericReportOutput } from '../../vo/GenericReportOutput';
import { ReportService } from '../../services/report.service';
import { CommonService } from '../../../shared/common/common.service';
import { GenericReportInput } from '../../vo/GenericReportInput';
import { forEach } from '@angular/router/src/utils/collection';
import { saveAs } from 'file-saver';

@Component({
  selector: 'energy-allotment-report',
  templateUrl: './energy-allotment-report.component.html',
  styleUrls: ['./energy-allotment-report.component.scss']
})
export class EnergyAllotmentReportComponent implements OnInit {
  rows: Array<GenericReportOutput>;

  selectedSellerOrg:string = "";
  selectedSellerCapacity:string = "";
  selectedIsCaptive:string = "";
  selectedBuyerOrg:string = "";
  selectedMakeCode:string="";
  orgList = [];
  powerPlantClassTypes = [];
  generatorModelTypes = [];
  disableRate: boolean = false;

  isCaptives = [
    { "key": "Y", "name": "IS-CAPTIVE" },
    { "key": "N", "name": "SELL-TO-BOARD" }
  ]
  constructor(private service:ReportService,
  private commonService: CommonService) { }

  ngOnInit() {
    this.fetchCodes();

    this.fetchEDC();

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

  openDownload(data: Response) {
		let content_type = data.headers.get('Content-type');
		let x_filename = "energy-allotment.pdf";
		console.log(content_type)
		saveAs(data.blob(), x_filename);
  }

  printReport(){ 
    this.service.printEnergyAllotmentReport().subscribe(xs=>{
			this.openDownload(xs);
		});

  }

  fetchCodes(){
	  this.commonService.fetchCodes().subscribe(
      result => {
        this.generatorModelTypes = result.filter(x=>x.listCode == "GENERATOR_MAKE_CODE");

        this.powerPlantClassTypes = result.filter(x=>x.listCode == "PLANT_CLASS_TYPE_CODE");
      })
  }

}
