import 'rxjs/add/operator/switchMap';
import { Component, OnInit, HostBinding } from '@angular/core';
import { Router, ActivatedRoute, Params } from '@angular/router';
import { MatDialog, MatDialogRef, MatDialogConfig } from '@angular/material';
import{CommonService} from '../../../../app/shared/common/common.service';
import { CommonUtils } from '../../../shared/common/common-utils';
import { FeederLoss } from '../../vo/feeder-loss';
import { FeederLossService } from '../service/feeder-loss.service';
import { ImpFeederLoss } from '../../vo/imp-feeder-loss';
import { FeederLossComponent } from './feeder-loss.component';
@Component({
  selector: 'feeder-loss-dialog',
  templateUrl: './feeder-loss-dialog.component.html',

})
export class FeederLossDialogComponent implements OnInit {
  months: { value: string; name: string; }[];
  impFeederLoss:ImpFeederLoss[];
  newArray: any = {};
  orgList: any;
  subStationList: any;
  feederName: any;
  selectedValue: any;
  isSelect:boolean=false;
  test: any;


constructor(
    private route: ActivatedRoute,
    private commonService: CommonService,
    private commonUtils: CommonUtils,
    public dialog: MatDialog,
    private router: Router,
    public dialogRef: MatDialogRef<FeederLossComponent>,
    private feederLossService:FeederLossService
  ) {

  }
  ngOnInit() {
    this.impFeederLoss = [];
    this.months = this.commonService.fetchMonths();
    this.edcList(); 
  }

  edcList(){
    // this.orgList = "";
    this.commonService.fetchEDCs().subscribe(
      result => {
        this.orgList = result;
      });
      
  }

private tempArr=[];

  subStationNames(val,index){
    // this.subStationList="";
    console.log(val)
    let newVal = val.substring(4)
    console.log(newVal)
    
    this.commonService.fetchSubstationsByOrgIdAndTypeOfSs(newVal, 'FEEDER-Loss').subscribe(
      x => {
        this.subStationList = x;
        this.tempArr[index]['subStationList']=x;
      console.log(this.subStationList);
      });  
      // this.isSelect=true;
  }
  
  findFeeder(val,index){
    console.log(val)
    this.feederName ="";
    this.commonService.fetchFeeders(val).subscribe(x=>{
      this.feederName = x;
      this.tempArr[index]['feederName']=x;
      console.log(this.feederName);
    })
  }
  // navigateToListPage() {
  //   console.log("im here")
  //   // this.router.navigateByUrl('/feeder-loss/feeder-loss');
  //   this.router.navigateByUrl('/feeder-loss/feeder-loss/');

  // }
  navigateToListPage(){
    console.log("im here")
    this.dialogRef.close();
  }

  addBillRows(){
    this.impFeederLoss.push(this.newArray)
    
    this.tempArr.push({})
    console.log(this.tempArr)
    this.newArray = {};
  }


  deleteFieldValue(index) {
    this.impFeederLoss.splice(index, 1);
}

save(){
  console.log(this.impFeederLoss,this.tempArr)
  this.feederLossService.addImportFeederLoss(this.impFeederLoss).subscribe(x=>{
    this.dialog.closeAll()
    // this.navigateToListPage();
  },
  error => {
    console.error('Error loading Feeder!');
    console.error(error);
    // this.navigateToListPage();
  });
  // this.navigateToListPage();
  this.dialog.closeAll()
}
}