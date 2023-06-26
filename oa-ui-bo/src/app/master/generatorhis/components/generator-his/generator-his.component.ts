import 'rxjs/add/operator/switchMap';
import { Component, OnInit, HostBinding } from '@angular/core';
import { Router, ActivatedRoute, Params } from '@angular/router';
 
 
import { GeneratorhisService } from '../../services/genertorhis.service'; 
import { CommonService } from '../../../../shared/common/common.service';
import { CommonUtils } from '../../../../shared/common/common-utils';
import { servicehistory } from '../../../vo/servicehis';

@Component({
  selector: 'generator-his',
  templateUrl: './generator-his.component.html',
  styleUrls: ['./generator-his.component.scss'],
  providers: [GeneratorhisService]
})
export class GeneratorHisComponent implements OnInit{ 

  rows:[servicehistory];
  utility:servicehistory[];
  namechange:servicehistory[];
  migration:servicehistory[];
  meterchange:servicehistory[];
  mCompServNumber: string;
  statementMonth: string = "";
  statementYear: string = "";
  rowss:any=[];
  buyerData:any=[];
  captive:string="";
  capacityInKV:string="";

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
      this.utility=[];
      this.migration=[];
      this.namechange=[];
      this.meterchange=[];
      for (let i=0;i<this.rows.length;i++){

          if(this.rows[i].commercialMonth!=null ){
            this.utility.push(this.rows[i]);
          }
          if (this.rows[i].nameChangeMonth!=null){
            this.namechange.push(this.rows[i]);
          }
          if(this.rows[i].migrationMonth!=null){
            this.migration.push(this.rows[i]);
          }
          if(this.rows[i].mc_month!=null){
            this.meterchange.push(this.rows[i]);
          }


      }
  
    

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
