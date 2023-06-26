import 'rxjs/add/operator/switchMap';
import { Component, OnInit, HostBinding } from '@angular/core';
import { Router, ActivatedRoute, Params } from '@angular/router';
 
 
import { GeneratorhisService } from '../../services/genertorhis.service'; 
import { CommonService } from '../../../../shared/common/common.service';
import { CommonUtils } from '../../../../shared/common/common-utils';

@Component({
  selector: 'generator-his',
  templateUrl: './generator-his.component.html',
  styleUrls: ['./generator-his.component.scss'],
  providers: [GeneratorhisService]
})
export class GeneratorHisComponent implements OnInit{ 

  rows:any;
  mCompServNumber: string;
  statementMonth: string = "";
  statementYear: string = "";
  rowss:any=[];
  buyerData:any=[];
  captive:string="";
  capacityInKV:string="";
  dispCompanyServiceNumber:any;
  disableCompanyName: boolean = false;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private service: GeneratorhisService,private commonService:CommonService,private commonUtils: CommonUtils
  ) {}

  ngOnInit() {
    
  }

  getDataHis(){
    this.service.getHistoryByServiceNo(this.mCompServNumber).subscribe(x=>{
      this.rows=x;
      console.log(this.rows);
  this.getGsData();
  this.getBuyerDetail();
      
    })
  }

  getGsData(){
    this.service.getGeneratorDetail(this.mCompServNumber).subscribe(x=>{
         const data=x;
         this.rowss=data[0]
         console.log("details");       
         console.log(this.rowss);
         this.capacityInKV=this.rowss.capacity+"KW";
         if(this.rowss.flowTypeCode="IS-CAPTIVE"){
           this.captive="CAPTIVE"
         }
         else{
          this.captive=this.rowss.flowTypeCode;
         }
    })

  }

  getBuyerDetail(){
    this.service.getTradeData(this.mCompServNumber).subscribe(x=>{
       this.buyerData=x;
       console.log(this.buyerData);
       
    })

  }


  numberFormat(event){
    this.commonService.numberOnly(event);
  }

}
