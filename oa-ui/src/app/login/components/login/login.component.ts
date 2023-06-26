import 'rxjs/add/operator/switchMap';
import { Component, OnInit, HostBinding } from '@angular/core';
import { Router, ActivatedRoute, Params } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { User } from './../../../user/services/user';
import {  LoginService } from './../../services/login.service';
import { AuthUser } from '../../../user/services/authuser';
import * as CryptoJS from 'crypto-js';  
import { ChartOptions } from '../../../dashboard/_models/chart.option';
import { environment } from '../../../../environments/environment';
import { CommonUtils } from '../../../shared/common/common-utils';
import { CommonService } from '../../../shared/common/common.service';
import * as uuid from 'uuid';
const ChartColors: any[] = [{
		backgroundColor:["#3f51b5", "#85144B", "#5B481A", "#FF4136", "#3D9970", "#FF851B", "#B10DC9", "#111111", "#01FF70", "#FFDC00", "#FF4136", "#001F3F"] 
      }];
@Component({
  selector: 'login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss'],
  providers: [LoginService]
})
export class LoginComponent implements OnInit {
  user: User;
  authUser: AuthUser;
 message: string;
  logging:boolean = false;

  barChartLabels1: string[];
  barChartOptions1: any;	
  barChartData1: any[]; 
 
  barChartLabels2: string[];
  barChartOptions2: any;	
  barChartData2: any[]; 
  totalGenerators:any[];
  edcGenerators:any[];
  isTotalChartAvailable:boolean = false;

  isEdcChartAvailable:boolean = false;
  browserObject: String =  navigator.userAgent;
  isChrome:boolean= false;
  
  gsRep:any=[];
  totalWise:any=[];
  edcWise:any=[];
  mm:any=[];
  searchYear: string = "";
  monthName: string ="";
  monthNo: string="";
  

  constructor(private router: Router, private route: ActivatedRoute, 
    private service: LoginService,private commonUtils: CommonUtils,
    private commonService:CommonService) {
    this.user = Object.assign({},{
      userName:'', 
      password:''
    });

    
  }

 
  ngOnInit() {

    this.service.getAllGsRepSummary().subscribe(x=>{
      this.gsRep=x;
      this.gsRep.forEach(element=>{
        if(element.repType=="total"){
          this.totalWise.push(element);
        }
        if(element.repType=="edc-summary"){
          this.edcWise.push(element);
        }
      });
    this.searchYear = this.commonUtils.getCurrentYear();
    this.monthNo = environment.month;
    this.mm = this.commonService.fetchMonths();
    this.mm.forEach(element => {
      if(element.value==environment.month){
        this.monthName=element.name;
      }
    });
      this.populateChart(this.totalWise,this.edcWise,this.monthNo,this.monthName,this.searchYear);
    })
    
  //   this.isChrome = (this.browserObject.search('Chrome') >0 ) 
  //   // console.log("this.isChrome-"+this.isChrome );

     
  //   if(!this.isChrome){
  //     alert("Please use Google Chrome browser!!!");
  // }
    // this.service.getTotalGenerator().subscribe(x=>{
      // console.log(x)
      // this.totalGenerators=x;
      
      
    // });

  
    
    // this.service.getEdcGenerator().subscribe(x=>{
      // console.log(x)
      // this.edcGenerators =x
    
      // console.log(this.edcWise)
      
      // console.log(this.isEdcChartAvailable)
    // })
     
    this.route.queryParams.subscribe(params => {
        if(params['reason']) this.message = params['reason'];
      });
  }

