import 'rxjs/add/operator/switchMap';
import { Component, OnInit, HostBinding } from '@angular/core';
import { Router, ActivatedRoute, Params } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { MatDatepickerModule} from '@angular/material';
import { MatNativeDateModule} from '@angular/material';
import { DatePipe } from '@angular/common';
import { GsEvent } from './../../services/gs.event'; 
import { GsService } from './../../services/gs.service'; 
import { Gs,MeterChangeMc,GenerationStatementCharge,GenerationStatementSlot,GenerationStatementImportSlotForPrint,GenerationStatementExportSlotForPrint,GenerationSummary} from './../../../vo/gs'; 
import { TmpGs,TmpGenerationStatementCharge,TmpGenerationStatementSlot,TmpGenerationStatementImportSlotForPrint,TmpGenerationStatementExportSlotForPrint,TmpGenerationSummary} from './../../../vo/tmp-gs'; 
import { CommonService } from './../../../../shared/common/common.service'; 
import { NativeDateAdapter, DateAdapter, MAT_DATE_FORMATS } from "@angular/material";

import { saveAs } from 'file-saver';
import { THIS_EXPR } from '@angular/compiler/src/output/output_ast';

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
  tempGsList:Array<TmpGs>;
  generationStatementCharges:GenerationStatementCharge;
  generationStatementImportChargesForPrint:Array<GenerationStatementImportSlotForPrint>;
  generationStatementExportChargesForPrint:Array<GenerationStatementExportSlotForPrint>;
  generationSummaryForPrint:Array<GenerationSummary>;
  slot1:GenerationStatementSlot;
  slot2:GenerationStatementSlot;
  slot3:GenerationStatementSlot;
  slot4:GenerationStatementSlot;
  slot5:GenerationStatementSlot;


  oldGs:TmpGs;
  oldGenerationStatementCharges:TmpGenerationStatementCharge;
  oldGenerationStatementImportChargesForPrint:Array<TmpGenerationStatementImportSlotForPrint>;
  oldGenerationStatementExportChargesForPrint:Array<TmpGenerationStatementExportSlotForPrint>;
  oldGenerationSummaryForPrint:Array<TmpGenerationSummary>;
  oldSlot1:TmpGenerationStatementSlot;
  oldSlot2:TmpGenerationStatementSlot;
  oldSlot3:TmpGenerationStatementSlot;
  oldSlot4:TmpGenerationStatementSlot;
  oldSlot5:TmpGenerationStatementSlot;

  newGs:TmpGs;
  newGenerationStatementCharges:TmpGenerationStatementCharge;
  newGenerationStatementImportChargesForPrint:Array<TmpGenerationStatementImportSlotForPrint>;
  newGenerationStatementExportChargesForPrint:Array<TmpGenerationStatementExportSlotForPrint>;
  newGenerationSummaryForPrint:Array<TmpGenerationSummary>;
  generationStatementSlots: Array<TmpGenerationStatementSlot>;
  newSlot1:TmpGenerationStatementSlot;
  newSlot2:TmpGenerationStatementSlot;
  newSlot3:TmpGenerationStatementSlot;
  newSlot4:TmpGenerationStatementSlot;
  newSlot5:TmpGenerationStatementSlot;
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

  isNewMeter:boolean=false;
  message: string;
  
  isCaptives = [
    { "key": "Y", "name": "WHEELING" },
    { "key": "N", "name": "SELL-TO-BOARD" }
  ]

  mrSourceCodes = [
    { "key": "01", "name": "AMR READING" },
    { "key": "02", "name": "MANUAL READING" },
    { "key": "03", "name": "CMRI READING" }
  ]
  meterchange: Array<MeterChangeMc>;
  oldmeter:MeterChangeMc;
  newmeter:MeterChangeMc;

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
   this.meterchange= new Array<MeterChangeMc>();
   this.tempGsList=new Array<TmpGs>();
   this.slot1 =  new GenerationStatementSlot();
   this.slot2 =  new GenerationStatementSlot();
   this.slot3 =  new GenerationStatementSlot();
   this.slot4 =  new GenerationStatementSlot();
   this.slot5 =  new GenerationStatementSlot();
   this.generationStatementImportChargesForPrint =[];
   this.generationStatementExportChargesForPrint =[];

   this.oldGs = new TmpGs();
   this.oldGs.tempGenStmtSlots = [];

   this.oldSlot1 =  new TmpGenerationStatementSlot();
   this.oldSlot2 =  new TmpGenerationStatementSlot();
   this.oldSlot3 =  new TmpGenerationStatementSlot();
   this.oldSlot4 =  new TmpGenerationStatementSlot();
   this.oldSlot5 =  new TmpGenerationStatementSlot();
   this.oldGenerationStatementImportChargesForPrint =[];
   this.oldGenerationStatementExportChargesForPrint =[];


   this.newGs = new TmpGs();
   this.newGs.tempGenStmtSlots = [];
   this.newSlot1 =  new TmpGenerationStatementSlot();
   this.newSlot2 =  new TmpGenerationStatementSlot();
   this.newSlot3 =  new TmpGenerationStatementSlot();
   this.newSlot4 =  new TmpGenerationStatementSlot();
   this.newSlot5 =  new TmpGenerationStatementSlot();
   this.newGenerationStatementImportChargesForPrint =[];
   this.newGenerationStatementExportChargesForPrint =[];



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
              this.service.getGsById(_params['_id']+"forview").subscribe(_gs=>{
                this.gs = _gs;
                console.log(this.gs.dispCompanyServiceNumber+"----"+this.gs.statementMonth+"---"+this.gs.statementYear);
                this.service.checkmc(this.gs.dispCompanyServiceNumber,this.gs.statementMonth,this.gs.statementYear).subscribe(_checkmc=>{
                  this.meterchange=_checkmc;
                  console.log(this.meterchange);
                  if (this.meterchange != undefined && this.meterchange != null && this.meterchange.length!=0){
                            this.isNewMeter=true;
                           console.log("not new meter");
                  this.meterchange.forEach(meterchanges=>{
                      if (meterchanges.readingType=='METER CHANGE'){
                           this.oldmeter=meterchanges;
                          
                          }
                          if (meterchanges.readingType=='AMR'){

                            this.newmeter=meterchanges;
                          }


                  }
                    );
                    this.oldGenerationStatementImportForPrint();
                this.oldGenerationStatementExportForPrint();
                this.newGenerationStatementImportForPrint();
                this.newGenerationStatementExportForPrint();
                }
                
                  console.log(this.meterchange);
                });
                this.GenerationStatementCharges();
                this.formatChangesforUI();
                this.slot1 =this.gs.generationStatementSlots.filter(x => x.slotCode == 'C1')[0];
                this.slot2 =this.gs.generationStatementSlots.filter(x => x.slotCode == 'C2')[0];
                this.slot3 =this.gs.generationStatementSlots.filter(x => x.slotCode == 'C3')[0];
                this.slot4 =this.gs.generationStatementSlots.filter(x => x.slotCode == 'C4')[0];
                this.slot5 =this.gs.generationStatementSlots.filter(x => x.slotCode == 'C5')[0];

                // console.log(JSON.stringify(this.gs));
                // console.log(this.gs);
                // console.log("Charge Desc");
                this.bankingDetails();
                this.generationStatementImportForPrint();
                this.generationStatementExportForPrint();
                this.generationSummayForPrint();
              
                
                this.service.getTmpGss(this.gs.dispCompanyServiceNumber,this.gs.statementMonth,this.gs.statementYear).subscribe(tmpGss=>{
                  // console.log(tmpGss);
                  this.tempGsList=tmpGss;
                  // console.log(this.tempGsList);
                  // console.log(tmpGss.length);
                  if(parseInt(tmpGss.length)>0){
                    this.isNewMeter = true;
                    
                    tmpGss.forEach(tmpGs=>{
                      if(tmpGs.isNewMeter=='Y'){
                        // console.log(tmpGs);
                        this.newGs = tmpGs;
                        if(this.newGs.tempGenStmtSlots.length > 0){
                          // console.log("length",this.newGs.tenGenStmtSlots)
                        this.newSlot1 =this.newGs.tempGenStmtSlots.filter(x => x.slotCode == 'C1')[0];
                        this.newSlot2 =this.newGs.tempGenStmtSlots.filter(x => x.slotCode == 'C2')[0];
                        this.newSlot3 =this.newGs.tempGenStmtSlots.filter(x => x.slotCode == 'C3')[0];
                        this.newSlot4 =this.newGs.tempGenStmtSlots.filter(x => x.slotCode == 'C4')[0];
                        this.newSlot5 =this.newGs.tempGenStmtSlots.filter(x => x.slotCode == 'C5')[0];
                        this.newGenerationStatementImportForPrint();
                        this.newGenerationStatementExportForPrint();
                        }
                      }else if(tmpGs.isNewMeter=='N'){
                        // console.log(tmpGs);
                        this.oldGs = tmpGs;
                        // console.log(this.oldGs)
                        if(this.oldGs.tempGenStmtSlots.length > 0){
                          
                          this.oldSlot1 =this.oldGs.tempGenStmtSlots.filter(x => x.slotCode == 'C1')[0];
                          this.oldSlot2 =this.oldGs.tempGenStmtSlots.filter(x => x.slotCode == 'C2')[0];
                          this.oldSlot3 =this.oldGs.tempGenStmtSlots.filter(x => x.slotCode == 'C3')[0];
                          this.oldSlot4 =this.oldGs.tempGenStmtSlots.filter(x => x.slotCode == 'C4')[0];
                          this.oldSlot5 =this.oldGs.tempGenStmtSlots.filter(x => x.slotCode == 'C5')[0];
                          this.oldGenerationStatementImportForPrint();
                          this.oldGenerationStatementExportForPrint();
                        }
                        
                      }
                    });
           
            
                  }
                })
              
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



    oldGenerationStatementImportForPrint(){
      this.oldGenerationStatementImportChargesForPrint.push({"heading":"Final Reading", 
        "c1": this.oldmeter.impFinC1,  
        "c2": this.oldmeter.impFinC2,
        "c3": this.oldmeter.impFinC3,    
        "c4": this.oldmeter.impFinC4,    
        "c5": this.oldmeter.impFinC5,    
      });
      this.oldGenerationStatementImportChargesForPrint.push({"heading":"Initial Reading", 
      "c1": this.oldmeter.impInitC1,  
      "c2": this.oldmeter.impInitC2,
      "c3": this.oldmeter.impInitC3,    
      "c4": this.oldmeter.impInitC4,    
      "c5": this.oldmeter.impInitC5,    
        });
      this.oldGenerationStatementImportChargesForPrint.push({"heading":"Difference", 
      "c1": this.oldmeter.diffImpC1,  
      "c2": this.oldmeter.diffImpC2,
      "c3": this.oldmeter.diffImpC3,    
      "c4": this.oldmeter.diffImpC4,    
      "c5": this.oldmeter.diffImpC5,  
        });
      this.oldGenerationStatementImportChargesForPrint.push({"heading":"Units", 
      "c1": this.oldmeter.netImpC1,  
      "c2": this.oldmeter.netImpC2,
      "c3": this.oldmeter.netImpC3,    
      "c4": this.oldmeter.netImpC4,    
      "c5": this.oldmeter.netImpC5,  
      });
  
    }

    oldGenerationStatementExportForPrint(){
      this.oldGenerationStatementExportChargesForPrint.push({"heading":"Final Reading", 
      "c1":this.oldmeter.expFinC1,
      "c2": this.oldmeter.expFinC2,
      "c3": this.oldmeter.expFinC3,    
      "c4": this.oldmeter.expFinC4,    
      "c5": this.oldmeter.expFinC5,   
      });
      this.oldGenerationStatementExportChargesForPrint.push({"heading":"Initial Reading", 
      "c1": this.oldmeter.expInitC1,  
      "c2": this.oldmeter.expInitC2,
      "c3": this.oldmeter.expInitC3,    
      "c4": this.oldmeter.expInitC4,    
      "c5": this.oldmeter.expInitC5,  
       });
      this.oldGenerationStatementExportChargesForPrint.push({"heading":"Difference", 
      "c1": this.oldmeter.diffExpC1,  
      "c2": this.oldmeter.diffExpC2,
      "c3": this.oldmeter.diffExpC3,    
      "c4": this.oldmeter.diffExpC4,    
      "c5": this.oldmeter.diffExpC5,    
      });
      this.oldGenerationStatementExportChargesForPrint.push({"heading":"Units", 
      "c1": this.oldmeter.netExpC1,  
      "c2": this.oldmeter.netExpC2,
      "c3": this.oldmeter.netExpC3,    
      "c4": this.oldmeter.netExpC4,    
      "c5": this.oldmeter.netExpC5,  
      });
    }


    newGenerationStatementImportForPrint(){
      this.newGenerationStatementImportChargesForPrint.push({"heading":"Final Reading", 
        "c1": this.newmeter.impFinC1,  
        "c2": this.newmeter.impFinC2,
        "c3": this.newmeter.impFinC3,    
        "c4": this.newmeter.impFinC4,    
        "c5": this.newmeter.impFinC5,    
      });
      this.newGenerationStatementImportChargesForPrint.push({"heading":"Initial Reading", 
      "c1": this.newmeter.impInitC1,  
      "c2": this.newmeter.impInitC2,
      "c3": this.newmeter.impInitC3,    
      "c4": this.newmeter.impInitC4,    
      "c5": this.newmeter.impInitC5,    
        });
      this.newGenerationStatementImportChargesForPrint.push({"heading":"Difference", 
      "c1": this.newmeter.diffImpC1,  
      "c2": this.newmeter.diffImpC2,
      "c3": this.newmeter.diffImpC3,    
      "c4": this.newmeter.diffImpC4,    
      "c5": this.newmeter.diffImpC5,  
        });
      this.newGenerationStatementImportChargesForPrint.push({"heading":"Units", 
      "c1": this.newmeter.netImpC1,  
      "c2": this.newmeter.netImpC2,
      "c3": this.newmeter.netImpC3,    
      "c4": this.newmeter.netImpC4,    
      "c5": this.newmeter.netImpC5,  
      });
  
    }

    newGenerationStatementExportForPrint(){
      this.newGenerationStatementExportChargesForPrint.push({"heading":"Final Reading", 
      "c1":this.newmeter.expFinC1,
      "c2": this.newmeter.expFinC2,
      "c3": this.newmeter.expFinC3,    
      "c4": this.newmeter.expFinC4,    
      "c5": this.newmeter.expFinC5,   
      });
      this.newGenerationStatementExportChargesForPrint.push({"heading":"Initial Reading", 
      "c1": this.newmeter.expInitC1,  
      "c2": this.newmeter.expInitC2,
      "c3": this.newmeter.expInitC3,    
      "c4": this.newmeter.expInitC4,    
      "c5": this.newmeter.expInitC5,  
       });
      this.newGenerationStatementExportChargesForPrint.push({"heading":"Difference", 
      "c1": this.newmeter.diffExpC1,  
      "c2": this.newmeter.diffExpC2,
      "c3": this.newmeter.diffExpC3,    
      "c4": this.newmeter.diffExpC4,    
      "c5": this.newmeter.diffExpC5,    
      });
      this.newGenerationStatementExportChargesForPrint.push({"heading":"Units", 
      "c1": this.newmeter.netExpC1,  
      "c2": this.newmeter.netExpC2,
      "c3": this.newmeter.netExpC3,    
      "c4": this.newmeter.netExpC4,    
      "c5": this.newmeter.netExpC5,  
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

  getPdf(gs){
    console.log("getPdg-->",gs)
    this.gs=gs;
    console.log("DocID",this.gs.docId)
    if(this.gs.docId!=null || this.gs.docId!="" || this.gs.docId!=undefined){
      this.service.getPdf(this.gs.docId).subscribe(x=>{
        let content_type = x.headers.get('Content-type');
        let extension;
        switch(content_type){
          case 'application/jpeg':
          case 'application/jpg':
            extension = 'jpg';
            break;
          case 'application/png':
            extension = 'png';
            break;
          case 'application/gif':
            extension = 'gif';
            break;
          case 'application/pdf':
            extension = 'pdf';
            break;
          default:
            extension = 'pdf';
        }
        let x_filename = this.gs.id+this.gs.dispCompanyServiceNumber+"." + extension;
                    saveAs(x.blob(), x_filename);
      })
    }
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
    },
    error=>{
      if (JSON.parse(error._body).message) {
        this.message = JSON.parse(error._body).message;
      } else {
        this.message = "Too many request please try again later"
      }
    });

  }

}
