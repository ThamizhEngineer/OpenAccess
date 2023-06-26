import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs/Rx';
import { ReportService } from '../../services/report.service';
import { CommonService } from '../../../shared/common/common.service';
import { GenericReportInput } from '../../vo/GenericReportInput';
import { DOCUMENT } from '@angular/common';
import { saveAs } from 'file-saver';
import { ExcelExportService } from '../../../shared/services/excelExport';
import { CommonUtils } from '../../../shared/common/common-utils';
import { ActivatedRoute, Params, Router } from '@angular/router';
// import { ConsoleReporter } from 'jasmine';


@Component({
  selector: 'weg-progress-report',
  templateUrl: './weg-progress-report.component.html',
})
export class WegProgressReportComponent implements OnInit {
  stmtMonth: string;
  stmtYear: any;
  fuelTypeCode: string;
  obj_processed: any = [];
  arrayList: any = [];
  rows: any = [];
  fuelTypeList = [

    { "key": "WIND", "name": "Wind" },
    { "key": "COAL", "name": "Coal" },
    { "key": "SOLAR", "name": "Solar" },
    { "key": "BIOGASS", "name": "Bio-Gas" },
    { "key": "NATURAL GAS", "name": "NaturalGas" },
    { "key": "WASTE HEAT", "name": "Waste Heat " },
    { "key": "BIO-MASS", "name": "Bio-Mass" },
    { "key": "BAGASSE", "name": "Bagasse " },
    { "key": "DIESEL", "name": "Diesel" }

  ];
  months: { value: string; name: string; }[];
 years:any[];
  // rows=[];
  fuelTypes: any;
  public rows1: any =[];
  rows2: any;
  rows3: any;
  rows4: any;
  rows5: any;
  totalMeterReading: any;
  totalCaptive: any;
  totalTotalAllot: any;
  totalThirdParty: any;
  totalManualReading: any;
  resultSet1: boolean = false;
  resultSet2: boolean = false;
  resultSet3: boolean = false;
  resultSet4: boolean = false;
  resultSet5: boolean = false;
  isLoading: boolean = false;
  rows6: any;
  totalMeterChange: any;
  constructor(private router: Router, private service: ReportService, private route: ActivatedRoute,
    private commonService: CommonService, private excelService: ExcelExportService) {
  }

  ngOnInit() {
    this.fetchMonths();
    this.fetchFuelTypes();
    this.years = this.commonService.fetchYearList(); 
  }

  reset() {
    let currentUrl = this.router.url;
    this.router.navigateByUrl('/', { skipLocationChange: true }).then(() => {
      this.router.navigate([currentUrl]);
    });
  }

  fetchMonths() {
    this.months = this.commonService.fetchMonths();
  }

  fetchFuelTypes() {
    this.commonService.fetchCodes("FUEL_TYPE_CODE").subscribe(x => {
      this.fuelTypes = x;
    })
  }

  // loading = () => {
  //   this.isLoading = true;
  //   //Faking an API call
  //   setTimeout(() => {
  //     this.isLoading = false;
  //     console.log("time")
  //   }, 1000);
  // };


  callAll() {
    let genRptInput: GenericReportInput = new GenericReportInput();

    if (this.stmtMonth != "" &&
      this.stmtMonth != null)
      genRptInput.ip2 = this.stmtMonth

    if (this.stmtYear != "" &&
      this.stmtYear != null)
      genRptInput.ip3 = this.stmtYear

    if (this.fuelTypeCode != "" &&
      this.fuelTypeCode != null)
      genRptInput.ip1 = this.fuelTypeCode

    this.searchResults1(genRptInput);
    

  }

  searchResults1(genRptInput: GenericReportInput) {
    genRptInput.reportName = 'Progress-report-Meter-reading';
    this.service.getWegProgressReport(genRptInput).subscribe(x => {
      this.rows1 = x;
      console.log(this.rows1)
      const mr = this.rows1.reduce((sum, item) => sum + Number(item.op4), 0);
      this.totalMeterReading = mr;
      this.searchResults2(genRptInput);
      this.searchResults3(genRptInput);
      this.searchResults4(genRptInput);
      this.searchResults5(genRptInput);
      this.searchResults6(genRptInput);
    })
  }

  searchResults2(genRptInput: GenericReportInput) {
    genRptInput.reportName = 'Progress-report-captive-allotment';
    this.service.getWegProgressReport(genRptInput).subscribe(x => {
      this.rows2 = x;
      // console.log(this.rows2)
      const tca = this.rows2.reduce((sum, item) => sum + Number(item.op6), 0);
      this.totalCaptive = tca;
      for (let i of this.rows1) {
        for (let j of this.rows2) {
          if (i.op2 == j.op2) {
            i.op8 = j.op6;
          }
        }
      }
    })
  }
  searchResults3(genRptInput: GenericReportInput) {
    genRptInput.reportName = 'Progress-report-third-party-allotment';

    this.service.getWegProgressReport(genRptInput).subscribe(x => {
      this.rows3 = x;

      const tpa = this.rows3.reduce((sum, item) => sum + Number(item.op6), 0);
      this.totalThirdParty = tpa;

      for (let i of this.rows1) {
        for (let j of this.rows3) {
          if (i.op2 == j.op2) {
            i.op9 = j.op6;
          }
        }
        if(i.op9==""){
          i.op9="0";
        }
      }
    })
  }
  searchResults4(genRptInput: GenericReportInput) {

    genRptInput.reportName = 'Progress-report-total-allotment';

    this.service.getWegProgressReport(genRptInput).subscribe(x => {
      this.rows4 = x;

      const tta = this.rows4.reduce((sum, item) => sum + Number(item.op6), 0);
      this.totalTotalAllot = tta;
      // console.log(this.rows1);
      for (let i of this.rows1) {
        for (let j of this.rows4) {
          if (i.op2 == j.op2) {
            i.op10 = j.op6;
          }
        }
        // this.rows1[i].op8 = this.rows1[i].op8 || '0';
      }
    })
    // console.log(this.rows1);
  }



