import 'rxjs/add/operator/switchMap';
import { Component, OnInit, HostBinding, ViewChild } from '@angular/core';
import { Router, ActivatedRoute, Params } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { MatStepperModule } from '@angular/material';
import { DatePipe } from '@angular/common';
import { CsService } from './../../services/cs.service';
import { CsEvent } from './../../services/cs.event';
import { CommonService } from './../../../../shared/common/common.service';
import { Cs} from './../../../vo/cs'; 


@Component({
  selector: 'cs-detail',
  templateUrl: './cs-detail.component.html',
  providers: [CommonService, CsService,DatePipe]
})

export class CsDetailComponent {
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
  
  cs: Cs;
  disableControls: boolean = true;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private commonService: CommonService,
    private service: CsService,
  private csEvent: CsEvent,
  private datePipe: DatePipe
  ) {}

  ngOnInit() {
    this.cs = new Cs();
  //   this.csEvent.cs$.subscribe(latestValue =>{ 
  //    this.cs =  latestValue;
  //   });
  //  this.csEvent.load();
  this.service.csEvent.subscribe(_cs =>{ 
    this.cs =  _cs;
   });
   
   //this.stepper._selectedIndex  = 1;

    if (this.route.params['_value']['_id']) {
      this.addScreen = false;
      this.route.params
        .switchMap((params: Params) => this.service.getCsById(this.route.params['_value']['_id']))
        .subscribe((_cs: Cs) => {
          this.cs = _cs;
          this.formatChangesForUI();
          this.step1Disable = true;
        
          
        });
    }else{
		this.step1Disable = false;
	}

  }
  
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
      this.addCs();
    }
    else {
      this.updateCs();
    }
  }

  addCs() {
    this.formatChangesforDB();
    this.service.addCs(this.cs).subscribe(
      result => {
       this.router.navigateByUrl('cs/cs-edit/'+result);
   

      },
      error => {
        console.error('Error adding Cs!');
        console.error(error);
      }
    );
  }

  updateCs() {
    console.log("Update Cs");
    console.log(this.cs);
    this.service.updateCs(this.cs).subscribe(
      result => {
        this.router.navigateByUrl('cs/cs-list');
      },
      error => {
        console.error('Error updating Cs!');
        console.error(error);
      }
    );
  }

  listCs() {
    this.router.navigateByUrl('/cs/cs-list');
  }

  completeCs() {
    this.service.csEvent.subscribe(_cs =>{ 
      this.cs =  _cs;
     });
     console.log("InCompleteCs")
   console.log(this.cs)  
    this.service.completeCs(this.cs).subscribe(
      result => {
        this.listCs();
      },
      error => {
        console.error('Error completing cs!');
        console.error(error);
      }
    );
  }

  approveCs() {
    this.service.approveCs(this.cs).subscribe(
      result => {
        this.listCs();
      },
      error => {
        console.error('Error approving Cs!');
        console.error(error);
      }
    );
  }

  formatChangesForUI(){
    this.cs.effectiveDate = (this.cs.effectiveDate) ? this.cs.effectiveDate.substr(0, 10) : "";
  }

  formatChangesforDB() {
    
        this.cs.effectiveDate = this.datePipe.transform(this.cs.effectiveDate, 'yyyy-MM-dd');

        
      }
    
}