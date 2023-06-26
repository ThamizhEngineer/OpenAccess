// Angular and other libraires
import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute, Params } from '@angular/router';
import { MatDialog, MatDialogRef } from '@angular/material';
import { FormBuilder} from '@angular/forms';
import { MatDatepickerModule } from '@angular/material';
import { MatNativeDateModule } from '@angular/material';
import { DatePipe } from '@angular/common';



// Other imports
import 'rxjs/add/operator/switchMap';
import 'rxjs/add/operator/catch';

// Helpers
import { EaDetailHelper } from './../../components/ea-detail/ea-detail-helper';
import { CommonUtils } from '../../../../shared/common/common-utils';
import { EaDialogComponent } from '../ea-dialog/ea-dialog.component';
import {BulkDialogComponent} from '../ea-detail/ea-bulk.component';


// Service imports
import { TradeRelationshipService } from './../../../../master/trade-relationship/services/trade-relationship.service';
import { CommonService } from './../../../../shared/common/common.service';
import { EaService } from './../../services/ea.service';
import { GsService } from './../../../generation-statement/services/gs.service';

// Data Structre imports
import { Ea, EsUsageDetail, EsUsageSummary, generationStatementForPrint, AvailableGsUnits, AllocatedGsUnits } from './../../../vo/ea';
import { Job } from '../../../../transaction/vo/job';
import { Gs } from './../../../../transaction/vo/gs';
import { TradeRelationship } from './../../../../master/vo/trade-relationship';

@Component({
  selector: 'ea-detail',
  templateUrl: './ea-detail.component.html',
  styleUrls: ['./ea-detail.component.scss'],
  providers: [MatDatepickerModule, MatNativeDateModule, DatePipe]
})

export class EaDetailComponent implements OnInit {
  displayedColumns = ['c1', 'c2', 'c3'];
  tempHideFlag = false;
  es: Ea;
  gs: Gs;
  job: Job;
  esUsageSummary: EsUsageSummary;
  esUsageDetail: EsUsageDetail;
  size = 10;
  // Accordin
  panelOpenState = false;
  availableOpenState = false;
  allocatedOpenState = false;
  chargesOpenState = false;
  postalAllocationOpenState = false;
  isFossilFuel=false;
  errorMessage="";
  // --page

  //for pagination
  count = 0;
  offset = 0;
  limit = 2;

  // oaas:Array<Oaa>;
  // dataSource:any;
  esUsageSummaryRowIndex: number;
  consumerServices = [];
  consumerServicesForQuantum = [];
  consumerCompanies: Array<TradeRelationship>;
  filteredServices = []
  isMandatory = true;
  statusCode: string;
  savedOnce: string;
  quantumChanged: any;
  availableGsUnits: Array<AvailableGsUnits>;
  availableGsUnitsForPanel: AvailableGsUnits;
  allocatedGsunits: Array<AllocatedGsUnits>;
  allocatedGsunitsForPanel: AllocatedGsUnits
  allocationFlag: boolean = false;
  chargeFlag: boolean = false;
  minimunAllocationFlag: boolean = false;
  minimumChargeFlag: boolean = false;
  months = [];
  voltages = [];
  generationStatementForPrint: Array<generationStatementForPrint>;
  c1Name: string;
  c2Name: string;
  c3Name: string;
  c4Name: string;
  c5Name: string;
  c1: string; c2: string; c3: string; c4: string; c5: string;
  total: any;
  row = [];
  checkBuyer: boolean = false;
  sumC1: any; sumC2: any; sumC3: any; sumC4: any; sumC5: any; sum: any;
  accessUpdateFlag: boolean = false;
  accessDeleteFlag: boolean = false;
  accessApproveFlag: boolean = false;
  accessCompleteFlag: boolean = false;
  accessProcessFlag: boolean = false;
  isFinalDisabled: boolean = false;
  fuelGroup:any; 

