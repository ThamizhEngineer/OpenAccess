import { Component, OnInit } from '@angular/core';
import { forEach } from '@angular/router/src/utils/collection';
import { saveAs } from 'file-saver';
import { WegWithBuyerReport } from '../../vo/WegWithBuyerReport';
import { ReportService } from '../../services/report.service';
import { CommonUtils } from '../../../shared/common/common-utils';
import { CommonService } from '../../../shared/common/common.service';

import { Criteria } from '../../vo/report';


@Component({
    selector: 'weg-with-buyer-report',
    templateUrl: './weg-with-buyer-report.component.html'

})
export class WegWithBuyerReportComponent implements OnInit {
    criteria : Criteria

    rows: Array<WegWithBuyerReport>;
    searchorgId: string = "";
    searchcapacity: string = "";
    orgList = [];
    filterNCES = [];
    filteredEDCs = [];
    disableEdc: boolean = false;

    searchmakeCode: string = "";
    powerPlantClassTypes = [];
    generatorModelTypes = [];
    constructor(
        private service: ReportService,
        private commonService: CommonService
    ) { }
    ngOnInit() {
        this.criteria = new Criteria();

        this.rows = [];
        this.fetchCodes();
        this.criteria.orgId = CommonUtils.getLoginUserEDC();
        if (this.criteria.orgId != "") {
          console.log("in edc select")
          this.searchorgId = this.criteria.orgId;
          this.disableEdc = true;
        }
        this.fetchEDC();
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
      fetchCodes(){
        this.commonService.fetchCodes().subscribe(
        result => {
          this.generatorModelTypes = result.filter(x=>x.listCode == "GENERATOR_MAKE_CODE");
  
          this.powerPlantClassTypes = result.filter(x=>x.listCode == "PLANT_CLASS_TYPE_CODE");
        })
    }
    fetchResults() {
        this.service.getWegWithBuyer(this.searchorgId, this.searchcapacity, this.searchmakeCode).subscribe(
            _wegwithbuyers => {
                this.rows = _wegwithbuyers;
                console.log(this.rows)
            });
    }

    openDownload(data: Response) {
		let content_type = data.headers.get('Content-type');
		let x_filename = "weg-with-buyer-report.pdf";
		console.log(content_type)
		saveAs(data.blob(), x_filename);
  }

  printReport(){ 
    this.service.printReport(this.searchorgId, this.searchcapacity, this.searchmakeCode).subscribe(xs=>{
			this.openDownload(xs);
		});

  }

}
