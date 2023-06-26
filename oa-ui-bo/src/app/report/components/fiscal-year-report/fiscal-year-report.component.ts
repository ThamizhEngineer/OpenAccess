import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs/Rx';
import { ReportService } from '../../services/report.service';
import { CommonService } from '../../../shared/common/common.service';
import { GenericReportInput } from '../../vo/GenericReportInput';
import { DOCUMENT } from '@angular/common';
import { saveAs } from 'file-saver';
import { ExcelExportService } from '../../../shared/services/excelExport';
import { CommonUtils } from '../../../shared/common/common-utils';
import { ActivatedRoute, Params } from '@angular/router';


@Component({
  selector: 'fiscal-year-report',
  templateUrl: './fiscal-year-report.component.html',
})
export class FiscalYearReportComponent implements OnInit {
    months: { value: string; name: string; }[];

    rows: any;
    Orgid: any;
    Year: any;
    edcList: any;
    printdata: any;

    yearList = [

      { "key": "1997", "name": "1997-1998" },
      {"key": "1998", "name": "1998-1999" },
      { "key": "1999", "name": "1999-2000" },
      { "key": "2000", "name": "2000-2001" },
      {"key": "2001", "name": "2001-2002" },
      { "key": "2002", "name": "2002-2003" },
      { "key": "2003", "name": "2003-2004" },
      {"key": "2004", "name": "2004-2005" },
      { "key": "2005", "name": "2005-2006" },
      { "key": "2006", "name": "2006-2007" },
      {"key": "2007", "name": "2007-2008" },
      { "key": "2008", "name": "2009-2010" },
      { "key": "2010", "name": "2010-2011" },
      {"key": "2011", "name": "2011-2012" },
      { "key": "2012", "name": "2012-2013" },
      { "key": "2013", "name": "2013-2014" },
      {"key": "2014", "name": "2014-2015" },
      { "key": "2015", "name": "2015-2016" },
      { "key": "2016", "name": "2016-2017" },
      { "key": "2017", "name": "2017-2018" },
      {"key": "2018", "name": "2018-2019" },
      { "key": "2019", "name": "2019-2020" },
      { "key": "2020", "name": "2020-2021" },
      {"key": "2021", "name": "2021-2022" },
      { "key": "2022", "name": "2022-2023" },
      { "key": "2023", "name": "2023-2024" },
      {"key": "2024", "name": "2024-2025" },
      {"key": "2025", "name": "2025-2026" },
     ];

 flowTypeList=[
        
  { "value": "IS-CAPTIVE", "name": "CAPTIVE" },
  { "value": "THIRD-PARTY", "name":"THIRD-PARTY"},
  { "value": "STB", "name":"SALE-TO-BOARD"},
];

isRecList=[
        
  { "value": "Y", "name": "REC" },
  { "value": "N", "name":"NON-REC"},
];

statusList=[
        
  { "value": "LIVE", "name": "LIVE" },
  { "value": "DISMANTLE", "name":"DISMANTLE"},
  { "value": "MNS", "name":"MNS"},
];
  edcid: any;
  flowType:any;
  disableEdc:boolean;
  isRec:any;
  District:any;
  substation:any;
  subsationById: any;
  districtData: any;
  substaions: any;
  substaion: any;
  status: any;

    constructor(private service:ReportService,private route: ActivatedRoute, private commonService:CommonService,private excelService: ExcelExportService) {
    }

    ngOnInit() {
      this.fetchOrgList();
      this.fetchDist();
      this.edcid = CommonUtils.getLoginUserEDC();
      if (this.edcid != "") {
        console.log("in edc select")
        this.Orgid = this.edcid;
        console.log(this.Orgid);
        this.filterSubstations(this.Orgid);
        this.disableEdc = true; 
      }
           
    }



  searchResults(){
    let genRptInput:GenericReportInput = new GenericReportInput();
    genRptInput.reportName = 'MASTER SOLAR REPORT';

    if(this.Orgid != "" &&
    this.Orgid != null)
      genRptInput.ip1 = this.Orgid

      if(this.Year != "" &&
    this.Year != null)
      genRptInput.ip2 = this.Year

      if(this.flowType != "" &&
      this.flowType != null)
        genRptInput.ip3 = this.flowType

        if(this.substation != "" &&
        this.substation != null)
          genRptInput.ip4 = this.substation

          if(this.isRec != "" &&
          this.isRec != null)
            genRptInput.ip5 = this.isRec

            if(this.District != "" &&
            this.District != null)
              genRptInput.ip6 = this.District


            if(this.status != "" &&
            this.status != null)
              genRptInput.ip7 = this.status
      
      this.service.getFiscalYear(genRptInput).subscribe(x=>{
        this.rows=x;

         console.log(this.rows);
    })

  }

