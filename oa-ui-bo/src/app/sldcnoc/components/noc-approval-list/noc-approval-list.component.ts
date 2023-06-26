import { Component, OnInit } from '@angular/core';
import { SdlcNocService } from '../../sldc-noc.service';
import { searchSldc } from '../vo/search-sldc';
import { CommonUtils } from '../../../shared/common/common-utils';
import { CommonService } from '../../../shared/common/common.service';
import { SldcNOC } from '../vo/sldc-noc';
import { Router } from '@angular/router';
import { DatePipe } from '@angular/common';

@Component({
    selector: 'noc-approvallist',
    templateUrl: 'noc-approval-list.component.html'
})

export class NocApprovalList implements OnInit {
   
    searchServiceNo: string="";
    type:string;
    sldcNocList:[SldcNOC];
    searchSldc:searchSldc;
    appliedDt: any;
    appliedNo: any;
    cntrctDemand: any;
    compServNo: any;
    edcId: any;
    date:any;
    nocCode: any;
    nocpurpose: any;
    newList:any;
     value=[];
     public sortBy:string ='';
     public sortOrder:string ="desc";
     Noc=[];
     Noc1=[];
    constructor(private router: Router,private datePipe:DatePipe,private service:SdlcNocService){}

    ngOnInit(){
        console.log("id")
        this.searchSldc= new searchSldc();
        this.searchResult();
      
    }   
    searchFilter(){
     this.date = this.datePipe.transform(this.date, 'yyyy-MM-dd');
     if(this.date!==null){
      this.service.searchDate(this.date).subscribe(x=>{
        this.sldcNocList=x;
        console.log(this.sldcNocList);    
    })
}
else{
    this.searchResult();
}
    }
   
    searchResult(){
       
        
        this.service.searchSldcNoc(this.appliedDt,this.appliedNo,this.cntrctDemand,this.compServNo,this.edcId,this.nocCode,this.nocpurpose).subscribe(x=>{
            this.sldcNocList=x;
            console.log(this.sldcNocList)  
            this.Noc=[
                this.sldcNocList
                
            ]
            let nocdata =[
                x.filter(({status})=>status=='EDC-APPROVED'),
               x.filter(({status})=>status!='EDC-APPROVED')
            ]
    
           this.sldcNocList=nocdata[0].concat(nocdata[1])
               console.log(  this.sldcNocList);
               
        })
    }
    
    detailPage(id){
        this.router.navigateByUrl('sldcnocapproval/noc-approval-ce-detail/cons/'+id);
    }    
    // searchDate(){
    //     // this.type=purpose;
    //     // this.searchSldc.nocPurpose=purpose
        
    //     this.service.searchSldcNoc(this.appliedDt,this.appliedNo,this.cntrctDemand,this.compServNo,this.edcId,this.nocCode,this.nocpurpose).subscribe(x=>{
    //         this.sldcNocList=x;
    //         console.log(this.sldcNocList);
    //         this.sldcNocList.push(this.newList);
    //        console.log(this.newList);
           
            
    //     })
    // }

}