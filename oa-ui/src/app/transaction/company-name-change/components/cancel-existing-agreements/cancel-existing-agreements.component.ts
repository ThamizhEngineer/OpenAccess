import {Component, Input,OnInit} from '@angular/core'
import { CompanyNameChange} from './../../../vo/company-name-change'; 
import { DatePipe } from '@angular/common';
import { Agreement} from './../../../vo/agreement'; 
import { CompanyNameChangeService } from './../../services/company-name-change.service';
import { AgreementService}from './../../../agreement/services/agreement.service';
@Component({
  selector: 'cancel-existing-agreements',
  templateUrl: './cancel-existing-agreements.component.html',
  providers:[AgreementService,DatePipe]
})

export class CancelExistingAgreements implements OnInit {
  @Input() companyNameChange: CompanyNameChange;
  @Input() stepDisable: boolean;

  rows=[];
  agreement:Agreement;
  
  constructor(
    private service:CompanyNameChangeService,  
    private agreementService:AgreementService,
    private datePipe: DatePipe,
  ) { 
  }
  
  ngOnInit() {
    this.service.companyNameChangeEvent.subscribe(_companyNameChange => {
      
            this.companyNameChange = _companyNameChange;
            console.log(this.companyNameChange);
      
          })
      this.agreementService.getAllAgreements("",this.companyNameChange.serviceId,"","","SIGNED").subscribe(agreements=>{
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
               this.agreement.csId=this.companyNameChange.id;
            this.agreementService.agreementCancelled(this.agreement).subscribe(_res=>{
     
            });
         
         
         
          });        
        });
  
 
    }
  
  }

  