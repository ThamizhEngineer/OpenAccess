import 'rxjs/add/operator/switchMap';
import { Component, OnInit, HostBinding } from '@angular/core';
import { Router, ActivatedRoute, Params } from '@angular/router';
import { MatDatepickerModule } from '@angular/material';
import { MatNativeDateModule } from '@angular/material';
import { DatePipe } from '@angular/common';
import { CompanyServiceV1 } from './../../services/company.service.v1'; 
import { Company} from './../../../vo/company.v1'; 

@Component({
  selector: 'app-company-list',
  templateUrl: './company-list.component.html',
  styleUrls: ['./company-list.component.scss'],
  providers: [MatDatepickerModule,  MatNativeDateModule, DatePipe]
})
export class CompanyListComponent implements OnInit{ 

  rows: Array<Company>;
  tempResults: Array<Company>;
  /*
  columns = [
              { prop: 'companyId', name: 'Company Id'  },
              { prop: 'companyName', name: 'Company Name' }
            ];
*/
  //for pagination
  count = 0;
  offset = 0;
  limit = 8;
  searchCompanyCode :string="";
  searchCompanyName :string="";
  searchServiceAssociated:string="";
  searchEnabled:string="";
  serviceAssociated=[
    {"key":"01","name":"Banking Service"},  
    {"key":"02","name":"Buyer Service"},    
    {"key":"03","name":"Seller Service"}
  ];
  isEnabled=[
    {"key":"Y","name":"Y"},  
    {"key":"N","name":"N"}];
  
  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private service: CompanyServiceV1
  ) {}

  ngOnInit() {
   this.rows = [];

   // this.fetchResults();
   //this.page(this.offset,this.limit);
  }
 


  fetchResults(){
    console.log(' search criteria ' + this.searchCompanyCode +this.searchCompanyName +this.searchServiceAssociated +this.searchEnabled);
    this.service.searchCompanies(this.searchCompanyCode ,this.searchCompanyName ,this.searchServiceAssociated ,this.searchEnabled).subscribe(
      _companies => {  
        this.rows = _companies;
      });
  }

  newCompany() {
	  try{
	    //this.router.navigate(['/company-new','_id', _id]);
      this.router.navigateByUrl('/company/company-new');
      // to-do
      // this still needs to be fixed as giving '/order' is unecessary maintenance hazzle
      
	  }
	  catch(e){
	  console.log(e);
	  }
    
  }

  editCompany(_id: string) {
	  try{
	  console.log(_id);
	    //this.router.navigate(['/company-edit','_id', _id]);
      this.router.navigateByUrl('/company/company-edit/'+ _id);
      // to-do
      // this still needs to be fixed as giving '/order' is unecessary maintenance hazzle
      
	  }
	  catch(e){
	  console.log(e);
	  }
    
  }

}
