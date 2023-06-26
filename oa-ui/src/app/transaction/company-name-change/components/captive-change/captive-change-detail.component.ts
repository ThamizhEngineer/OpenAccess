import 'rxjs/add/operator/switchMap';
import { Component, OnInit, HostBinding, ViewChild } from '@angular/core';
import { Router, ActivatedRoute, Params } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { MatStepperModule } from '@angular/material';
import { DatePipe } from '@angular/common';
import { CompanyNameChangeService } from './../../services/company-name-change.service';

import { CommonService } from './../../../../shared/common/common.service';
import { CompanyNameChange} from './../../../vo/company-name-change'; 


@Component({
  selector: 'captive-change-detail',
  templateUrl: './captive-change-detail.component.html',
  providers: [CommonService, CompanyNameChangeService,DatePipe]
})

export class CaptiveChangeDetailComponent {
  @ViewChild('stepper') stepper;
  addScreen: boolean = true;
  step1Disable: boolean = true;
  step2Disable: boolean = true;
  step3Disable: boolean = true;
  step4Disable: boolean = true;
  step5Disable: boolean = true;
  step6Disable: boolean = true;
  step1: boolean = false;
  step2: boolean = false;
  step3: boolean = false;
  step4: boolean = false;
  step5: boolean = false;
  step6: boolean = false;
  
  companyNameChange: CompanyNameChange;
  disableControls: boolean = true;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private commonService: CommonService,
    private service: CompanyNameChangeService,

  private datePipe: DatePipe
  ) {}

  ngOnInit() {
    this.companyNameChange = new CompanyNameChange();
  //   this.csEvent.cs$.subscribe(latestValue =>{ 
  //    this.cs =  latestValue;
  //   });
  //  this.csEvent.load();
  this.service.companyNameChangeEvent.subscribe(_companyNameChange =>{ 
    this.companyNameChange =  _companyNameChange;
   });
   
   //this.stepper._selectedIndex  = 1;
   this.route.queryParams.filter(params=>params.flowType)
   .subscribe(params=>{
  
     this.companyNameChange.flowTypeCode=params.flowType;
     
   })
   this.route.queryParams.filter(params=>params.id)
   .switchMap((params: Params) => this.service.getCompanyNameChangeById(params.id))
  .subscribe(_companyNameChange=>{
          this.companyNameChange = _companyNameChange;
          // this.formatChangesForUI();
          this.step1Disable = true;
        
          
        });
    }
  //   else{
	// 	this.step1Disable = false;
	// }

  // }
  
  nextStep(){
    var si = this.stepper._selectedIndex + 1;
    console.log(si);
    this['step'+si+'Disable'] = true;
    console.log(this['step'+si+'Disable']);
	  si++;
    this['step'+si] = true;
    console.log(this['step'+si]);
    this['step'+si+'Disable'] = false;
    console.log(this['step'+si+'Disable']);
 
	  this.stepper.next();
  }

  prevStep(){
	this.stepper.previous();
  }
  
  saveCs() {
    if (this.addScreen) {
      this.addCompanyNameChange();
    }
    else {
      this.updateCompanyNameChange();
    }
  }

  addCompanyNameChange() {
    // this.formatChangesforDB();
    this.companyNameChange.statusCode="CREATED";
    this.companyNameChange.oldCompanyName = this.companyNameChange.companyName;
    this.companyNameChange.flowTypeCode="CAPTIVE-CHANGE";
    console.log("In add company name in captive change");
    console.log(this.companyNameChange);
    this.service.addCompanyNameChange(this.companyNameChange).subscribe(
      result => {
        console.log(result);
        this.router.navigate(['/company-name-change/captive-change-edit'] ,{queryParams:{id:result}});
   

      },
      error => {
        console.error('Error adding Cs!');
        console.error(error);
      }
    );
  }

  updateCompanyNameChange() {
    console.log("Update Cs");
  
    this.service.updateCompanyNameChange(this.companyNameChange).subscribe(
      result => {
      this.listCompanyNameChange();
      },
      error => {
        console.error('Error updating Cs!');
        console.error(error);
      }
    );
  }

  listCompanyNameChange() {
    this.router.navigateByUrl('/company-name-change/company-name-change-list');
  }

  // completeCs() {
  //   this.service.csEvent.subscribe(_cs =>{ 
  //     this.cs =  _cs;
  //    });
  //    console.log("InCompleteCs")
  //  console.log(this.cs)  
  //   this.service.completeCs(this.cs).subscribe(
  //     result => {
  //       this.listCs();
  //     },
  //     error => {
  //       console.error('Error completing cs!');
  //       console.error(error);
  //     }
  //   );
  // }

  approveCompanyNameChange() {
    this.service.approveCompanyNameChange(this.companyNameChange).subscribe(
      result => {
        alert("Please Create New Energy Sale Intent!!!");
        this.listCompanyNameChange();
      },
      error => {
        console.error('Error approving Cs!');
        console.error(error);
      }
    );
  }

  // formatChangesForUI(){
  //   this.cs.effectiveDate = (this.cs.effectiveDate) ? this.cs.effectiveDate.substr(0, 10) : "";
  // }

  // formatChangesforDB() {
    
  //       this.cs.effectiveDate = this.datePipe.transform(this.cs.effectiveDate, 'yyyy-MM-dd');

        
  //     }
    
}