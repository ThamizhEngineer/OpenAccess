import 'rxjs/add/operator/switchMap';
import { Component, OnInit, HostBinding } from '@angular/core';
import { Router, ActivatedRoute, Params } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { MatDatepickerModule} from '@angular/material';
import { MatNativeDateModule} from '@angular/material';
import { DatePipe } from '@angular/common';
import { GsEvent } from './../../services/gs.event'; 
import { GsService } from './../../services/gs.service'; 
import { Gs,GenerationStatementCharge,GenerationStatementSlot,GenerationStatementImportSlotForPrint,GenerationStatementExportSlotForPrint,GenerationSummary} from './../../../vo/gs'; 
import { CommonService } from './../../../../shared/common/common.service'; 
import { NativeDateAdapter, DateAdapter, MAT_DATE_FORMATS } from "@angular/material";

import { saveAs } from 'file-saver';

export class AppDateAdapter extends NativeDateAdapter {
  parse(value: any): Date | null {
   if ((typeof value === 'string') && (value.indexOf('/') > -1)) {
     const str = value.split('/');
     const year = Number(str[2]);
     const month = Number(str[1]) - 1;
     const date = Number(str[0]);
     return new Date(year, month, date);
   }
   const timestamp = typeof value === 'number' ? value : Date.parse(value);
   return isNaN(timestamp) ? null : new Date(timestamp);
 }

  format(date: Date, displayFormat: Object): string {
      if (displayFormat == "input") {
          let day = date.getDate();
          let month = date.getMonth() + 1;
          let year = date.getFullYear();
          return this._to2digit(day) + '/' + this._to2digit(month) + '/' + year;
      } else {
          return date.toDateString();
      }
  }

  private _to2digit(n: number) {
      return ('00' + n).slice(-2);
  } 
}

export const APP_DATE_FORMATS =
{
  parse: {
      dateInput: {month: 'short', year: 'numeric', day: 'numeric'}
  },
  display: {
      // dateInput: { month: 'short', year: 'numeric', day: 'numeric' },
      dateInput: 'input',
      monthYearLabel: { month: 'short', year: 'numeric', day: 'numeric' },
      dateA11yLabel: {year: 'numeric', month: 'long', day: 'numeric'},
      monthYearA11yLabel: {year: 'numeric', month: 'long'},
  }
}




@Component({
  selector: 'gs-detail',
  templateUrl: './gs-detail.component.html',
  styleUrls: ['./gs-detail.component.scss'],
  providers: [ MatDatepickerModule,MatNativeDateModule,DatePipe,
    {
   provide: DateAdapter, useClass: AppDateAdapter
},
{
   provide: MAT_DATE_FORMATS, useValue: APP_DATE_FORMATS
}
]
})

export class GsDetailComponent implements OnInit {
  gs:Gs;
  generationStatementCharges:GenerationStatementCharge;
  generationStatementImportChargesForPrint:Array<GenerationStatementImportSlotForPrint>;
  generationStatementExportChargesForPrint:Array<GenerationStatementExportSlotForPrint>;
  generationSummaryForPrint:Array<GenerationSummary>;
  slot1:GenerationStatementSlot;
  slot2:GenerationStatementSlot;
  slot3:GenerationStatementSlot;
  slot4:GenerationStatementSlot;
  slot5:GenerationStatementSlot;
  // gsSlot:GsSlot;
  viewType: string;
  gsChargesRowIndex:number;
  months= [];
  disableControls: boolean = true;
  bankingSlot1:any;
  bankingSlot2:any;
  bankingSlot3:any;
  bankingSlot4:any;
  bankingSlot5:any;

  importDifference:any;
  exportDifference:any;
  
  isCaptives = [
    { "key": "Y", "name": "WHEELING" },
    { "key": "N", "name": "SELL-TO-BOARD" }
  ]

