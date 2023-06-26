import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { id } from '@swimlane/ngx-datatable/release/utils';
import 'rxjs/add/operator/switchMap';
import { CommonUtils } from '../../../shared/common/common-utils';
import { CommonService } from '../../../shared/common/common.service';
import { FeederLineLossService } from '../../services/feederlineloss.service';
import {MatSnackBar} from '@angular/material/snack-bar';

@Component({
    selector: 'feeder-line-loss',
    templateUrl: './feeder-line-loss.component.html',
    styleUrls: ['./feeder-line-loss.component.scss'],
    providers: [FeederLineLossService]
})

export class FeederLineLossComponent implements OnInit{
    orgList = [];
    substationlist = [];
    Feederlist = [];
    months = [];
    rows=[];
    disableEdc: boolean = false;
    disabless: boolean = false;
    disablefeeder: boolean = false;
    searchOrgId: string = "";
    orgName: string ="";
    ssName: string="";
    feederName:string="";
    searchssId: string = "";
    searchfeederId: string = "";
    statementMonth: string = "";
    statementYear: string = "";
    message:string="";
    totalexportunits:string="";
    totalfeederexportunits:string="";
    lospercentage:any;
    feedermeterno:String="";
    ExportunitDifference:String="";
    fuelType:String="SOLAR";
    // save_msg:any;
    // save_msg_show:boolean=false;

        constructor(
        private feederlinelossservice: FeederLineLossService,
        private route: ActivatedRoute,
        private router: Router,
        private commonUtils: CommonUtils,
        private commonService: CommonService,
        private _snackBar: MatSnackBar
     ){
        this.fetchEDCs();
    }

    ngOnInit(): void {
        this.months = this.commonService.fetchMonths();
        this.statementMonth = this.commonUtils.getPerviousMonth();
    this.statementYear = (this.statementMonth=="12")?this.commonUtils.getpreviousYear():this.commonUtils.getCurrentYear();
    
    }
//    get Edc datas
    fetchEDCs(){
        this.commonService.fetchEDCs().subscribe(
            result => {
              this.orgList = result;
            });
    }

    // Edc name change
    onEdcNameChange(event:any){
        this.fetchSubstations(event);
        const org = this.orgList.filter(x => x.id == event)
        console.log("org_name",org[0].name  )
        this.orgName=org[0].name;
    }

    // get substation based on edc id
     fetchSubstations(edc_id){
         this.feederlinelossservice.getSsById(edc_id).subscribe(
             result => {
                 console.log("substation_list",result);
                 this.substationlist=[];
                 this.substationlist=result;
                 this.searchssId=this.substationlist[0].id;
                 this.ssName=this.substationlist[0].name;
             }
         )
     }

    //  substation name change
    onsubstationNameChange(ss_id:any){
        console.log(ss_id);
        this.fetchFeederlist(ss_id)
        const ss = this.substationlist.filter(x => x.id == ss_id)
        console.log("ss_name",ss[0].name)
        this.ssName=ss[0].name;
    }

    // get feeder based on ss id
    fetchFeederlist(ss_id){
        this.feederlinelossservice.getfeedBySSId(ss_id).subscribe(
            result => {
                console.log("feeder_list",result);
                this.Feederlist=[];
                this.Feederlist=result;
                this.searchfeederId = this.Feederlist[0].id;
                this.feederName=this.Feederlist[0].name;
            }
        )
    }

    // feedername change
    onfeederNameChange(feederid:any){
        const ss = this.Feederlist.filter(x => x.id == feederid)
        console.log("ss_name",ss[0].name)
        this.feederName=ss[0].name;
    }

    fetchResult(){
        if(this.searchfeederId==""){
           this.message="Feeder id required";
           return false
        }
        
        
        this.feederlinelossservice.getfeederlistdata(this.searchfeederId,this.statementMonth,this.statementYear).subscribe(
            result =>{
                console.log("list",result['serviceReadings'].length);
                if(result['status']=='OK'){
                    if(result['serviceReadings'].length > 0){
                this.rows=[];
                this.rows=result['serviceReadings']
                this.totalexportunits=result['totalAllWegs'].toString();
                this.totalfeederexportunits=result['bulkMeterReading'].toString();
                var loss = result['lineLossPercentage'];
                this.lospercentage=loss.toFixed(2);
                this.feedermeterno=result['feederMeterNo'].toString();
                this.ExportunitDifference=result['exportsDifference'].toString();
                    }
                    else{
                     this.rows=[];
                     this.totalexportunits="";
                     this.totalfeederexportunits="";
                     this.lospercentage=null;
                     this.feedermeterno="";
                     this.ExportunitDifference="";
                    }
                }else{
                    this.rows=[];
                    this.rows=[];
                    this.totalexportunits="";
                    this.totalfeederexportunits="";
                    this.lospercentage=0.0;
                    this.feedermeterno="";
                }
            }
        ) 
    }

    Save(){
      var send_data={
          "orgId":this.searchOrgId,
          "orgName":this.orgName,
          "substationId":this.searchssId,
          "substationName":this.ssName,
          "feederId":this.searchfeederId,
          "feederName":this.feederName,
          "lossPercent":this.lospercentage,
          "month":this.statementMonth,
          "year":this.statementYear,
          "bulkMeterReading":this.totalfeederexportunits,
          "totalAllWegs":this.totalexportunits,
          "fuelType":this.fuelType
      }
      
      this.feederlinelossservice.savesolar_data(send_data).subscribe (
          res =>{  
              console.log(res);            
          }
        //   ,(err)=>{
        //     this._snackBar.open(err._body,'Close',{
        //         duration: 2000,
        //       });
        //   }
      )
    }
  
}