	disableAllButtons: boolean =true;
	processingMessage: string = '';
  disableSave: boolean = false;
  chargeAbbr={"C001":"M.R.C","C002":"O.M.C","C003":"T.R.C","C004":"S.O.C","C005":"R.K.P","C006":"N.E.C","C007":"S.H.C","C008":"O.C","C009":"P.O.C"};
  constructor(
    private commonUtils: CommonUtils,
    private route: ActivatedRoute,
    private router: Router,
    private service: EaService,
    private commonService: CommonService,
    private _formBuilder: FormBuilder,
    private gsService: GsService,
    private datePipe: DatePipe,
    private tradeService: TradeRelationshipService,
    private eaDetailHelper: EaDetailHelper,
    public dialog: MatDialog) {

      this.fetchVoltages();
  }

  isAllowLowerSlotAdj = [
		{ "key": "Y" ,"name":"YES"},
		{ "key": "N" ,"name":"NO" }
	  ]

  ngOnInit() {
    this.page(this.offset, this.limit);

    this.es = new Ea();
    this.gs = new Gs();
    this.job = new Job();
    this.accessUpdateFlag = this.commonUtils.userHasAccess("ENERGY-SALE", "UPDATE");
    this.accessDeleteFlag = this.commonUtils.userHasAccess("ENERGY-SALE", "DELETE");
    this.accessApproveFlag = this.commonUtils.userHasAccess("ENERGY-SALE", "APPROVE");
    this.accessCompleteFlag = this.commonUtils.userHasAccess("ENERGY-SALE", "COMPLETE");
    this.accessProcessFlag = this.commonUtils.userHasAccess("ENERGY-SALE", "PROCESS");
    this.esUsageSummary = new EsUsageSummary();
    this.esUsageDetail = new EsUsageDetail();
    this.consumerServices = new Array<TradeRelationship>();

    this.months = this.commonService.fetchMonths();
    this.generationStatementForPrint = [];
    this.availableGsUnits = [];
    this.allocatedGsunits = [];
    this.availableGsUnitsForPanel = new AvailableGsUnits();
    this.allocatedGsunitsForPanel = new AllocatedGsUnits();

    if (this.route.params['_value']['_id']) {
      //route.params['value'] will have the '_id' in it, during edit 
      this.route.params
        .subscribe((_params: Params) => {
            this.fetchEsAndGsFromDB(_params['_id']);
        });
    }


  }

  page(offset, limit) {
    this.offset = offset;
    this.limit = limit;

  }

  // loop energy-allotment line records and set quantum in all of them
    /*
    for each es-summary
      find the commercial entry (just one) with matching buyerServiceId
        TODO - dinesh - need to implement duration and flowtype and   
        month&year between from/to date in commercials and buyer id match
      fetch commercial.quantum 
      calculate units and update summary.qua
    */
   updateQuantumInEsSummaries(){
     var commercial: TradeRelationship;
     var quantum = 0;
     var days = this.commonUtils.getDaysInMonth(parseInt(this.es.month), parseInt(this.es.year));
     this.es.esUsageSummaries.forEach(summary => {
       commercial = this.consumerCompanies.filter((item) => item.buyerCompServiceId == summary.buyerCompanyServiceId)[0];
       if (commercial.intervalTypeCode == "04") {
         if (commercial.sharePercent != null && parseFloat(commercial.sharePercent) > 0) {
           quantum = (parseFloat(this.es.sanctionedCapacity) * parseFloat(commercial.sharePercent)) / 100;
           summary.quantum = quantum.toString();
           summary.quantum = this.commonUtils.convertQuantumToUnits(summary.quantum);
         }
       }
       if (commercial.intervalTypeCode == "01" || commercial.intervalTypeCode == "02") {
         if (summary.quantum != null && parseFloat(summary.quantum) > 0) {
           summary.quantum = this.commonUtils.calculateProposedQuantum(parseFloat(summary.quantum), days).toString();
         }
       }
       summary.total =  ""+ (parseFloat(summary.c1)+ parseFloat(summary.c2)+ parseFloat(summary.c3)+ parseFloat(summary.c4)+ parseFloat(summary.c5));			
     }); 
     console.log("updateQuantumInEsSummaries output")
     console.log(this.es.esUsageSummaries)
        
  }