  mrSourceCodes = [
    { "key": "01", "name": "AMR READING" },
    { "key": "02", "name": "MANUAL READING" },
    { "key": "03", "name": "CMRI READING" }
  ]
  message: string;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private service: GsService,
    private commonService: CommonService,
    private gsEvent: GsEvent,
    private datePipe: DatePipe
  ) {
    this.initGsCharges();
  }

  ngOnInit() {
   this.gs = new Gs();

   this.generationStatementCharges =  new GenerationStatementCharge();
   this.slot1 =  new GenerationStatementSlot();
   this.slot2 =  new GenerationStatementSlot();
   this.slot3 =  new GenerationStatementSlot();
   this.slot4 =  new GenerationStatementSlot();
   this.slot5 =  new GenerationStatementSlot();
   this.generationStatementImportChargesForPrint =[];
   this.generationStatementExportChargesForPrint =[];
   this.generationSummaryForPrint =[];
   this.months = this.commonService.fetchMonths();
  //  this.gsEvent.gs$.subscribe(latestValue =>{ 
  //    this.gs =  latestValue;
  //   });
  //  this.gsEvent.load();
 
    if(this.route.params['_value']['_id']){
      //route.params['value'] will have the '_id' in it, during edit 
     
      this.route.params
            .subscribe((_params: Params) => {
              this.service.getGsById(_params['_id']).subscribe(_gs=>{
                this.gs = _gs;
                this.GenerationStatementCharges();
                this.formatChangesforUI();
                this.slot1 =this.gs.generationStatementSlots.filter(x => x.slotCode == 'C1')[0];
                this.slot2 =this.gs.generationStatementSlots.filter(x => x.slotCode == 'C2')[0];
                this.slot3 =this.gs.generationStatementSlots.filter(x => x.slotCode == 'C3')[0];
                this.slot4 =this.gs.generationStatementSlots.filter(x => x.slotCode == 'C4')[0];
                this.slot5 =this.gs.generationStatementSlots.filter(x => x.slotCode == 'C5')[0];

                console.log(JSON.stringify(this.gs));
                console.log(this.gs);
                console.log("Charge Desc");
                this.bankingDetails();
                this.generationStatementImportForPrint();
                this.generationStatementExportForPrint();
                this.generationSummayForPrint();
              
                
                
              
              });
         
            }); 
    }
  }
  formatChangesforUI() {
    this.gs.statementGenerationDate  =  (this.gs.statementGenerationDate) ? this.gs.statementGenerationDate.substr(0,10):"";
    this.gs.initStatementDate =  (this.gs.initStatementDate) ? this.gs.initStatementDate.substr(0,10):"";
    this.gs.finalStatementDate  = (this.gs.finalStatementDate) ? this.gs.finalStatementDate.substr(0,10):"";
}

  formatChangesforDB(){
    
    this.gs.statementGenerationDate  =  this.datePipe.transform(this.gs.statementGenerationDate, 'yyyy-MM-dd');
    this.gs.initStatementDate =  this.datePipe.transform(this.gs.initStatementDate, 'yyyy-MM-dd');
    this.gs.finalStatementDate  =  this.datePipe.transform(this.gs.finalStatementDate, 'yyyy-MM-dd');
   
    }
    generationStatementImportForPrint(){
      this.generationStatementImportChargesForPrint.push({"heading":"Final Reading", 
        "c1": this.slot1.impFinal,  
        "c2": this.slot2.impFinal,
        "c3": this.slot3.impFinal,    
        "c4": this.slot4.impFinal,    
        "c5": this.slot5.impFinal,    
      });
      this.generationStatementImportChargesForPrint.push({"heading":"Initial Reading", 
        "c1": this.slot1.impInitial,  
        "c2": this.slot2.impInitial,
        "c3": this.slot3.impInitial,    
        "c4": this.slot4.impInitial,   
        "c5": this.slot5.impInitial,   
        });
      this.generationStatementImportChargesForPrint.push({"heading":"Difference", 
        "c1": this.slot1.impDifference,  
        "c2": this.slot2.impDifference,
        "c3": this.slot3.impDifference,    
        "c4": this.slot4.impDifference,   
        "c5": this.slot5.impDifference,  
        });
      this.generationStatementImportChargesForPrint.push({"heading":"Units", 
        "c1": this.slot1.impUnits,  
        "c2": this.slot2.impUnits,
        "c3": this.slot3.impUnits,    
        "c4": this.slot4.impUnits,    
        "c5": this.slot5.impUnits,    
      });
  
    }

    generationStatementExportForPrint(){
      this.generationStatementExportChargesForPrint.push({"heading":"Final Reading", 
        "c1": this.slot1.expFinal, 
        "c2": this.slot2.expFinal,  
        "c3": this.slot3.expFinal, 
        "c4": this.slot4.expFinal,
        "c5": this.slot5.expFinal, 
      });
      this.generationStatementExportChargesForPrint.push({"heading":"Initial Reading", 
        "c1": this.slot1.expInitial,   
        "c2": this.slot2.expInitial, 
        "c3": this.slot3.expInitial,  
        "c4": this.slot4.expInitial,  
        "c5": this.slot5.expInitial, 
       });
      this.generationStatementExportChargesForPrint.push({"heading":"Difference", 
        "c1": this.slot1.expDifference, 
        "c2": this.slot2.expDifference,
        "c3": this.slot3.expDifference,
        "c4": this.slot4.expDifference,   
        "c5": this.slot5.expDifference,   
      });
      this.generationStatementExportChargesForPrint.push({"heading":"Units", 
        "c1": this.slot1.expUnits, 
        "c2": this.slot2.expUnits,  
        "c3": this.slot3.expUnits, 
        "c4": this.slot4.expUnits, 
        "c5": this.slot5.expUnits,
      });
    }

    generationSummayForPrint(){
      this.generationSummaryForPrint.push({"heading":"Initial",
        "rKvah":this.gs.rKvahInitial,
        "kVah":this.gs.kVahInitial,
        "importGeneration":"NA",
        "exportGeneration":"NA",
      });
      this.generationSummaryForPrint.push({"heading":"Final",
        "rKvah":this.gs.rKvahFinal,
        "kVah":this.gs.kVahFinal,
        "importGeneration":"NA",
        "exportGeneration":"NA",
      });
      this.generationSummaryForPrint.push({"heading":"Difference",
        "rKvah":this.gs.rKvahDifference,
        "kVah":this.gs.kVahDifference,
        "importGeneration":this.importDifference,
        "exportGeneration":this.exportDifference,
      });
    this.generationSummaryForPrint.push({"heading":"Units",
      "rKvah":this.gs.rKvahUnits,
      "kVah":this.gs.kVahUnits,
      "importGeneration":this.gs.totalImportGeneration,
      "exportGeneration":this.gs.totalExportGeneration,
     });  

    }

  bankingDetails(){
    this.bankingSlot1 = this.slot1.bankedBalance;
    this.bankingSlot2 = this.slot2.bankedBalance;
    this.bankingSlot3 = this.slot3.bankedBalance;
     this.bankingSlot4 = this.slot4.bankedBalance;
     this.bankingSlot5 = this.slot5.bankedBalance;
    this.importDifference = parseInt(this.slot1.impDifference)+parseInt(this.slot2.impDifference)+
      parseInt(this.slot3.impDifference)+parseInt(this.slot4.impDifference)+parseInt(this.slot5.impDifference);
    this.exportDifference = parseInt(this.slot1.expDifference)+parseInt(this.slot2.expDifference)+
      parseInt(this.slot3.expDifference)+parseInt(this.slot4.expDifference)+parseInt(this.slot5.expDifference);
  }

  listGss() {
      this.router.navigateByUrl('/gs/gs-list');
      // to-do
      // this still needs to be fixed as giving '/order' is unecessary maintenance hazzle   
    }
    
    initGsCharges(){  
      this.generationStatementCharges =  new GenerationStatementCharge();
      console.log(this.generationStatementCharges);
      //this.GenerationStatementCharge();
      //this.generationStatementCharges.push({"netPayable": });
     //this.gsChargesRowIndex = -1;
    }

    // if(gs.isStb !='N'")
    
    GenerationStatementCharges()
    {
      if(this.gs.isStb !='N')
      {
      this.gs.generationStatementCharges.push({"chargeDesc":"Net Payable Charges","totalCharges":this.gs.netPayable});
      console.log("this.GenerationStatementCharges()");
      console.log(this.gs.generationStatementCharges);
      }
      else 
      {

      }
    }
   
	printPage(){
		// var originalContents = document.body.innerHTML;
		// var printReport= document.getElementById('printSection').innerHTML;
		// document.body.innerHTML = printReport;
		// window.print();
    // document.body.innerHTML = originalContents;
   
    
    this.service.printGs(this.gs);
    this.service.printGs(this.gs).subscribe(x=>{
			this.openFileForDownload(x);
		});
  }
  openFileForDownload(data: Response) {
		let content_type = data.headers.get('Content-type');
		let x_filename = "generation-statement"+this.gs.dispCompanyServiceNumber+this.gs.statementMonth+this.gs.statementYear+".pdf";
		
		saveAs(data.blob(), x_filename);
  }

  openDownload(data: Response) {
		let content_type = data.headers.get('Content-type');
		let x_filename = "generation-statement"+this.gs.dispCompanyServiceNumber+this.gs.statementMonth+this.gs.statementYear+".pdf";
		console.log(content_type)
		saveAs(data.blob(), x_filename);
  }
  printGenstmt(){
    // this.service.printGen(this.gs).subscribe(
    //   result => {
    //     this.listGss();
    //   },
    //   error => {
    //     console.error('error generation pdf');
    //     console.error(error);
    //   }
    // );
    // console.log("gs");
    // console.log(this.gs);

    // this.service.printGen(this.gs);
    this.service.printGen(this.gs).subscribe(xs=>{
      this.openDownload(xs);
      this.message = "Downloaded Successfully"
      console.log("message :",this.message)
    },
    error =>{
      if (JSON.parse(error._body).message) {
        this.message = JSON.parse(error._body).message;
      } else {
        this.message = "Too many request please try again later"
      }
      console.log("message :",this.message)
    });

  }

}
