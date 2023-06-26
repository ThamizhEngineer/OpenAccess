import { Component, OnInit, HostBinding } from '@angular/core';
import { Router, ActivatedRoute, Params } from '@angular/router';
import { MatDialog, MatDialogRef, MatDialogConfig } from '@angular/material';
import { CommonUtils } from '../../../shared/common/common-utils';
import { GcAddComponent } from './../gc-add/gc-add.component';
 
 
import { GcService } from './../../services/gc.service'; 
import { Gc} from './../../services/gc'; 

@Component({
  selector: 'gc-list',
  templateUrl: './gc-list.component.html',
  //styleUrls: ['./gc-list.component.scss'],
  providers: [GcService,CommonUtils]
})
export class GcListComponent implements OnInit{ 

  rows: Array<Gc>;
  dialogRef: MatDialogRef<GcAddComponent>;
  config: MatDialogConfig = {
    disableClose: false,
    width: '100%',
    height: '50%',
    position: {
      left: '20%',
      right: '20%'
    }
  };
  lastCloseResult: string;
  tempResults: Array<Gc>;

  //for pagination
  // count = 0;
  // offset = 0;
  // limit = 8;
  
  
  accessAddFlag: boolean = false;
  accessViewFlag: boolean = false;
  constructor(
    private commonUtils: CommonUtils,
    private route: ActivatedRoute,
    private router: Router,
    private service: GcService,
    public dialog: MatDialog
  ) {}

  ngOnInit() {
    this.accessAddFlag = this.commonUtils.userHasAccess("GRID-CONNECTIVITY-APPLICATION", "CREATE");
    this.accessViewFlag = this.commonUtils.userHasAccess("GRID-CONNECTIVITY-APPLICATION", "VIEW");
   this.rows = [];
   this.fetchResults();
  
  }
 

  

  fetchResults(){
    this.service.getGcs().subscribe(
      _gcs => {  
        this.rows = _gcs;
       
      });
  }

  
  newGc() {
	  try{

      this.dialogRef = this.dialog.open(GcAddComponent, this.config);
      this.dialogRef.afterClosed().subscribe(result => {
      
          this.editGc(result);
        
        this.dialogRef = null;
      });

	  }
	  catch(e){
	  console.log(e);
	  }
    
  }

  editGc(_id: string) {
	  try{
    
      this.router.navigateByUrl('/gc/gc-edit/'+ _id);
      // to-do
      // this still needs to be fixed as giving '/order' is unecessary maintenance hazzle
      
	  }
	  catch(e){
	  console.log(e);
	  }
    
  }

}
