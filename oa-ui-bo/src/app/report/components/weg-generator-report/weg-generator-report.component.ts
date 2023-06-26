import { Component, OnInit } from '@angular/core';
import { GenericReportOutput } from '../../vo/GenericReportOutput';
import { ReportService } from '../../services/report.service';
import { CommonService } from '../../../shared/common/common.service';
import { GenericReportInput } from '../../vo/GenericReportInput';
import { CommonUtils } from '../../../shared/common/common-utils';
import { forEach } from '@angular/router/src/utils/collection';
import { saveAs } from 'file-saver';
import { Criteria } from '../../vo/report';

@Component({
  selector: 'weg-generator-report-report',
  templateUrl: './weg-generator-report.component.html',
  styleUrls: ['./weg-generator-report.component.scss']
})
export class WegGeneratorReportComponent implements OnInit {
  criteria : Criteria
  rows: Array<GenericReportOutput>;
   
  filterNCES = [];
  filteredEDCs = [];
  selectedSellerOrg:string = "";
  selectedSellerCapacity:string = "";
  selectedIsCaptive:string = "";
  selectedBuyerOrg:string = "";
  selectedMakeCode:string="";
  orgList = [];
  powerPlantClassTypes = [];
  generatorModelTypes = [];
  disableRate: boolean = false;
  disableEdc: boolean = false;

  isCaptives = [
    { "key": "Y", "name": "WHEELING" },
    { "key": "N", "name": "SALE-TO-BOARD" }
  ]
  constructor(private service:ReportService,
  private commonService: CommonService) { 
   
    this.fetchEDC();
  }

  ngOnInit() {
    this.criteria = new Criteria();
    this.rows = [];
    this.fetchCodes();
   
    this.criteria.orgId = CommonUtils.getLoginUserEDC();
    if (this.criteria.orgId != "") {
      console.log("in edc select")
      this.selectedSellerOrg = this.criteria.orgId;
      this.disableEdc = true;
    }
    

  }
  fetchEDC(){
    this.commonService.fetchEDCs()
    .subscribe(
      edcs => {this.orgList = edcs;
        console.log(edcs) ;
      
        if(CommonUtils.getLoginUserTypeCode()=='MRT'){
          console.log(CommonUtils.getLoginUserCompany());
          console.log(this.orgList.filter((item) => item.ncesDivisionCode))
          this.filterNCES = this.orgList.filter((item) => item.ncesDivisionCode == CommonUtils.getLoginUserCompany());
          console.log(this.filterNCES)

          this.orgList =  this.filterNCES;

          console.log(this.orgList)
      

        }
        else if(CommonUtils.getLoginUserTypeCode()=='NCES'){
          console.log(CommonUtils.getLoginUserCompany());
          console.log(this.orgList.filter((item) => item.ncesDivisionCode))
          this.filterNCES = this.orgList.filter((item) => item.ncesDivisionCode);
          console.log(this.filterNCES)

          this.orgList =  this.filterNCES;
          console.log(this.orgList)
      
        }
       
 
      },
      error => {
        console.error('Error loading orgs!');
        console.error(error);
      });
  }


  searchReport(){
    console.log('search Weg Generator');

    if (this.criteria.orgId != "") {
      this.selectedSellerOrg = this.criteria.orgId;
    }
    console.log(this.selectedSellerOrg);

    let genericRptInput:GenericReportInput = new GenericReportInput();
    genericRptInput.reportName = 'WEG-GENERATOR-REPORT';
    if(this.selectedSellerOrg != "" && 
    this.selectedSellerOrg != null)
      genericRptInput.ip1 = this.selectedSellerOrg;
    
      if(this.selectedSellerCapacity != "" && 
    this.selectedSellerCapacity != null)
      genericRptInput.ip2 = this.selectedSellerCapacity;
    
    if(this.selectedIsCaptive != "" && 
    this.selectedIsCaptive != null) 
      genericRptInput.ip3 = this.selectedIsCaptive;

    if(this.selectedBuyerOrg != "" && 
    this.selectedBuyerOrg != null)
      genericRptInput.ip4 = this.selectedBuyerOrg;

      if(this.selectedMakeCode != "" && 
      this.selectedMakeCode != null)
        genericRptInput.ip5 = this.selectedMakeCode;

    console.log(genericRptInput.ip1);
    console.log(genericRptInput.ip2);
    console.log(genericRptInput.ip3);
    console.log(genericRptInput.ip4);
    console.log(genericRptInput.ip5);

    this.service.getGenericReport(genericRptInput)
    .subscribe(
      items => {this.rows = items ;
        console.log(this.rows)
        
      this.criteria.orgId = CommonUtils.getLoginUserEDC();

      });
}
 
  // openDownload(data: Response) {
	// 	let content_type = data.headers.get('Content-type');
	// 	let x_filename = "generator-report.pdf";
	// 	console.log(content_type)
	// 	saveAs(data.blob(), x_filename);
  // }

  // printReport(){ 
  //   this.service.printReport().subscribe(xs=>{
	// 		this.openDownload(xs);
	// 	});

  // }

  fetchCodes(){
	  this.commonService.fetchCodes().subscribe(
      result => {
        this.generatorModelTypes = result.filter(x=>x.listCode == "GENERATOR_MAKE_CODE");

        this.powerPlantClassTypes = result.filter(x=>x.listCode == "PLANT_CLASS_TYPE_CODE");
      })
  }

}
