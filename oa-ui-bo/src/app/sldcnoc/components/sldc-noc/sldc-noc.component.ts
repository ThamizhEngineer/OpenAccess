import { Component, OnInit } from '@angular/core';
import { SdlcNocService } from '../../sldc-noc.service';
import { searchSldc } from '../vo/search-sldc';
import { CommonUtils } from '../../../shared/common/common-utils';
import { CommonService } from '../../../shared/common/common.service';
import { SldcNOC, SldcNOCLine } from '../vo/sldc-noc';
import { Router } from '@angular/router';

@Component({
  selector: '',
  templateUrl: 'sldc-noc.component.html'
})

export class SldcNocComponent implements OnInit {
  rows = [
    { "id": 1, "op1": "GNOC-732-2021-10-01", "op2": "In-Progress", "op3": "ARGCPTP11200001", "op4": "Gen 1", "op5": "TP11200001", "op14": "07-NOV-21" },
    { "id": 2, "op1": "GNOC-732-2021-11-01", "op2": "In-Progress", "op3": "ARGCPTP11200002", "op4": "Gen 1", "op5": "TP11200002", "op14": "07-DEC-21" },
    { "id": 2, "op1": "GNOC-732-2021-11-01", "op2": "In-Progress", "op3": "ARGCPTP11200003", "op4": "Gen 1", "op5": "TP11200003", "op14": "07-MAY-21" },
  ]

  searchSldc: searchSldc;
  orgList = [];
  filterNCES = [];
  isEdc: boolean = false;
  tabIndex: any;
  data: Array<SldcNOC>
  datas: Array<SldcNOC>
  Approvaldata: Array<SldcNOCLine>
  mOrgId: String;
  IdasEdc: String;
  edcname: any;
  sldcNoc: any = [];
  value = 'Buyer';
  status = [
    { "key": "CREATED", "name": "CREATED" },
    { "key": "EDC-APPROVED", "name": "EDC-APPROVED" },
    { "key": "EDC-REJECTED", "name": "EDC-REJECTED" },
    { "key": "DIRECTOR-APPROVED", "name": "DIRECTOR-APPROVED" },
    { "key": "DIRECTOR-REJECTED", "name": "DIRECTOR-REJECTED" }
  ]
  appliedDt: any;
  edcId: string;
  nocCode: string;
  compServNo: string;
  cntrctDemand: string;
  appliedNo: string;
  edc: any;
  disableEdc: boolean;
  Seller=[];
  Buyer=[];
  quantum: string;
  tSldcNocId: string;
  commitType: string;
  flowType: string;
  fuelGroup: string;
  isExisting: string;
  constructor(
    private service: SdlcNocService,
    private commonService: CommonService,
    private route:Router
  ) { }

  ngOnInit() {
    this.searchSldc = new searchSldc();
    this.edc = CommonUtils.getLoginUserEDC();
    this.searchSldc.edcId = CommonUtils.getLoginUserEDC();
    this.fetchEDCs();
    if (this.edc != "") {
      console.log(" edc select")
      this.edcId = this.edc;
      this.disableEdc = true;
    }
  
  }

  Collagedata:any;
    Buyertab(type) {
     
      this.searchSldc.nocPurpose = type
    console.log(this.searchSldc.nocPurpose);
    this.service.searchSldcNoc(this.appliedDt, this.nocCode, this.compServNo, this.edcId, this.cntrctDemand, this.appliedNo, this.appliedDt).subscribe(x => {
             this.Buyer = [
                  this.data,
               ]
        this.Collagedata = [
         x.filter(({nocPurpose})=>nocPurpose=='Seller'),
         x.filter(({nocPurpose})=>nocPurpose=='Buyer')
     
       ]
       this.data=this.Collagedata[1];
       console.log(this.data);
     })

     this.getapprovaldata();
    }
  Sellertab(type) {
    this.getapprovaldata();
    this.searchSldc.nocPurpose = type
    console.log(this.searchSldc.nocPurpose);
    
       this.service.searchSldcNoc(this.appliedDt, this.nocCode,this.cntrctDemand, this.compServNo, this.searchSldc.edcId, this.appliedNo, this.appliedDt).subscribe(x => {
               this.Seller = [
                    this.datas,
                 ]
          this.Collagedata = [
            x.filter(({nocPurpose})=>nocPurpose=='Buyer'),
           x.filter(({nocPurpose})=>nocPurpose=='Seller')
         ]
         this.datas=this.Collagedata[1];
         console.log(this.data);
       })
       this.getapprovaldata();
  }
  getapprovaldata(){
  this.service.searchApprovalNoc(this.quantum,this.tSldcNocId,this.commitType,this.flowType,this.isExisting).subscribe(y => {
    this.Approvaldata=y;
    console.log(this.Approvaldata); 

    
})
}
  Updateapprovalinfo(id:string) {
      console.log(id);
    try {
      this.route.navigateByUrl('sldcnocs/sldc-noc-form/' +id);
    }
    catch (e) {
      console.log(e);
    }
  }

  getByPurpose(purpose: any) {
    this.service.getSldcNocByPurpose(purpose).subscribe(x => {
      this.sldcNoc = [];
      this.sldcNoc = x;
    })
  }

  tabClick(event) {
    if (this.tabIndex != event.index) {
      this.searchSldc = new searchSldc();
      this.searchSldc.edcId = CommonUtils.getLoginUserEDC();
      this.sldcNoc = [];
      this.tabIndex = event.index;
    } else {
      this.searchSldc.edcId = CommonUtils.getLoginUserEDC();
      console.log(event);
    }

  }


  fetchEDCs() {
    // this.commonService.fetchWindEDCs().subscribe(
    this.commonService.fetchEDCs().subscribe(
      result => {
        this.orgList = result;
        console.log(result)
        this.mOrgId = CommonUtils.getLoginUserEDC();
        console.log(this.mOrgId);
        for (let i = 0; i < this.orgList.length; i++) {
          if (this.orgList[i].id == this.mOrgId) {
            this.IdasEdc = this.orgList[i].id + "-" + this.orgList[i].name

            this.edcId = this.orgList[i].id
            console.log(this.edcId);

          }

        }
        if (CommonUtils.getLoginUserTypeCode() == 'MRT') {
          console.log(CommonUtils.getLoginUserCompany());
          console.log(this.orgList.filter((item) => item.ncesDivisionCode))
          this.filterNCES = this.orgList.filter((item) => item.ncesDivisionCode == CommonUtils.getLoginUserCompany());
          console.log(this.filterNCES)

          this.orgList = this.filterNCES;
          console.log(this.orgList)
        }
        else if (CommonUtils.getLoginUserTypeCode() == 'NCES') {
          console.log(CommonUtils.getLoginUserCompany());
          console.log(this.orgList.filter((item) => item.ncesDivisionCode))
          this.filterNCES = this.orgList.filter((item) => item.ncesDivisionCode);
          console.log(this.filterNCES)

          this.orgList = this.filterNCES;
          console.log(this.orgList)
        }

      },
      error => {
        console.error('Error loading orgs!');
        console.error(error);
      });
  }

 
  
}