  populateChart(totalWise,edcWise,monthNo,monthName,year){
    this.barChartOptions1 = new ChartOptions('Total Generator Billed Online In 2020', 'Months', "Total Generator Online vs  Billed Generator Online").barChartOptions;

      // console.log(totalWise)
      // console.log(totalWise[0])
        this.barChartLabels1 = ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun','Jul','Aug','Sep','Oct','Nov','Dec'];
        this.barChartData1 = [ {
          label: 'Total Generator',
        data: [totalWise.filter((x:any)=>x.year==year && x.month=='01')[0].totalCount,totalWise.filter((x:any)=>x.year==year && x.month=='02')[0].totalCount,totalWise.filter((x:any)=>x.year==year && x.month=='03')[0].totalCount,totalWise.filter((x:any)=>x.year==year && x.month=='04')[0].totalCount,totalWise.filter((x:any)=>x.year==year && x.month=='05')[0].totalCount,totalWise.filter((x:any)=>x.year==year && x.month=='06')[0].totalCount]
      },
        { 
          label: 'Billed Generator',
        data: [totalWise.filter((x:any)=>x.year==year && x.month=='01')[0].captiveTppCount,totalWise.filter((x:any)=>x.year==year && x.month=='02')[0].captiveTppCount,totalWise.filter((x:any)=>x.year==year && x.month=='03')[0].captiveTppCount,totalWise.filter((x:any)=>x.year==year && x.month=='04')[0].captiveTppCount,totalWise.filter((x:any)=>x.year==year && x.month=='05')[0].captiveTppCount,totalWise.filter((x:any)=>x.year==year && x.month=='06')[0].captiveTppCount]
        }      ];
       this.isTotalChartAvailable=true;

        // bar chart 2
        // console.log(edcWise)
        // console.log(edcWise[0])
       this.barChartOptions2 = new ChartOptions('Total Generator Billed Online Edc wise '+ monthName + ' -' + year, '', "Total Generator Online vs  Billed Generator Online").barChartOptions;

      this.barChartLabels2 = ['Coimbatore (South)', 'Udumalpet', 'Palladam', 'Dindugul', 'Tuticorin', 'Tirunelveli', 'Kanniyakumari', 'Theni', 'Ramnad'];
        this.barChartData2 = [ {
          label: 'Total Generator',
          data: [edcWise.filter((x:any)=>x.year==year && x.month==monthNo && x.orgName=='COIMBATORE SOUTH')[0].totalSellerCount,edcWise.filter((x:any)=>x.year==year && x.month==monthNo && x.orgName=='UDUMALPET')[0].totalSellerCount,edcWise.filter((x:any)=>x.year==year && x.month==monthNo && x.orgName=='PALLADAM')[0].totalSellerCount,edcWise.filter((x:any)=>x.year==year && x.month==monthNo && x.orgName=='DINDIGUL')[0].totalSellerCount,
          edcWise.filter((x:any)=>x.year==year && x.month==monthNo && x.orgName=='TUTICORIN')[0].totalSellerCount,edcWise.filter((x:any)=>x.year==year && x.month==monthNo && x.orgName=='TIRUNELVELI')[0].totalSellerCount,edcWise.filter((x:any)=>x.year==year && x.month==monthNo && x.orgName=='KANYAKUMARI')[0].totalSellerCount,edcWise.filter((x:any)=>x.year==year && x.month==monthNo && x.orgName=='THENI')[0].totalSellerCount,
          edcWise.filter((x:any)=>x.year==year && x.month==monthNo && x.orgName=='RAMNAD')[0].totalSellerCount] 
        },
        { 
          label: 'Billed Generator',
          data: [edcWise.filter((x:any)=>x.year==year && x.month==monthNo && x.orgName=='COIMBATORE SOUTH')[0].billedSellerCount,edcWise.filter((x:any)=>x.year==year && x.month==monthNo && x.orgName=='UDUMALPET')[0].billedSellerCount,edcWise.filter((x:any)=>x.year==year && x.month==monthNo && x.orgName=='PALLADAM')[0].billedSellerCount,edcWise.filter((x:any)=>x.year==year && x.month==monthNo && x.orgName=='DINDIGUL')[0].billedSellerCount,
          edcWise.filter((x:any)=>x.year==year && x.month==monthNo && x.orgName=='TUTICORIN')[0].billedSellerCount,edcWise.filter((x:any)=>x.year==year && x.month==monthNo && x.orgName=='TIRUNELVELI')[0].billedSellerCount,edcWise.filter((x:any)=>x.year==year && x.month==monthNo && x.orgName=='KANYAKUMARI')[0].billedSellerCount,edcWise.filter((x:any)=>x.year==year && x.month==monthNo && x.orgName=='THENI')[0].billedSellerCount,
          edcWise.filter((x:any)=>x.year==year && x.month==monthNo && x.orgName=='RAMNAD')[0].billedSellerCount] }]
       this.isEdcChartAvailable=true;

  }

