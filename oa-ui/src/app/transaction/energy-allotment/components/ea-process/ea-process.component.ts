import {Component, Input,OnInit} from '@angular/core'
import { CommonService } from '../../../../shared/common/common.service'; 
import { CommonUtils } from '../../../../shared/common/common-utils';
import { EaService } from '../../services/ea.service';
import { Gs ,ValidateGs} from '../../../vo/gs';
import { Router, ActivatedRoute, Params } from '@angular/router';
import {FormBuilder, FormGroup, Validators, FormArray} from '@angular/forms';
import { ColumnMode } from '@swimlane/ngx-datatable';
import { Es } from './../../../vo/es';
import { MatStepper } from '@angular/material/stepper';
import { Ea } from '../../../vo/ea';


@Component({
  selector: 'ea-process',
  templateUrl: './ea-process.component.html',
  styleUrls: ['./ea-process.component.scss'],
  providers:[CommonUtils]
})

export class EaProcessComponent implements OnInit {

// --- Forms
    ColumnMode = ColumnMode;
    isLinear = false;
    firstFormGroup: FormGroup;
    secondFormGroup: FormGroup;
    thirdFormGroup: FormGroup;

// --- Validations
    id:any;
    isValidServiceDetail:String="N";
    isValidTypeDetails:String="N";
    isValidCharges:String="N";
    finalMessage:String="";
    finalMessageStatus:String="Y";
    isTimesUp:string="";
    creationStatus:string="";
    hardHidden:boolean=true;

// ---Search
    searchSellerCompanyName: string = "";
    searchCompanyServiceNumber: string = "";
    searchCompanyServiceId: string = "";
    searchMonth: string = "";
    searchYear: string = "";
    searchEdcId: string = "";
    searchsellerCompanyServiceNumber: string = "";

 //------ variables and List
    orgList = [];
    filteredCompanyList = [];
    companyServices = [];
    months = [];
    disableCompanyName: boolean = false;
    sellerCompanyServiceNumber:string="";
    disableCreate: boolean = false;
    disableEdc: boolean = false;
    isFinalDisabled: boolean = false;

//  --- Identifications
    es: Ea;
    gs: Gs;
    validateGs: ValidateGs;
    rows = [];
    gsId: string = "";

// --- Timer

    timeLeft: number = 5;
    interval;


  constructor(
  private commonService: CommonService,
  private commonUtils: CommonUtils,
  private service: EaService,
  private router: Router,
  private _formBuilder: FormBuilder
  ) { 
   
  }

  ngOnInit() {

    this.es = new Ea();
    this.gs = new Gs();
    
    this.rows = [];
    this.fetchEDCs();

    this.validateGs = new ValidateGs();
    this.months = this.commonService.fetchMonths();
    this.searchMonth=this.commonUtils.getPerviousMonth();
    this.searchYear=this.commonUtils.getpreviousYear();
   
    if(CommonUtils.getLoginUserTypeCode()=='GEN')
    {
     this.es.sellerCompanyServiceNumber = CommonUtils.getLoginUserCompany();
     this.searchEdcId = CommonUtils.getLoginUserEDC();
     if (this.es.sellerCompanyServiceNumber != "") {
       this.sellerCompanyServiceNumber = this.es.sellerCompanyServiceNumber;
       this.disableEdc = true;
       this.disableCompanyName = true;
 
     }
     //this.checkToCreateEs();
     this.searchResults();
    }
    else{
      this.fetchCompanyList();
    }
  


    this.firstFormGroup = this._formBuilder.group({
        id: '',
        statusCode:'',
        machineCapacity:'',
        statementMonth:''
      });

      this.secondFormGroup = this._formBuilder.group({
        id: '',
        statusCode:'',
        machineCapacity:'',
        statementMonth:''
      });

      this.thirdFormGroup = this._formBuilder.group({
        creationStatus: ''
      });


      var numberFromUserCheck:boolean=this.isSomeValueDefined(CommonUtils.getLoginUserCompany());
      console.log(numberFromUserCheck)
      if(CommonUtils.getLoginUserTypeCode()=='GEN')
      {
        if(numberFromUserCheck){
          var serviceNum=CommonUtils.getLoginUserCompany();
          this.es.sellerCompanyServiceNumber=serviceNum;
          console.log('Init Generator Check-'+serviceNum+'-'+CommonUtils.getLoginUserTypeCode())
        }
       this.checkToCreateEs();
       this.fetchAllGs();
      }
  
    
  }

//   ---------------------------------------------------------------------------