  fetchEsAndGsFromDB(esId) { 
        this.service.getEsById(esId).subscribe(_es => {
          this.es = _es;
          this.disableAllButtons = true;
          this.tradeService.getTradeRelationships("", "", "", this.es.sellerCompanyServiceId, "", "", this.es.month, this.es.year, "", "").subscribe(
            _consumerNames => {
              this.consumerCompanies = _consumerNames;
              this.updateQuantumInEsSummaries();
              this.fuelGroup = this.es.fuelGroupe;
              if(this.fuelGroup=="FF"){
                this.isFossilFuel = true;                
              }
              this.fetchGsById();
              this.statusCode = this.es.statusCode;
              this.savedOnce = this.es.savedOnce; 

            });
        }); 
  }

  fetchGsById() { 
    this.gsService.getGsById(this.es.generationStatementId).subscribe(
      _gs => {
        this.gs = _gs;
        if(this.gs.dispFuelTypeGroup=='FF'){
					this.isFossilFuel = true;
					if(CommonUtils.getLoginUserTypeCode() == "GEN"){
						this.accessApproveFlag = false;
					} 
					else if(CommonUtils.getLoginUserTypeCode() == "EDC"){
						this.accessApproveFlag = true;
					}
					if(this.gs.flowTypeCode=='IS-CAPTIVE'){
						this.es.allowLowerSlotAdmt = 'Y'
					}
					if(this.gs.flowTypeCode=='THIRD-PARTY'){
						this.es.allowLowerSlotAdmt = 'N'
					}
				}
        console.log("calling availableGsUnitsForPrint ")
        var res = this.eaDetailHelper.availableGsUnitsForPrint(this.es, this.gs);
        this.availableGsUnits = res["availableGsUnits"];
        this.avaliableGsPanel(this.availableGsUnits);

        console.log("calling allocatedGsUnitsForPrint ")
        var allocatedResult = this.eaDetailHelper.allocatedGsUnitsForPrint(this.es);
        this.allocatedGsunits = allocatedResult["allocated"];
        this.allocatedGsPanel(this.allocatedGsunits);

        console.log("calling checkAllocatedUnits ")
        var result1 = this.eaDetailHelper.checkAllocatedUnits(this.es, this.gs,CommonUtils.getLoginUserTypeCode());
        // this.allocationFlag = result["allocation"];
        // this.chargeFlag = result["charge"];
        this.es = result1["es"];
        this.allocatedGsunits = result1["allocatedGsunits"];
        this.errorMessage = result1["errorMessage"];
        console.log("esUsageSummaries output received")
        console.log(this.es.esUsageSummaries)

				this.disableAllButtons = false;
      }
    )
  }

  avaliableGsPanel(elementArray: Array<AvailableGsUnits>) {
    console.log("Available-" + elementArray)
    var temp = elementArray.filter(function (element) {
      return element.heading == "Total";
    });
    this.availableGsUnitsForPanel = temp[0];
    console.log(this.availableGsUnitsForPanel)
  }

  allocatedGsPanel(elementArray: Array<AllocatedGsUnits>) {
    console.log("Allocated-" + elementArray)
    var temp = elementArray.filter(function (element) {
      return element.heading == "Total";
    });
    this.allocatedGsunitsForPanel = temp[0];
    console.log(this.allocatedGsunitsForPanel)
  }


  listEss() {
    this.router.navigateByUrl('/ea/ea-list');
  }

  fetchVoltages() {
    this.commonService.fetchCodes('VOLTAGE_CODE').subscribe(
      result => {
        this.voltages = result;
      },
      error => {
        console.error('Error loading Voltages');
        console.error(error);
      });
  }

  openConfirmDilog() {
    // let dialogRef =
    this.dialog.open(EaDialogComponent)
    // let instance = dialogRef.componentInstance;
    // instance.es.id = "Updated text";
    // console.log('dialogRef',dialogRef);

  }


