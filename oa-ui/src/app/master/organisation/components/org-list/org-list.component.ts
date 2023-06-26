import 'rxjs/add/operator/switchMap';
import { Component, OnInit, HostBinding } from '@angular/core';
import { Router, ActivatedRoute, Params } from '@angular/router';
 
 
import { OrgService } from './../../services/org.service'; 
import { Org} from './../../../vo/org'; 

@Component({
  selector: 'app-org-list',
  templateUrl: './org-list.component.html',
  styleUrls: ['./org-list.component.scss'],
  providers: [OrgService]
})
export class OrgListComponent implements OnInit{ 

  rows: Array<Org>;
  tempResults: Array<Org>;
  /*
  columns = [
              { prop: 'orgId', name: 'Org Id'  },
              { prop: 'orgName', name: 'Org Name' }
            ];
*/
  //for pagination
  count = 0;
  offset = 0;
  limit = 55;
  
  
  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private service: OrgService
  ) {}

  ngOnInit() {
   this.rows = [];
   this.page(this.offset,this.limit);
  }
 

  page(offset, limit) { 
    this.offset = offset;
    this.limit = limit;
    this.fetchResults()
  
  
  }

  fetchResults(){
    this.service.getOrgs().subscribe(
      _orgs => {  
        this.tempResults = _orgs;
        
        this.count = this.tempResults.length;
        const start = this.offset * this.limit;
        const end = start + this.limit;
        const rows = [...this.rows];

        for (let i = start; i < end; i++) {
          rows[i] = this.tempResults[i];
        }

        this.rows = rows;
       // console.log('Page Results', start, end, rows);
      });
  }

  onPage(event) {
    console.log('Page Event', event);
    this.page(event.offset, event.limit);
  }

  newOrg() {
	  try{
	    //this.router.navigate(['/org-new','_id', _id]);
      this.router.navigateByUrl('/org/org-new');
      // to-do
      // this still needs to be fixed as giving '/order' is unecessary maintenance hazzle
      
	  }
	  catch(e){
	  console.log(e);
	  }
    
  }

  editOrg(id: string) {
	  try{
	  //console.log(_id);
     this.router.navigateByUrl('/org/org-edit/'+ id);
      // to-do
      // this still needs to be fixed as giving '/order' is unecessary maintenance hazzle
      
	  }
	  catch(e){
	  console.log(e);
	  }
    
  }

}
//test