  searchResults5(genRptInput: GenericReportInput) {
    genRptInput.reportName = 'Progress-report-manual-reading';

    this.service.getWegProgressReport(genRptInput).subscribe(x => {
      this.rows5 = x;
      const tmr = this.rows5.reduce((sum, item) => sum + Number(item.op3), 0);
      this.totalManualReading = tmr;
      console.log(this.totalManualReading)

      for (let i of this.rows1) {
        for (let j of this.rows5) {
          if (i.op2 == j.op2) {
            i.op11 = j.op3;
          }
        }
        // this.rows1[i].op8 = this.rows1[i].op8 || '0';
      }
    })
    console.log(this.rows1);
  }


  searchResults6(genRptInput: GenericReportInput) {
    genRptInput.reportName = 'Meter-change-list';

    this.service.getWegProgressReport(genRptInput).subscribe(x => {
      this.rows6 = x;
      const mcl = this.rows6.reduce((sum, item) => sum + Number(item.op1), 0);
      this.totalMeterChange = mcl;
      console.log(this.rows6)

      for (let i of this.rows1) {
        for (let j of this.rows6) {
          if (i.op2 == j.op2) {
            i.op12 = j.op1;
          }
        }
        if(i.op12==""){
          i.op12="0";
        }
      //   // this.rows1[i].op8 = this.rows1[i].op8 || '0';
      }
    })
    console.log(this.rows1);
  }
  //   searchResults5(genRptInput:GenericReportInput) {
  // console.log(genRptInput)
  //     genRptInput.reportName = 'Progress-report-manual-reading';

  //     this.service.getWegProgressReport(genRptInput).subscribe(x => {
  //       this.rows5 = x;
  //       const tmr = this.rows5.reduce((sum, item) => sum + Number(item.op3), 0);
  //       this.totalManualReading = tmr;
  // console.log(this.totalManualReading)

  //       for (let i of this.rows1) {     
  //         for (let j of this.rows5) {
  //           if (i.op2 == j.op2) {
  //             i.op11 = j.op3;
  //           }
  //         }
  //         // this.rows1[i].op8 = this.rows1[i].op8 || '0';
  //       }
  //     })
  //   }
  exportAsXLSX(): any {
    this.rows1.push({
      op1: "TOTAL", op2: "", op3: "", op4: this.totalMeterReading, op5: "", op6: "", op7: "", op8: this.totalCaptive,
      op9: this.totalThirdParty, op10: this.totalTotalAllot, op11: this.totalManualReading, op12: this.totalMeterChange
    })

    let str = JSON.stringify(this.rows1);
    str = str.replace(/\"op2\":/g, "\"ORG ID\":");
    str = str.replace(/\"op3\":/g, "\"EDC\":");
    str = str.replace(/\"op4\":/g, "\"METER READING\":");
    str = str.replace(/\"op5\":/g, "\"MONTH\":");
    str = str.replace(/\"op6\":/g, "\"YEAR\":");
    str = str.replace(/\"op7\":/g, "\"FUEL TYPE\":");
    str = str.replace(/\"op9\":/g, "\"THIRD PARTY\":");
    str = str.replace(/\"op8\":/g, "\"CAPTIVE ALLOTMENT\":");
    str = str.replace(/\"op10\":/g, "\"TOTAL ALLOTMENT\":");
    str = str.replace(/\"op11\":/g, "\"MANUAL READING\":");
    str = str.replace(/\"op12\":/g, "\"METER CHANGE\":");
    this.rows = JSON.parse(str);
    console.log(this.rows);
    this.rows.forEach(x =>{
      delete x.$$index;
      delete x.op1
      delete x.op13
      delete x.op14
      delete x.op15
      delete x.op16
      delete x.op17
      delete x.op18
      delete x.op19
      delete x.op20
      delete x.op21
      delete x.op22
      delete x.op23
      delete x.op24
      delete x.op25
      delete x.op26
      delete x.op27
      delete x.op28
      delete x.op29
      delete x.op30
      delete x.op31
      delete x.op32
      delete x.op33
      delete x.op34
      delete x.op35
      delete x.op36
      delete x.op37
      delete x.op38
      delete x.op39
      delete x.op40
      delete x.op41
    });
    console.log(this.rows);

    this.excelService.exportAsExcelFile(this.rows, 'PROGRESS-REPORT');
    this.callAll();
  }
}