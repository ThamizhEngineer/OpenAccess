import { filter } from 'rxjs/operator/filter';
import { Component, OnInit } from '@angular/core';
import { ReportService } from '../../services/report.service';
import { CommonService } from '../../../shared/common/common.service';
import { GenericReportInput } from '../../vo/GenericReportInput';
import { CommonUtils } from '../../../shared/common/common-utils';
import { ExcelExportService } from '../../../shared/services/excelExport';
import { saveAs } from 'file-saver';
@Component({
  selector: 'solarfeeder-edcwise-component',
  templateUrl: './solarfeeder-edcwise-report.html'
//   styleUrls: ['./consolidate-energy-adjustment-report.component.scss']
})
export class SolarfeederEdcwiseComponent implements OnInit {
  
  Year= [
    { "id": "2018", "year": "2018" },
   { "id": "2019", "year": "2019" },
    { "id": "2020", "year": "2020" },
   { "id": "2021", "year": "2021" },
    { "id": "2022", "year": "2022" },
    { "id": "2023", "year": "2023" }
 ]
  months: { value: string; name: string; }[];
  opt: any;
  orgList: any;
  substationName:any;
  dispOrgCode: any;
  readYear: any;
  readMonth: string;
  fuelTypes: any;
 substaion:any;
 subsationById:any;
 OrgName:any;
   tempId:any;
  disableEdc:boolean;
  Usertype:any;
  edcid:any;

  constructor(private service:ReportService, private commonService:CommonService,private excelService: ExcelExportService) {
 
  }

    ngOnInit(): void {
      
      this.fetchOrgList();
      this.Usertype=CommonUtils.getLoginUserTypeName();
       console.log(this.Usertype);
      
      this.edcid = CommonUtils.getLoginUserEDC();
      if (this.edcid != "") {
        console.log("in edc select")
        this.dispOrgCode = this.edcid;
        console.log(this.dispOrgCode);
        this.filterSubstations(this.dispOrgCode);
        this.disableEdc = true; 
      }
     
      this.fetchMonths();
    }

    fetchMonths() {
      this.months = this.commonService.fetchMonths();
    }

    fetchOrgList() {
      this.commonService.fetchEDCs().subscribe(
        result => {
          this.orgList = result;
          console.log(this.orgList)
        },
        error => {
          console.error('Error loading states!');
          console.error(error);
        }
      );
    }
    searchResults(){
      let genRptInput:GenericReportInput = new GenericReportInput();
      genRptInput.reportName = 'SOLAR FEEDER EDC WISE REPORT';   

      if(this.readMonth != "" && 
      this.readMonth != null){
       genRptInput.ip1 = this.readMonth 
     }
     if(this.readYear != "" && 
     this.readYear != null){
      genRptInput.ip2 = this.readYear  
    }
    if(this.substationName != "" && 
            this.substationName != null){
            genRptInput.ip3 = this.substationName
          }
      if(this.dispOrgCode != "" && 
            this.dispOrgCode != null){
                     genRptInput.ip4 = this.dispOrgCode
          }
          
          this.service.getSolarFeederwise(genRptInput).subscribe(x=>{
            this.opt=x;    
            console.log(this.opt);
        })  
      }


//      filteredSubstationEDC(edc){
//   this.commonService.fetchSubstationsByOrgId(this.OrgName).subscribe(x=>{
//     this.subsationById=x.sort((a, b) => (a.name > b.name) ? 1 : ((b.name > a.name) ? -1 : 0));
//   })
// }

// filteredSubstation(){
//   console.log(this.dispOrgCode);
//   const tempOrgId= this.orgList.filter(item => item.name==this.dispOrgCode)
//   this.tempId=tempOrgId[0].id;
//   console.log(this.tempId);

//  const substatiomtemp=this.substaion.filter(item => item.orgId==this.tempId);
//  this.subsationById=substatiomtemp.sort((a, b) => (a.name > b.name) ? 1 : ((b.name > a.name) ? -1 : 0))
//     console.log(this.subsationById);
//      }
   
     exportAsXLSX():any {
      let str = JSON.stringify(this.opt);
      str = str.replace(/\"op1\":/g, "\"SUBSTATION NAME\":");
      str = str.replace(/\"op3\":/g, "\"EDC NAME\":");
      str = str.replace(/\"op4\":/g, "\"FEEDER NAME\":");
      str = str.replace(/\"op5\":/g, "\"FEEDER METER NO\":");
      str = str.replace(/\"op6\":/g, "\"VOLTAGE\":");
      str = str.replace(/\"op9\":/g, "\"EXPORT UNITS(KWH)\":");
      str = str.replace(/\"op7\":/g, "\"TOTAL SERVICES CONNECTED IN FEEDER\":");
      str = str.replace(/\"op8\":/g, "\"BILLED SERVICES\":");
      str = str.replace(/\"op12\":/g, "\"FEEDER LINE LENGTH(KM)\":");
  
      this.opt = JSON.parse(str);
    
      this.opt.forEach(x =>{
        delete x.$$index;
        delete x.op9;delete x.op10;delete x.op11;delete x.op2;
        delete x.op12;delete x.op13;delete x.op14;delete x.op15;delete x.op16;delete x.op17;
        delete x.op18;delete x.op19;delete x.op20;
        delete x.op21;delete x.op22;delete x.op23;delete x.op24;delete x.op25;delete x.op26;delete x.op27;delete x.op28;
        delete x.op29;delete x.op30;delete x.op31;delete x.op32;delete x.op33;delete x.op34;delete x.op35;delete x.op36;
        delete x.op37;delete x.op38;delete x.op39;delete x.op40;delete x.op41;
      });
    
    this.excelService.exportAsExcelFile(this.opt, 'SOLARFEEDER-EDCWISE-REPORT');
    this.searchResults();
    }

    openDownload(data: Response) {
      let content_type = data.headers.get('Content-type');
      let x_filename = "solar-feeder-edc-wise-report.pdf";
      // console.log(content_type)
      saveAs(data.blob(), x_filename);
    }

    printReport(){
      this.service.solarfeeederPrint(this.readMonth,this.readYear,this.substationName,this.dispOrgCode).subscribe(xs=>{
        this.openDownload(xs);
      });
    }
    filterSubstations(Orgid){
      this.service.getSubstaion(Orgid).subscribe(x=>{ 
        this.subsationById=x.sort((a, b) => (a.name > b.name) ? 1 : ((b.name > a.name) ? -1 : 0));
        console.log("ID");
        
      })
      }
  }
