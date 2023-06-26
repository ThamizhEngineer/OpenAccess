import {Component} from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { CommonUtils } from '../../../../shared/common/common-utils';
import { CommonService } from '../../../../shared/common/common.service';
import { UnAllocatedRemarks } from '../../../vo/unallocatedRemarks';
import { UnAllocatedService } from '../../services/un-allocated-service';

@Component({
    selector: 'un-allocated-list',
    templateUrl: './un-allocated-list.component.html',
    
})

export class UnAllocatedListComponent {
    orgList=[];
    rows: Array<UnAllocatedRemarks>;
    unAllocatedRemarks:UnAllocatedRemarks;
    
    years=[
        { "value": "2018", "name": "2018" },
        { "value": "2019", "name": "2019" },
        { "value": "2020", "name": "2020" },
        { "value": "2021", "name": "2021" },
        { "value": "2022", "name": "2022" },
        { "value": "2023", "name": "2023"}

];

FlowType=[
        
    { "value": "IS-CAPTIVE", "name": "CAPTIVE" },
    { "value": "THIRD-PARTY", "name":"THIRD-PARTY"},
  

];


    months: { value: string; name: string; }[];
    edcname :any;
    fuelTypeCode:any;
    fuel:any;
    Month:any;
    Year:any;

    IdasEdc: string;
    HasEdc: boolean = false;

   

  FlowTypes: any;

    

  dispServicenumber: string;
  mOrgId: string;
    
    
    constructor( 
        private route: ActivatedRoute,
        private router: Router,
        private commonService: CommonService,
        private commonUtils: CommonUtils,
        // private datePipe: DatePipe,
        private service: UnAllocatedService,
      ) { }


ngOnInit() {
    console.log("came here");
    this.fetchOrgList();
    this.fetchFuelTypes();
    this.months = this.commonService.fetchMonths();
  
   
    } 
    fetchOrgList() {
        this.commonService.fetchEDCs().subscribe(
          result => {
            this.orgList = result;
            this.mOrgId = CommonUtils.getLoginUserEDC();
            
            
            for(let i=0;i<this.orgList.length;i++){
                    if(this.orgList[i].id == this.mOrgId){
                      this.IdasEdc = this.orgList[i].id +"-"+this.orgList[i].name
                      this.HasEdc = true
                      this.edcname = this.orgList[i].id
                    }

            }

            
       



          },
          error => {
            console.error('Error loading states!');
            console.error(error);
          }
        );
      }
      fetchFuelTypes(){
        this.commonService.fetchCodes("FUEL_TYPE_CODE").subscribe(x=>{
        this.fuelTypeCode=x;
       })
      }  
      
      
      genrateResult(){
        console.log(this.FlowTypes);
        
        this.service.fetchrecord(this.edcname,this.Month,this.Year,this.FlowTypes).subscribe(
            _deltrans => {
              this.rows = _deltrans;
              console.log("In search result")
               
            });
        }
        editUnallocated(dispservicenumber:string,month:string,year:string,remarks:string,disporgname:string){
           
          try {
            
            
            this.router.navigateByUrl('/un-allocated/un-allocated-detail/'+dispservicenumber+"/"+month+"/"+year+"/"+remarks+"/"+disporgname);
            console.log(dispservicenumber);

          }
          catch (e) {
            console.log(e);
          }
      
        }



        }





      
    