  initAllocations() {
    console.log("initAlln")
    if (!this.es.esUsageSummaries) {
      this.es.esUsageSummaries = [];
    }
    this.esUsageSummary = new EsUsageSummary();
    this.esUsageSummaryRowIndex = -1;
  }

  
  
  
  checkAllocations(summaryToCheck) {
    this.errorMessage = "";
    this.allocationFlag = false;
    if(summaryToCheck!=''){
      if (summaryToCheck.quantum == "0" && summaryToCheck.quantum == null) {
        this.errorMessage ="Consumer Data issue - Quantum data is not available"; 
        this.allocationFlag = true;
        return false;
      }
        else 
        if(!this.isFossilFuel){
          if (summaryToCheck.sharePercent == "0" && summaryToCheck.sharePercent == null) {
            this.errorMessage ="Consumer Data issue - Share Percent data is not available";
            this.allocationFlag = true;
            return false;
          } 
        }
        else{
          var _result = this.eaDetailHelper.checkSlotsWithCommercials(summaryToCheck, this.consumerCompanies);
          var _hasIssue = _result["hasIssue"];
          var _resultMessage = _result["resultMessage"];
          if(_hasIssue){
            this.errorMessage = _resultMessage;
            return false;
          }
        }
    } 
    if(this.isFossilFuel){
      var _result = this.eaDetailHelper.checkSlotsWithCommercialsForAll(this.es.esUsageSummaries, this.consumerCompanies);
      var _hasIssue = _result["hasIssue"];
      var _resultMessage = _result["resultMessage"];
      if(_hasIssue){
        this.errorMessage = _resultMessage;
        this.disableAllButtons = false;
        return false;
      }
    }

    var result = this.eaDetailHelper.checkAllocatedUnits(this.es, this.gs,CommonUtils.getLoginUserTypeCode());
    this.allocationFlag = result["allocation"];
    this.chargeFlag = result["charge"];
    this.es = result["es"];
    this.allocatedGsunits = result["allocatedGsunits"];
    this.errorMessage = result["errorMessage"];
    //  this.initAllocations();  // note needed. as there is no addrow or deleterow
    // }
  }


  //------------------------- charges validation ---------------------------------- //
  onCompleteDisable(){
    this.isFinalDisabled = true;
  }

  onMRCValidate(){
    this.isFinalDisabled = true;
    console.log(this.es.esUsageSummaries);
    this.es.esUsageSummaries.filter(function(Charges){
       var mrcCharge = 0 ;
       mrcCharge = mrcCharge +parseFloat(Charges.c001TotalCharge);
       console.log("mrcCharge");
       console.log(mrcCharge);
      // console.log("this.gs.c001TotalCharge")
      //  console.log(this.gs.c001TotalCharge);
      //  if(mrcCharge <= this.gs.c001TotalCharge)
      //  {
      //    console.log("Allow");
      //  }
      //  else {
      //    alert("Total C001 Allocated charges cannot be greater than available charges")
      //  }
    });
   }


   
  saveEs() {
    this.errorMessage = "";
    this.disableSave = true;
		this.disableAllButtons = true;
		this.processingMessage = "Please wait. Energy Allotment is saving ...";
    if(this.fuelGroup == 'RE')
    {
      var result = this.eaDetailHelper.checkMinumumAllocatedUnits(this.es, this.gs);
      this.errorMessage = result["errorMessage"];
      this.minimumChargeFlag = result["minimumCharge"]; 
    }
    // this.minimunAllocationFlag = result["minimunAllocation"];
    //save can be add or update
    this.formatChangesforDB();
    this.es.simpleEnergySale = 'N';
    this.es.usageDetailAvail = 'Y';
    //this.es.multipleBuyers = 'Y';
    this.es.savedOnce = 'Y';

    if(this.es.allowLowerSlotAdmt==null){
			this.errorMessage ="Update the higher order to lower order slot and then proceed ";	
			this.disableAllButtons=false;
			return false;
		}
    if (this.allocationFlag) {
      this.errorMessage ="Cannot update Energy Allotment!!! - Error in Allocation of Units ";
			this.disableAllButtons=false;
			return false;
    } else if ((!this.isFossilFuel) && this.chargeFlag) {
      this.errorMessage ="Cannot update Energy Allotment!!! - Error in Allocation of Charges ";
			this.disableAllButtons=false;
			return false;
    }
    // else if (this.minimunAllocationFlag) {
    // 	alert("Cannot update Energy Allotment!!! - Allocated Units cannot be more than available Units")
    // }
    else if ((!this.isFossilFuel) && this.minimumChargeFlag) {
      this.errorMessage ="Cannot update Energy Allotment!!! - Allocated charges cannot be less than available charges";
			this.disableAllButtons=false;
			return false;
    } else {
			if(this.isFossilFuel){
				var _result = this.eaDetailHelper.checkSlotsWithCommercialsForAll(this.es.esUsageSummaries, this.consumerCompanies);
				var _hasIssue = _result["hasIssue"];
				var _resultMessage = _result["resultMessage"];
				if(_hasIssue){
					this.errorMessage =_resultMessage;
					this.disableAllButtons = false;
					return false;
				}
			}
      this.checkAllocations('');
      if (this.es.id == '') {
        if (this.es.allowLowerSlotAdmt == null) {
          this.errorMessage ="Update the higher order to lower order slot and then proceed ";
					this.disableAllButtons=false;
					return false;	
        }
      }
      else {
        this.updateEs();
      }
    }
  }

