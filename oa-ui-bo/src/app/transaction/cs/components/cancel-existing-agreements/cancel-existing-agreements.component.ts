import {Component, Input,OnInit} from '@angular/core'
import { Cs} from './../../../vo/cs'; 
import { DatePipe } from '@angular/common';
import { Agreement} from './../../../vo/agreement'; 
import { CsService } from './../../services/cs.service';
import { AgreementService}from './../../../agreement/services/agreement.service';
@Component({
  selector: 'cancel-existing-agreements',
  templateUrl: './cancel-existing-agreements.component.html',
  providers:[AgreementService,DatePipe]
})

export class CancelExistingAgreements implements OnInit {
  @Input() cs: Cs;
  @Input() stepDisable: boolean;

  rows=[];
  agreement:Agreement;
  
  constructor(
    private service:CsService,  
    private agreementService:AgreementService,
    private datePipe: DatePipe,
  ) { 
  }
  
  ngOnInit() {
    this.service.csEvent.subscribe(_cs => {
      
            this.cs = _cs;
            console.log(this.cs);
      
          })
      this.agreementService.getAllAgreements("",this.cs.sellerCompanyServiceId,"","","SIGNED").subscribe(agreements=>{
        this.rows =agreements;
      
        this.rows.forEach(element=>{

       if(element.oaaId!=null){
          element.agreementTypeName="Open Access Application";
       }else if(element.ewaId!=null){
        element.agreementTypeName="Energy Wheeling Agreement";
       }else if(element.epaId!=null){
        element.agreementTypeName="Power Purchase Agreement";
       }
        });
  
      });

   
    }

    cancelAgreements(){
   
        this.rows.forEach(element=>{       
          this.agreement= new Agreement();
          this.agreementService.getAgreementById(element.id).subscribe(_agreement=>{
            this.agreement =_agreement;
            this.agreement.fromDate = this.datePipe.transform(this.agreement.fromDate, 'yyyy-MM-dd');
            this.agreement.toDate = this.datePipe.transform(this.agreement.toDate, 'yyyy-MM-dd');
            this.agreement.agreementDate = this.datePipe.transform(this.agreement.agreementDate, 'yyyy-MM-dd');
               console.log(this.agreement);
               this.agreement.csId=this.cs.id;
            this.agreementService.agreementCancelled(this.agreement).subscribe(_res=>{
     
            });
         
         
         
          });        
        });
  
 
    }
  
  }

  