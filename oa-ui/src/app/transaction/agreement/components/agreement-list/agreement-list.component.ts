
import 'rxjs/add/operator/switchMap';
import { Component, OnInit, HostBinding } from '@angular/core';
import { Router, ActivatedRoute, Params } from '@angular/router';
import { Agreement } from './../../../vo/agreement';
import { Consent } from './../../../vo/consent';
import { AgreementService } from './../../services/agreement.service';
import { CommonService } from './../../../../shared/common/common.service';

import { CommonUtils } from '../../../../shared/common/common-utils';
import { MatAutocompleteModule, MatButtonModule, MatButtonToggleModule, MatCardModule, MatCheckboxModule, MatChipsModule, MatStepperModule, MatDatepickerModule, MatDialogModule, MatExpansionModule, MatGridListModule, MatIconModule, MatInputModule, MatListModule, MatMenuModule, MatNativeDateModule, MatPaginatorModule, MatProgressBarModule, MatProgressSpinnerModule, MatRadioModule, MatRippleModule, MatSelectModule, MatSidenavModule, MatSliderModule, MatSlideToggleModule, MatSnackBarModule, MatSortModule, MatTableModule, MatTabsModule, MatToolbarModule, MatTooltipModule } from '@angular/material';


@Component({
  selector: 'agreement-list',
  templateUrl: './agreement-list.component.html',
  //   styleUrls: ['./gs-list.component.scss'],
  providers: [CommonUtils]
})
export class AgreementListComponent implements OnInit {
  agreement:Agreement;
  rows:Array<Agreement>;
  agreementTypeName:string="";
  agreementType = [
    { "key": "Consent", "name": "Consent" },
    { "key": "Noc", "name": "Noc" },
    { "key": "Oaa", "name": "Oaa" },
    { "key": "Ewa", "name": "Ewa" },
    { "key": "Epa", "name": "Epa" },
    { "key": "Grid-Connectivity", "name": "Grid-Connectivity" }];
  selectedAgreement: string;
  searchSellerOrgId:string;
  sellerCompanyServices=[];
  viewType: string;
  edcList=[];
  statuses =[{ "key": "CREATED", "name": "Created" },
  { "key": "SIGNED", "name": "Signed" },]
  searchSellerCompanyServiceId:string;
  searchStatus: string;
  searchAgreementCode: string;
  searchEsIntentCode: string;
  disableEdc: boolean = false;
  constructor(
    private commonUtils: CommonUtils,
    private route: ActivatedRoute,
    private router: Router,
    private commonService: CommonService,
    private service: AgreementService,
  ) {
  }

  ngOnInit() {
    // console.log("In list load")
    // console.log(this.router.url.split('/')[3]);
    // this.viewType = this.router.url.split('/')[3];
    this.agreement = new Agreement();
    this.rows = new Array<Agreement>();
    this.agreement.sellerOrgId = CommonUtils.getLoginUserDetails().substr(3,3); 
   
    if ((this.agreement.sellerOrgId  != "") && isFinite(parseInt(this.agreement.sellerOrgId ))) {
      this.searchSellerOrgId = this.agreement.sellerOrgId;
     
      this.disableEdc = true;
      this.onEdcChange();
    }
   this.fetchOrgList();
    
  }

  fetchOrgList(){
    this.commonService.fetchEDCs().subscribe(
      result => { 
        this.edcList = result;
      },
      error => {
         console.error('Error loading states!');
         console.error(error);
      }
      );
  }
  onEdcChange() {
    console.log("In edc change");
    this.service.getAgreementCompanyByEdc(this.searchSellerOrgId,"").subscribe(
      _companyServices => {
        this.sellerCompanyServices = _companyServices;
        console.log(this.sellerCompanyServices)
     
      }
    )
  }
  searchResults(){
    this.service.getAllAgreements(this.searchSellerOrgId,this.searchSellerCompanyServiceId,this.searchAgreementCode,this.searchEsIntentCode,this.searchStatus).subscribe(agreements=>{
      this.rows =agreements;
    
      this.rows.forEach(element=>{
        console.log(element)
     if(element.oaaId!=null){
        element.agreementTypeName="Open Access Agreement";
     }else if(element.ewaId!=null){
      element.agreementTypeName="Energy Wheeling Agreement";
     }else if(element.epaId!=null){
      element.agreementTypeName="Power Purchase Agreement";
     }
      });

    });
  }

  // getAgreements() {
  //   console.log("In getAgreement");
  //   console.log(this.selectedAgreement);
  //   if (this.selectedAgreement == "Consent") {
  //     this.router.navigateByUrl('agreement/agreement-list/Consent');
  //   } else if (this.selectedAgreement == "Noc") {
  //     console.log("In getNocAgreement");
  //     this.router.navigateByUrl('agreement/agreement-list/Noc');
  //   }
  //   else if (this.selectedAgreement == "Ewa") {
  //     console.log("In getEwaAgreement");
  //     this.router.navigateByUrl('agreement/agreement-list/Ewa');
  //   } else if (this.selectedAgreement == "Oaa") {
  //     console.log("In getOaaAgreement");
  //     this.router.navigateByUrl('agreement/agreement-list/Oaa');
  //   } else if (this.selectedAgreement == "Epa") {
  //     console.log("In getEpaAgreement");
  //     this.router.navigateByUrl('agreement/agreement-list/Epa');
  //   }
  // }
  editAgreement(id:string){
    this.router.navigateByUrl('agreement/agreement-detail/'+id);

  }
}