  createItem(vs:ValidateGs): FormGroup {
    return this._formBuilder.group({
        id:vs.id,
        statusCode:vs.statusCode,
        machineCapacity:vs.machineCapacity,
        statementMonth:vs.statementMonth
    });
  }

  addItem(gs:Gs,stepper: MatStepper): void{
       this.validateGs.id= gs.id;
       this.validateGs.statusCode= gs.statusCode;
       this.validateGs.machineCapacity= gs.machineCapacity;
       this.validateGs.statementMonth= gs.statementMonth;
    this.firstFormGroup = this.createItem(this.validateGs) 
    this.goForward(stepper); 
    this.step2(this.validateGs,stepper);
  }

//   ----------Steppers-------------------------------------------------------------

  form1(){  // -- Notused
    console.log(this.firstFormGroup.value);
  }

  
  form2(){  // -- Notused
    console.log(this.firstFormGroup.value);
    console.log(this.secondFormGroup.value);
    this.validateGs=this.firstFormGroup.value;
    console.log(this.validateGs)
    this.secondFormGroup = this.createItem(this.validateGs);
    console.log(this.secondFormGroup.value);
  
  }

  step2(vs:ValidateGs,stepper: MatStepper){
    console.log(this.firstFormGroup.value);
    console.log(this.secondFormGroup.value);
    console.log(vs);
    this.secondFormGroup = this.createItem(vs);
    console.log(this.secondFormGroup.value);
    this.formGsData(this.secondFormGroup.value.id,stepper);
  }

  step3(gsId:string){
    this.es.id=gsId;
    // this.es.netGeneration=this.gs.netGeneration;
    console.log("Step3")
    console.log(this.thirdFormGroup.value);
    this.CreateEs(this.es);
    this.thirdFormGroup.value.creationStatus="Energy allotment creation initiated";
  }


// -- Stepper Actions----------------------------------------------------------

  goBack(stepper: MatStepper){
    stepper.previous();
    }

  goForward(stepper: MatStepper){
    stepper.next();
    }

  // fetchs--------------------------------------------------------------------

  checkToCreateEs(){
    if((this.searchMonth!=null && this.searchMonth!=undefined &&this.searchMonth!="")&&(this.searchYear!=null && this.searchYear!=undefined&&this.searchYear!="")){
      this.service.getAllEss('',this.es.sellerCompanyServiceNumber,'','','','',this.searchMonth, this.searchYear,'','').subscribe(
        _es => {
         _es.forEach(element => {
          this.es.generationStatementId=element.generationStatementId;
          console.log("In search Loop result")
          console.log(this.es.generationStatementId);
          });;
        });
    }
  }
  
  fetchAllGs(){

    if(this.searchMonth!='' && this.searchYear !=''){
      if(!(this.searchMonth==this.commonUtils.getPerviousMonth() && this.searchYear==this.commonUtils.getpreviousYear())){
        alert("Only generation statement from "+this.commonUtils.getPerviousMonth()+"-"+this.commonUtils.getpreviousYear()+" are eligible for allocation")
        if(CommonUtils.getLoginUserTypeCode()=='A'){
          alert("However being an administrator, you may proceed");
        }else{
          return false;
        }
      }
    }
    this.searchMonth=this.commonUtils.getPerviousMonth();
    this.searchYear=this.commonUtils.getpreviousYear();

    if(CommonUtils.getLoginUserTypeCode()=='GEN'){
      if ((this.isSomeValueDefined(this.es.sellerCompanyServiceNumber))==true) {
        this.searchsellerCompanyServiceNumber = this.es.sellerCompanyServiceNumber;
        console.log(this.searchsellerCompanyServiceNumber)
      }
    }
    this.service.getGsByAllocation(this.searchCompanyServiceId, this.searchsellerCompanyServiceNumber,this.searchSellerCompanyName, this.searchMonth, this.searchYear, this.searchEdcId).subscribe(
      _gs => {
        this.rows = _gs;
        // console.log(this.rows)
      })
}  


