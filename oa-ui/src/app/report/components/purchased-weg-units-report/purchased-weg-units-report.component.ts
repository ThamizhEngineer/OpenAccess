import { Component, OnInit, HostBinding,Injectable } from '@angular/core';
import { Router, ActivatedRoute, Params } from '@angular/router';
import {Subject} from 'rxjs/Subject';
import { FormsModule } from '@angular/forms';
import { CommonService } from '../../../shared/common/common.service'; 
import { ReportService} from './../../services/report.service'; 
import { Criteria} from './../../vo/report'; 


@Component({
    selector: 'pwu-criteria',
    templateUrl: './pwu-criteria.html',
    //styleUrls: [],
    providers: []
  })

  export class PwuCriteriaComponent implements OnInit {
      reportMonth:string="";
      reportYear:string="";
      criteria:Criteria;
     
    months= [];
    constructor(
        private commonService: CommonService,
        private service:ReportService,      
        private router: Router,
    ){

    }
    ngOnInit() {
        this.criteria =new Criteria();
        this.months = this.commonService.fetchMonths();
        console.log("CriteriaComponent");
    }
    searchReport(){
        this.criteria.month = this.reportMonth;
        this.criteria.year = this.reportYear;
        this.service.criteria = this.criteria;
        this.router.navigateByUrl('/reports/pwu-display-edc-units');
       
        
    }
    reset(){
        console.log("in reset")
        window.location.reload();
    }


  }


  @Component({
    selector: 'pwu-display-edc-units',
     templateUrl: './pwu-display-edc-units.html',
    //styleUrls: [],
    providers: []
  })

  export class PwuDisplayEdcUnitsComponent implements OnInit {
    criteria:Criteria;
    rows=[];
    constructor(
        private commonService: CommonService,
        private service:ReportService,       
        private router: Router,
    ){        
          
    }
    ngOnInit() {
        console.log("DisplayEdcUnitsComponent");
        this.criteria =this.service.criteria ;
        this.service.getWegUnitsReportByOrg(this.criteria.month,this.criteria.year).subscribe(_res=>{
            this.rows =_res;
        }); 
    }
    searchOrgService(orgId:string,orgName:string){
        console.log("In searchOrgService")
        console.log(this.criteria)
        this.criteria.orgId = orgId;
        this.criteria.orgName = orgName;
        this.service.criteria = this.criteria;
  
        this.router.navigateByUrl('/reports/pwu-display-service-units');

    }
    back(){
        this.router.navigateByUrl('/reports/pwu-criteria');
    }
  }

  @Component({
    selector: 'pwu-display-service-units',
    templateUrl: './pwu-display-service-units.html',
    //styleUrls: [],
    providers: []
  })

  export class PwuDisplayServiceUnitsComponent implements OnInit {
    criteria:Criteria;
    rows=[];
    constructor(
        private commonService: CommonService,
        private service:ReportService,
      
        private router: Router,
    ){

    }
    ngOnInit() {
        this.criteria =new Criteria();
        console.log("DisplayServiceUnitsComponent");
        this.criteria =this.service.criteria ;
        this.service.getWegUnitsReportByService(this.criteria.orgId,this.criteria.month,this.criteria.year).subscribe(res=>{
            this.rows =res;
            console.log(this.rows)
           
        })
    }
    back(){
        this.router.navigateByUrl('/reports/pwu-display-edc-units');
    }
  }