  formatChangesforDB() {

   this.es.stringFromDate = this.datePipe.transform(this.es.fromDate, 'yyyy-MM-dd');
   this.es.stringToDate = this.datePipe.transform(this.es.toDate, 'yyyy-MM-dd');
    // this.es.stringFromDate = this.datePipe.transform(this.es.fromDate, 'yyyy-MM-dd');
		// this.es.stringToDate = this.datePipe.transform(this.es.toDate, 'yyyy-MM-dd');
    this.esUsageDetail.usageDate = this.datePipe.transform(this.esUsageDetail.usageDate, 'yyyy-MM-dd');

  }

  resetEss() {
		this.disableAllButtons=true; 
    this.es.esUsageSummaries.forEach(element => {
      element.c1 = '0';
      element.c2 = '0';
      element.c3 = '0';
      element.c4 = '0';
      element.c5 = '0';
      element.c001TotalCharge = '0';
      element.c002TotalCharge = '0';
      element.c003TotalCharge = '0';
      element.c004TotalCharge = '0';
      element.c005TotalCharge = '0';
      element.c006TotalCharge = '0';
      element.c007TotalCharge = '0';
      element.c008TotalCharge = '0';
      element.c009TotalCharge = '0';
      element.total = '0';
    });

    this.es.gc1 = '0';
    this.es.gc2 = '0';
    this.es.gc3 = '0';
    this.es.gc4 = '0';
    this.es.gc5 = '0';
    this.es.bc1 = '0';
    this.es.bc2 = '0';
    this.es.bc3 = '0';
    this.es.bc4 = '0';
    this.es.bc5 = '0';
    this.es.c1 = '0';
    this.es.c2 = '0';
    this.es.c3 = '0';
    this.es.c4 = '0';
    this.es.c5 = '0';
    this.allocatedGsunits[0].c1 = '0';
    this.allocatedGsunits[1].c1 = '0';
    this.allocatedGsunits[2].c1 = '0';
    this.allocatedGsunits[0].c2 = '0';
    this.allocatedGsunits[1].c2 = '0';
    this.allocatedGsunits[2].c2 = '0';
    this.allocatedGsunits[0].c3 = '0';
    this.allocatedGsunits[1].c3 = '0';
    this.allocatedGsunits[2].c3 = '0';
    this.allocatedGsunits[0].c4 = '0';
    this.allocatedGsunits[1].c4 = '0';
    this.allocatedGsunits[2].c4 = '0';
    this.allocatedGsunits[0].c5 = '0';
    this.allocatedGsunits[1].c5 = '0';
    this.allocatedGsunits[2].c5 = '0';
    this.allocatedGsunits[0].total = '0';
    this.allocatedGsunits[1].total = '0';
    this.allocatedGsunits[2].total = '0';
    this.formatChangesforDB();
    this.updateEs();
    console.error(this.updateEs());
  }

