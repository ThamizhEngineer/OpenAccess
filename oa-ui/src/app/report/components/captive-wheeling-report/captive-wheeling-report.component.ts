import { Component, OnInit, HostBinding,Injectable } from '@angular/core';
import { Router, ActivatedRoute, Params } from '@angular/router';
import {Subject} from 'rxjs/Subject';
import { FormsModule } from '@angular/forms';
import { CommonService } from '../../../shared/common/common.service'; 
import { ReportService} from './../../services/report.service'; 
import { Criteria} from './../../vo/report'; 


@Component({
    selector: 'captive-criteria',
    templateUrl: './captive-criteria.html',
    //styleUrls: [],
    providers: []
  })

  export class CaptiveCriteriaComponent implements OnInit {
      reportMonth:string="";
      reportYear:string="";
      criteria:Criteria;
     
    months= [];
    years=[];
    constructor(
        private commonService: CommonService,
        private service:ReportService,      
        private router: Router,
    ){

    }
    ngOnInit() {
        this.criteria =new Criteria();
        this.months = this.commonService.fetchMonths();
        this.years = this.commonService.fetchYearList();
        console.log("CriteriaComponent");
    }
    searchReport(){
        this.criteria.month = this.reportMonth;
        this.criteria.year = this.reportYear;
        this.service.criteria = this.criteria;
        this.router.navigateByUrl('/reports/captive-display-edc-units');
       
        
    }
    reset(){
        console.log("in reset")
        window.location.reload();
    }


  }


  @Component({
    selector: 'captive-display-edc-units',
     templateUrl: './captive-display-edc-units.html',
    //styleUrls: [],
    providers: []
  })

  export class CaptiveDisplayEdcUnitsComponent implements OnInit {
    criteria:Criteria;
    month:string;
    year:string;
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
        this.service.getCaptiveWheelingReportByOrg(this.criteria.month,this.criteria.year).subscribe(_res=>{
            this.rows =_res;
            console.log(this.criteria.month,this.criteria.year)

        }); 
    }
    searchOrgService(orgId:string,orgName:string){
        console.log("In searchOrgService")
        console.log(this.criteria)
        this.criteria.orgId = orgId;
        this.criteria.orgName = orgName;
        this.service.criteria = this.criteria;
  
        this.router.navigateByUrl('/reports/captive-display-service-units');

    }
    back(){
        this.router.navigateByUrl('/reports/captive-criteria');
    }
  }

  @Component({
    selector: 'captive-display-service-units',
    templateUrl: './captive-display-service-units.html',
    //styleUrls: [],
    providers: []
  })

  export class CaptiveDisplayServiceUnitsComponent implements OnInit {
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
        this.service.getCaptiveWheelingReportByService(this.criteria.orgId,this.criteria.month,this.criteria.year).subscribe(res=>{
            this.rows =res;
            console.log(this.rows)
           
        })
    }
    back(){
        this.router.navigateByUrl('/reports/captive-display-edc-units');
    }
  }