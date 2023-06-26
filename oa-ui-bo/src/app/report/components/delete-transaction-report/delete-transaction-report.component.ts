import { DatePipe } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { CommonUtils } from '../../../shared/common/common-utils';
import { CommonService } from '../../../shared/common/common.service';
import { ExcelExportService } from '../../../shared/services/excelExport';
import { ReportService } from '../../services/report.service';
import { GenericReportInput } from '../../vo/GenericReportInput';
import { GenericReportOutput } from '../../vo/GenericReportOutput';


@Component({
  selector: 'delete-transaction-report',
  templateUrl: './delete-transaction-report.component.html',
 
})
export class DeleteTransactionReportComponent implements OnInit {
  rows: Array<GenericReportOutput>;

  
  orgList = [];
 
  stmtMonth: string;
  uer: any;
  userTypeName: any;
  stmtYear: string;
  year=[
    { "id": "2019", "year": "2019" },
    { "id": "2020", "year": "2020" },
    { "id": "2021", "year": "2021" },
    { "id": "2022", "year": "2022" },
    { "id": "2023", "year": "2023" }
  ]
  edc: any;
  dispOrgCode: any;
  disableEdc: boolean;
  fuelTypeCode: string;
  months: { value: string; name: string; }[];
  edcList: any;
  fuelTypes: any;
  Remarks:any;
  Date:any;


  constructor(private datePipe: DatePipe,private router: Router,private service:ReportService, private commonService:CommonService,private excelService: ExcelExportService) {

  }
  ngOnInit() {
   
      this.edc = CommonUtils.getLoginUserEDC();
    
    this.fetchMonths();
    this.fetchOrgList();


  }

  searchResults(){
    let genRptInput:GenericReportInput = new GenericReportInput();
    genRptInput.reportName = 'Delete-Transaction-Report';

      if(this.stmtMonth != "" &&
    this.stmtMonth != null)
      genRptInput.ip1 = this.stmtMonth

      if(this.stmtYear != "" &&
    this.stmtYear != null)
      genRptInput.ip2 = this.stmtYear



      if(this.dispOrgCode != "" &&
    this.dispOrgCode != null)
      genRptInput.ip4 = this.dispOrgCode;
     
      this.service.getDeleteTransactionReport(genRptInput).subscribe(x=>{
        this.rows=x;
        
    })

    
  }

  fetchMonths(){
    this.months = this.commonService.fetchMonths();
   
  }

  fetchOrgList() {
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



    }