  updateEs() {
    if (this.allocationFlag) {
      this.errorMessage ="Cannot update Energy Sale!!! - Error in Allocation ";
			this.disableAllButtons=false;
			return false;
    } else if ((!this.isFossilFuel) && this.chargeFlag) {
      this.errorMessage ="Cannot update Energy Allotment!!! - Error in Allocation of Charges ";
			this.disableAllButtons=false;
			return false;
    }
      if (this.es.allowLowerSlotAdmt == null) {
        this.errorMessage ="Update the higher order to lower order slot and then proceed ";	
				this.disableAllButtons=false;
				return false;
      }
      this.service.updateEa(this.es).subscribe(
        result => {
          // console.log(this.es);
					this.disableAllButtons=false;
          this.listEss();

        },
        error => {        
          this.errorMessage = "Error updating allotment in database!";
					this.disableAllButtons=false;
        }
      );
    }
  
  finalEs() {
    this.errorMessage = "";
		this.disableAllButtons = true;
		this.processingMessage = "Please wait. Energy Allotment is finalising ...";
    this.checkAllocations('');
    this.isFinalDisabled = true;
    //save can be add or update
    this.formatChangesforDB();
    if (this.allocationFlag) {
      this.errorMessage ="Cannot Approve Energy Sale!!! - Error in Allocation ";
			this.disableAllButtons = false;
    }else if ((!this.isFossilFuel) && this.chargeFlag) {
      this.errorMessage ="Cannot Approve Energy Allotment!!! - Error in Allocation of Charges ";
			this.disableAllButtons=false;
			return false;
    }
     else {
      this.openConfirmDilog();
      // this.service.finalEs(this.es).subscribe(
      this.job.tEnergySaleId = this.es.id;
      console.log(this.job)
      this.service.triggerEnergySaleEvent(this.job).subscribe(
        result => {
					this.disableAllButtons=false;
          this.listEss();
        },
        error => {
          this.errorMessage = "Error finalising allotment in database!";
					this.disableAllButtons=false;
        }
      );
    }

  }
  dialogRef: MatDialogRef<BulkDialogComponent> | null;
  lastCloseResult: string;
  config = {
    disableClose: false,
    panelClass: 'custom-overlay-pane-class',
    hasBackdrop: true,
    backdropClass: '',
    width: '1500px',
    height: '650px',
    position: {
      top: '',
      bottom: '',
      left: '',
      right: ''
    },
    data: {
      esInput: this.es,
      commercialsInput: this.consumerCompanies,
      gsInput: this.gs
    }
  };
  numTemplateOpens = 0;
  bulkAllocation() {
    this.config.data = { "esInput": this.es, "commercialsInput": this.consumerCompanies, "gsInput": this.gs }
    this.dialogRef = this.dialog.open(BulkDialogComponent, this.config);

    this.dialogRef.afterClosed().subscribe((result: string) => {
      this.lastCloseResult = result;
      this.dialogRef = null;

      this.fetchEsAndGsFromDB(this.es.id);
      // this.router.navigateByUrl('/es/es-list');
    });
  }

  toggleColumn() {
    this.tempHideFlag = !this.tempHideFlag;
    console.log(this.tempHideFlag)
  }
  hideColumn({ row, column, value }) {

    if (column.name == 'C3') {
      console.log(this.tempHideFlag)
      console.log(column)
      console.log(value)
      // if(column.name =='C3') {
      return {
        'hide': this.tempHideFlag
      };
    }
  }


	fetchAvailableUnitSummary(){
		if(this.isFossilFuel){
			return this.availableGsUnits.filter((item) => item.heading == "Total");
		}
		else{
			return this.availableGsUnits;
		}
	}
	fetchAllocatedUnitSummary(){
		if(this.isFossilFuel){
			return this.allocatedGsunits.filter((item) => item.heading == "Total");
		}
		else{
			return this.allocatedGsunits;
		}
	}
}