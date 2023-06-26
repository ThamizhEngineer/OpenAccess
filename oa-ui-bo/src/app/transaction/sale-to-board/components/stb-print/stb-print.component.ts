import 'rxjs/add/operator/switchMap';
import { Component, OnInit, HostBinding } from '@angular/core';
import { Router, ActivatedRoute, Params } from '@angular/router';
import { FormsModule } from '@angular/forms';


import { StbEvent } from './../../services/stb.event'; 
import { StbService } from './../../services/stb.service'; 
import { Stb,GenerationSummary,Charge} from './../../services/stb'; 
import { CommonService } from './../../../../shared/common/common.service'; 
import * as jsPDF from 'jspdf';

@Component({
  selector: 'stb-print',
  templateUrl: './stb-print.component.html',
  styleUrls: ['./stb-print.component.scss']
})

export class StbPrintComponent{

    stb: Stb;
  generationSummaries:GenerationSummary;
  viewType: string;
  months= [];
  tenOfNet;
  rKVAHGenetation;
  penaltyRate;
  rKVAKPenalty;
  monthlyMeterReading;
  netGenerationUnit;
  netPayable;
  machineCapacity;
  omCharges;
  electricityTax;
  final1;final2;final3;final4;
  initial1;initial2;initial3;initial4;
  difference1;difference2;difference3;difference4;
  generation1;generation2;generation3;generation4;

 
  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private service: StbService,
    private commonService: CommonService,
    private stbEvent: StbEvent
  ) {}

  ngOnInit() {
    this.stb = new Stb();
    this.generationSummaries = new GenerationSummary();
    
    this.months = this.commonService.fetchMonths();
    this.stbEvent.stb$.subscribe(latestValue =>{ 
    this.stb =  latestValue;
    });
   this.stbEvent.load();
   
 
    if(this.route.params['_value']['_id']){
      //route.params['value'] will have the '_id' in it, during edit 
     
      this.route.params
            .subscribe((_params: Params) => {
              this.service.getStbById(_params['_id']).subscribe(_stb=>{
                this.stb = _stb;
                this.stbEvent.set(_stb);
                this.generateCharges();
                 this.print(); 
               
                
              });
              this.viewType = _params['_viewType'];
            }); 
    }
           
    
  }
  

  generateCharges(){
    this.final1=this.stb.generationSummaries[2].final;
    this.final2=this.stb.generationSummaries[3].final;
    this.final3=this.stb.generationSummaries[0].final;
    this.final4=this.stb.generationSummaries[1].final;

    this.initial1=this.stb.generationSummaries[2].initial;
    this.initial2=this.stb.generationSummaries[3].initial;
    this.initial3=this.stb.generationSummaries[0].initial;
    this.initial4=this.stb.generationSummaries[1].initial;

    this.difference1=this.stb.generationSummaries[2].difference;
    this.difference2=this.stb.generationSummaries[3].difference;
    this.difference3=this.stb.generationSummaries[0].difference;
    this.difference4=this.stb.generationSummaries[1].difference;

    this.generation1=this.stb.generationSummaries[2].generation;
    this.generation2=this.stb.generationSummaries[3].generation;
    this.generation3=this.stb.generationSummaries[0].generation;
    this.generation4=this.stb.generationSummaries[1].generation;





    this.tenOfNet=this.stb.charges[2].totalCharges;
    this.omCharges=this.stb.charges[3].totalCharges;
    this.rKVAKPenalty=this.stb.charges[4].totalCharges;
    this.monthlyMeterReading=this.stb.charges[7].totalCharges;
    this.electricityTax=this.stb.charges[9].totalCharges;   
                     
  }


  calculateStb(){
      console.log("in calculate")
    if(this.stb.header.netGeneration){
      console.log(this.stb.header.netGeneration)
      var netGeneration = parseInt(this.stb.header.netGeneration)
      this.tenOfNet=(netGeneration*0.10 );
      console.log("10%:"+this.tenOfNet);
        
      this.rKVAHGenetation=this.stb.generationSummaries[0].generation;
      console.log("rkvah"+this.rKVAHGenetation);
      if(this.tenOfNet>=this.rKVAHGenetation){
          this.penaltyRate=0.25;
      }else if(this.tenOfNet<this.rKVAHGenetation){
        this.penaltyRate=0.50;
      }
      console.log("penalty rate"+this.penaltyRate);
      this.rKVAKPenalty = this.penaltyRate*this.rKVAHGenetation;
      console.log("rKvah Penalty"+this.rKVAKPenalty);
      this.monthlyMeterReading=300;
      this.netGenerationUnit =parseInt(this.stb.header.netGeneration)*2.90;
      console.log("Net Generation unit"+this.netGenerationUnit);
      this.machineCapacity = parseInt(this.stb.header.load);
      this.omCharges=((204205/1000)*this.machineCapacity)/12;
      console.log(this.omCharges);
      this.electricityTax=0;
      this.netPayable=(this.netGenerationUnit)-(this.rKVAKPenalty+this.monthlyMeterReading+this.omCharges+this.electricityTax);
      console.log(this.netPayable);  
      
     

   }
    
    
  }
 print(){
     console.log("In Print Component");
   var innerContents = document.getElementById("printSectionId").innerHTML;
        var popupWinindow = window.open('', '_blank', 'width=600,height=700,scrollbars=no,menubar=no,toolbar=no,location=no,status=no,titlebar=no');
        popupWinindow.document.open();
        popupWinindow.document.write('<html><head></head><body onload="window.print()">' + innerContents + '</html>');
        popupWinindow.document.close();
      
 }

  download(printSectionId) {
        var innerContents = document.getElementById(printSectionId);
              //var doc = new jsPDF(orientation, unit, format, compress);

        var img = new Image();
        img.addEventListener('load', function() {
        var doc = new jsPDF('p','pt','a4');    
        doc.addImage(img, 'png', 230, 10,100,100);
        doc.fromHTML(innerContents,50,100);
        doc.save('Test.pdf');
});
        img.src = './../../../assets/images/tneb-tangedco-logo_360.png';  
       
}
 
   listStb() {
      this.router.navigateByUrl('/stb/stb-list');
//       // to-do
//       // this still needs to be fixed as giving '/order' is unecessary maintenance hazzle
      
 	  }


}
