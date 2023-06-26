import { Component,OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { SldcNOC } from '../vo/sldc-noc';
import { SdlcNocService } from '../../sldc-noc.service';
import { searchSldc } from '../vo/search-sldc';
import { CommonUtils } from '../../../shared/common/common-utils';
import { CommonService } from '../../../shared/common/common.service';
@Component({
    selector: 'noc-approval-director',
    templateUrl: './noc-approval-director-list.component.html'
})

export class NocApprovalDirectorList implements OnInit {

    searchServiceNo: string="";
    type:string;
    orgList = [];
    filterNCES = [];
    isEdc:boolean=false;
    tabIndex:any;
    searchSldc:searchSldc;
    sldcNocList:[SldcNOC];

    status = [
        { "key": "CREATED", "name": "CREATED" },
        { "key": "EDC-APPROVED", "name": "EDC-APPROVED" },
        { "key": "EDC-REJECTED", "name": "EDC-REJECTED" },
        { "key": "DIRECTOR-APPROVED", "name": "DIRECTOR-APPROVED" },
        { "key": "DIRECTOR-REJECTED", "name": "DIRECTOR-REJECTED" }
      ]
  appliedDt: any;
  appliedNo: any;
  cntrctDemand: any;
  compServNo: any;
  edcId: any;
  nocCode: any;
  nocpurpose: any;
 

    constructor(private router: Router,private service:SdlcNocService,private commonService:CommonService){}

    ngOnInit(){
        this. sldcNocList=[new SldcNOC] 
        this.searchSldc=new searchSldc();
        this.isEdc = CommonUtils.getLoginUserTypeCode()=="EDC";
        this.searchSldc.edcId = CommonUtils.getLoginUserEDC();
        this.fetchEDCs();     
    }

    tabClick(event){
        if(this.tabIndex!=event.index){
            this.searchSldc=new searchSldc();
            this.sldcNocList=[new SldcNOC];
            this.searchSldc.edcId = CommonUtils.getLoginUserEDC();
            this.tabIndex=event.index;
        }else{
            console.log(event);
        }
        
    }
    
   
    searchResult(purpose){      
        this.type=purpose;
        this.searchSldc.nocPurpose=purpose
        this.service.searchSldcNoc(this.appliedDt,this.appliedNo,this.cntrctDemand,this.compServNo,this.edcId,this.nocCode,this.nocpurpose).subscribe(x=>{
            this.sldcNocList=x;
        })
       
    }
    
    detailPage(id){
        console.log("In-conDetail")
        this.router.navigateByUrl('sldcnocapproval/noc-approval-director-detail/cons/'+id);
    }  
    
    
    fetchEDCs() {
        // this.commonService.fetchWindEDCs().subscribe(
          this.commonService.fetchEDCs().subscribe(
          result => {
            this.orgList = result;
            console.log(result)
             
           
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
}