  fetchOrgList() {
    this.commonService.fetchEDCs().subscribe(
      result => {
        this.edcList = result;
        console.log(this.edcList)
      },
      error => {
        console.error('Error loading states!');
        console.error(error);
      }
    );
  }

fetchDist(){
  this.commonService.fetchDistrict().subscribe(x=>{
    this.districtData=x;
  })
}

  openDownload(data: Response) {
    let content_type = data.headers.get('Content-type');
    let x_filename = "master-solar-report.pdf";
    console.log(content_type)
    saveAs(data.blob(), x_filename);
  }

  printReport(){
    this.service.printfiscal(this.Orgid,this.Year,this.flowType,this.substation,this.isRec,this.District,this.status).subscribe(xs=>{
      this.printdata=xs;
      this.openDownload(xs);
    });
  }


  exportAsXLSX():any {

    let str = JSON.stringify(this.rows);

    str = str.replace(/\"op1\":/g, "\"EDC ID\":");
    str = str.replace(/\"op2\":/g, "\"EDC NAME\":");
    str = str.replace(/\"op3\":/g, "\"SERVICE NO\":");
    str = str.replace(/\"op4\":/g, "\"GENERATOR NAME\":");
    str = str.replace(/\"op5\":/g, "\"COMMISSION DATE\":");
    str = str.replace(/\"op7\":/g, "\"CAPACITY\":");
    str = str.replace(/\"op9\":/g, "\"DISTRICT\":");
    str = str.replace(/\"op10\":/g, "\"FLOW TYPE\":");
    str = str.replace(/\"op11\":/g, "\"REC/NON-REC\":");
if(this.flowType=='STB'&& this.flowType!=undefined){
    str = str.replace(/\"op15\":/g, "\"TARIFF\":");
  }
  str = str.replace(/\"op17\":/g, "\"CT RATIO\":");
  str = str.replace(/\"op18\":/g, "\"PT RATIO\":");
  str = str.replace(/\"op19\":/g, "\"MF\":");
  str = str.replace(/\"op20\":/g, "\"MODEM NUMBER\":");
  str = str.replace(/\"op21\":/g, "\"METER NO\":");
  str = str.replace(/\"op23\":/g, "\"SUBSTATION NAME\":");
  str = str.replace(/\"op24\":/g, "\"FEEDER NAME\":");
  str = str.replace(/\"op26\":/g, "\"FEEDER LINE LENGTH\":");
  str = str.replace(/\"op27\":/g, "\"TYPE OF SS\":");
  str = str.replace(/\"op28\":/g, "\"PLANT STATUS\":");
  str = str.replace(/\"op29\":/g, "\"REMARKS\":");

    this.rows = JSON.parse(str);
    this.rows.forEach(x =>{
      delete x.$$index;
      delete x.op6;
      delete x.op7;delete x.op8;delete x.op9;delete x.op10;delete x.op11;delete x.op12
      delete x.op13;delete x.op14;delete x.op15;delete x.op16;delete x.op17;delete x.op18;delete x.op19;delete x.op20;
      delete x.op21;delete x.op22;delete x.op23;delete x.op24;delete x.op25;delete x.op26;delete x.op27;delete x.op28;
      delete x.op29;delete x.op30;delete x.op31;delete x.op32;delete x.op33;delete x.op34;delete x.op35;delete x.op36;
      delete x.op37;delete x.op38;delete x.op39;delete x.op40;delete x.op41;
    });

    this.excelService.exportAsExcelFile(this.rows,'master-solar-report');
    this.searchResults();
}

filterSubstations(Orgid){
this.service.getSubstaion(Orgid).subscribe(x=>{ 
  this.subsationById=x.sort((a, b) => (a.name > b.name) ? 1 : ((b.name > a.name) ? -1 : 0));
  console.log("ID");
  
})
}

}