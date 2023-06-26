import { Component, OnInit } from '@angular/core';
import { SdlcNocService } from '../../sldc-noc.service';
import { SamastOutPut } from './vo/samastOutPut'; 
@Component({
    selector: '',
    templateUrl: 'samast-import.component.html',
    styleUrls: ['../../../../assets/styles/scss/components/_readonly-fields.scss']
})

export class SamastImportComponent implements OnInit {

    tabIndex:any;

    isClick:boolean=false;
    outPut: any;
 
    constructor(
        private service:SdlcNocService
    ) { }

    ngOnInit() { 

    }

    importApplication(){
        this.isClick=true;
        this.service.samastNocImport().subscribe(x=>{
            this.outPut=x;
           console.log(this.outPut);
        })
    }
    importApproval(){
        this.isClick=true;
        this.service.samastNocApprovalImport().subscribe(x=>{
            this.outPut=JSON.stringify(x);
            this.outPut=x;
        }) 
    }

    tabClick(event){
        if(this.tabIndex!=event.index){
            this.outPut=new SamastOutPut();
            this.isClick=false;
            this.tabIndex=event.index;
          }else{
              console.log(event);
          }
    }
}