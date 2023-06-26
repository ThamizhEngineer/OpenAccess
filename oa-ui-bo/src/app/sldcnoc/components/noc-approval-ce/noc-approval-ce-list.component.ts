import { CommonService } from './../../../shared/common/common.service';
import { Component,OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { SldcNOC } from '../vo/sldc-noc';
import { SdlcNocService } from '../../sldc-noc.service';
import { searchSldc } from '../vo/search-sldc';

@Component({
    selector: 'noc-approval-ce',
    templateUrl: './noc-approval-ce-list.component.html'
})

export class NocApprovalCeList implements OnInit {

    searchServiceNo: string="";
    type:string;
    sldcNocList:[SldcNOC];
    sldc:SldcNOC;
    searchSldc:searchSldc;
    appliedDt: any;
    appliedNo: any;
    cntrctDemand: any;
    compServNo: any;
    edcId: any;
    nocCode: any;
    nocpurpose: any;
    company:any;

    values='Seller';
    valued='Buyer'

    constructor(private router: Router,private service:SdlcNocService, private commonService:CommonService){}
    status:any;
    ngOnInit(){
        console.log("id")
        this.searchSldc= new searchSldc();

       
    }   
    
    
    searchResult(purpose){      
        this.type=purpose;
        this.searchSldc.nocPurpose=purpose
        this.service.searchSldcNoc(this.appliedDt,this.appliedNo,this.cntrctDemand,this.compServNo,this.edcId,this.nocCode,this.nocpurpose).subscribe(x=>{
            this.sldcNocList=x; 
            
        }) 
    }
    Tabpick(){
        this.values='Buyer';
        console.log(this.values);
        
    }
    Tabpicks(){
        this.valued='Seller';
        console.log(this.valued);
        
    }
    detailPage(id){
        this.router.navigateByUrl('sldcnocapproval/noc-approval-ce-detail/cons/'+id);
    }    

    ///To allow number only
    numberFormat(event){
        this.commonService.numberOnly(event)
    }

}