  login() {

     // CryptoJS.AES.encryp
    const key = CryptoJS.enc.Utf8.parse(environment.cryptoKey);
    // const iv = CryptoJS.enc.Utf8.parse('openaccesstneb12');
    const encryptedPassword=CryptoJS.AES.encrypt(this.user.password, key, {
          keySize: 16,
          // iv: iv,
          mode: CryptoJS.mode.ECB,
          padding: CryptoJS.pad.Pkcs7
        }).toString(); 
   
    this.user.password=encryptedPassword;
     
  
    if (!this.user.userName) {
      this.message = "UserName is mandatory";
      return false;
    }
    else {
      if (!this.user.password) {
        this.message = "Password is mandatory";
        return false;
      }
    }

    this.logging = true;
    //-------------------------------------------------------unique id for security audit purpose------------
    const myId = uuid.v4();
   
    this.user.uniqueId = myId;
   // const key = CryptoJS.enc.Utf8.parse(environment.cryptoKey);
    const encryptedUID=CryptoJS.AES.encrypt(this.user.uniqueId, key, {
      keySize: 16,
      // iv: iv,
      mode: CryptoJS.mode.ECB,
      padding: CryptoJS.pad.Pkcs7
    }).toString(); 

    this.user.uniqueId = encryptedUID;
    this.service.login(this.user).subscribe(
      result => {
        //this.user._id = result;
        this.authUser = result;

        console.log(this.authUser.isSuperUser);
        
      
        this.authUser.isSuperUser=atob(this.authUser.isSuperUser);
        if(this.authUser.id != 'null' && this.authUser.id != null){
        this.authUser.id=atob(this.authUser.id);
        }
         if(this.authUser.firstName != 'null' && this.authUser.firstName != null){
         this.authUser.firstName=atob(this.authUser.firstName);
         }
         if(this.authUser.lastName != 'null' && this.authUser.lastName != null){
         this.authUser.lastName=atob(this.authUser.lastName);
         }
         if(this.authUser.userName != 'null' && this.authUser.userName != null){
         this.authUser.userName=atob(this.authUser.userName);
         }
         if(this.authUser.password != 'null' && this.authUser.password != null){
         this.authUser.password=atob(this.authUser.password);
         }
         
         if(this.authUser.systemKeyCode != 'null' && this.authUser.systemKeyCode != null){
         this.authUser.systemKeyCode=atob(this.authUser.systemKeyCode);
         }
         if(this.authUser.systemRefKey != 'null' && this.authUser.systemRefKey != null){
         this.authUser.systemRefKey=atob(this.authUser.systemRefKey);
         }
 
         if(this.authUser.edcCode != 'null' && this.authUser.edcCode != null){
         this.authUser.edcCode=atob(this.authUser.edcCode);
         }
         if(this.authUser.companyId != 'null' && this.authUser.companyId != null){
         this.authUser.companyId= atob(this.authUser.companyId);
         }
         if(this.authUser.token != 'null' && this.authUser.token != null){
         this.authUser.token=atob(this.authUser.token);
         }
         if(this.authUser.companyServiceId != 'null' && this.authUser.companyServiceId != null){
         this.authUser.companyServiceId=atob(this.authUser.companyServiceId);
         }
         if(this.authUser.loginStopMessage != 'null' && this.authUser.loginStopMessage != null){
           this.authUser.loginStopMessage=atob(this.authUser.loginStopMessage);
           }
  
       
       
        
        if(this.authUser.isSuperUser.includes("Y")){
 
         this.authUser.isSuperUser='Y';
 
        }
        if(this.authUser.isSuperUser.includes("N")){
 
         this.authUser.isSuperUser='N';
 
        }
 
        this.authUser.accessList.forEach(function(value){
 
         if(value.feature != 'null' && value.feature != null){
           value.feature=atob(value.feature);
           }
         if(value.functionality != 'null' && value.functionality != null){
             value.functionality=atob(value.functionality);
             }
         if(value.orgType != 'null' && value.orgType != null){
               value.orgType=atob(value.orgType);
               }
         if(value.systemKey != 'null' && value.systemKey != null){
                 value.systemKey=atob(value.systemKey);
                 }
 
 
        });



        if(this.authUser.loginStopMessage!=null && this.authUser.loginStopMessage!=''){

          this.logging = false;
          this.message = this.authUser.loginStopMessage;
        }
        else{
          this.message = "Login Successfull";
          this.service.setToken(this.authUser);
          this.openDash();
        }
      },

      error => {
        this.logging = false;
        if (JSON.parse(error._body).message) {
          this.message = JSON.parse(error._body).message;
        } else {
          this.message = "System Issue: Please contact system administrator!"

        }

      }
    );
  }
  

  openDash() {
    //this.router.navigate ( [ '/dashboard' ] );
     this.router.navigateByUrl('/home');
  }
}
