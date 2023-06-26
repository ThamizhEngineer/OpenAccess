import 'rxjs/add/operator/switchMap';
import { Component, OnInit, HostBinding } from '@angular/core';
import { Router, ActivatedRoute, Params } from '@angular/router';


import { SsService } from './../../services/ss.service'; 
import { Ss} from './../../../vo/ss'; 

@Component({
  selector: 'app-ss-list',
  templateUrl: './ss-list.component.html',
  styleUrls: ['./ss-list.component.scss'],
  providers: [SsService]
})
export class SsListComponent implements OnInit{ 

  rows: Array<Ss>;
  tempResults: Array<Ss>;
  sscode:string;
  ssname:string;
  /*
  columns = [
              { prop: 'ssId', name: 'Ss Id'  },
              { prop: 'ssName', name: 'Ss Name' }
            ];
*/
   
  
    constructor(
      private route: ActivatedRoute,
      private router: Router,
      private service: SsService
            ) {}

      ngOnInit() {
      this.rows = [];
     
               }

     
        fetchResults()
        {
            console.log(" "+this.sscode);
            this.service.getSss(this.sscode,this.ssname).subscribe(
            _sss => {  
            this.rows = _sss;
             console.log(this.rows) ;
    
            });
       }

 
       //  fetchResults()
         //  { 
        
         //     console.log(" "+this.sscode);
             //  this.service.getSssCode(this.sscode).subscribe(
          //   this.service.getSsById(this.sscode).subscribe(
           //  _ssid => {  
           //  this.rows = [_ssid]; 
           //  console.log(this.rows) ; 
            // });
    
           // }

 
        newSs() {
            try{
          //this.router.navigate(['/ss-new','_id', _id]);
             this.router.navigateByUrl('/ss/ss-new');
          // to-do
          // this still needs to be fixed as giving '/order' is unecessary maintenance hazzle
          
                }
              catch(e){
              console.log(e);
              }
            }

          editSs(_id: string) {
            try{
             console.log("In edit"+_id);
             this.router.navigateByUrl('/ss/ss-edit/'+_id);
            // to-do
            // this still needs to be fixed as giving '/order' is unecessary maintenance hazzle
               }
              
            catch(e){
              console.log(e);
               }
        
           }

      }