  // -------------Validate-------------------------------------------------

  formGsData(_id: string,stepper: MatStepper){
    this.service.getGsById(_id).subscribe(_gs=>{
      this.gs = _gs;

      console.log("form gs data")
      console.log(this.gs)

      if(this.gs.dispOrgName!=null && this.gs.dispCompanyServiceNumber!=null){
        this.isValidServiceDetail="Y";
      }

      if(this.gs.generationStatementCharges!=null || this.gs.generationStatementCharges!=undefined){
        this.isValidCharges="Y";
      }

      if(this.finalMessageStatus=="Y"){
        this.finalMessage="Valid Data. Processing data for allotment"
        this.startTimer(stepper,_id);        
      }else{
        this.finalMessage="Data is not valid. Cannot be processed"

      }
    });
  }



  // Create---------------------------------------------------------------------

CreateEs(gs: Gs) {
  console.log(gs.id)
  console.log(gs.netGeneration)
  this.es.generationStatementId = gs.id;
  // this.es.netGeneration=gs.netGeneration;  
  this.service.addNewEa(this.es).subscribe(
    result => {
      this.thirdFormGroup.value.creationStatus="Energy allotment creation successful";
      this.editEa(result);
    },
    error => {  
      console.error('Error Creating Energy allocation!');
      this.thirdFormGroup.value.creationStatus="Energy allotment creation was not successful";
      console.error(error);
    }
  );
}


  // ---- Timer ------ Null check 

  startTimer(stepper: MatStepper,_id:string) {
    this.interval = setInterval(() => {
      if(this.timeLeft > 0) {
        this.timeLeft--;
        console.log(this.timeLeft)
        this.isTimesUp="N";
        console.log(this.isTimesUp)
      } else {
        // this.timeLeft = 60;
        console.log(this.timeLeft)
        this.isTimesUp="Y";
        this.goForward(stepper); 
        clearInterval(this.interval);
        this.step3(_id);
      }
    },1000)
  }

  isSomeValueDefined(inValue:any): boolean {  //-- Used for null check
    if(inValue) {
      return true
    }
    return false
  }


  // --Navigate---------------------------------------------------


  editEa(_id: string) {
    try {
      console.log("editEa-"+_id);
      this.router.navigateByUrl('/ea/ea-edit/' + _id);
      // to-do
      // this still needs to be fixed as giving '/order' is unecessary maintenance hazzle

    }
    catch (e) {
      console.log(e);
    }

  }


  // --Filters --------------------------

  fetchEDCs() { 
    this.commonService.fetchEDCs().subscribe(
      result => {
        this.orgList = result;
      },
      error => {
        console.error('Error loading orgs!');
        console.error(error);
      });
  }
  
  filterCompanyList(val: string) {

    return val ? this.companyServices.filter((s) => s.dispCompanyServiceNumber.replace(/[^\w\s]/gi, '').match(new RegExp(val.replace(/[^\w\s]/gi, ''), 'gi'))) : this.companyServices;
  }

  fetchCompanyList() {
    this.service.getGsByAllocation("", "", "", "", "","").subscribe(
      _gs => {
        this.companyServices = _gs;
      }
    )

  }

 // -------- search --------
  searchResults() {
    if(CommonUtils.getLoginUserTypeCode()=='GEN'){
      if (this.es.sellerCompanyServiceNumber != "") {
        this.searchsellerCompanyServiceNumber = this.es.sellerCompanyServiceNumber;
      }
      // console.log(this.searchsellerCompanyServiceNumber)
    }
    // console.log(this.searchMonth +"------"+this.searchYear)
    if((this.searchMonth!=null && this.searchMonth!=undefined&&this.searchMonth!="")&&(this.searchYear!=null && this.searchYear!=undefined&&this.searchYear!="")){
    this.service.getGsByAllocation(this.searchCompanyServiceId, this.searchsellerCompanyServiceNumber,this.searchSellerCompanyName, this.searchMonth, this.searchYear, this.searchEdcId).subscribe(
      _gs => {
        this.rows = _gs;
      }
    )
    }

  }
 
}