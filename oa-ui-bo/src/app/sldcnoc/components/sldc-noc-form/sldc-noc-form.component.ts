import { CommonService } from './../../../shared/common/common.service';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute,Params,Router } from '@angular/router';

import { SdlcNocService } from '../../sldc-noc.service';
import { SldcApproval, SldcNOC,SldcNOCLine, VCompanyService } from '../vo/sldc-noc';
import { saveAs } from 'file-saver';
import { stringify } from 'querystring';

@Component({
    selector: '',
    templateUrl: 'sldc-noc-form.component.html',
    styleUrls: ['../../../../assets/styles/scss/components/_readonly-fields.scss']
})

export class SldcNocFormComponent implements OnInit {

    sldcNoc:SldcNOC;
    approvalnoc:SldcApproval;
    tabIndex:any;
    isCon:boolean;
    isRemarks:boolean=true;
    decisions = [
        { "key": "Y", "name": "YES" },
        { "key": "N", "name": "NO" }
      ]

    constructor(
        private activatedRoute:ActivatedRoute,
        private service:SdlcNocService,
        private router:Router,private commonService:CommonService
    ) { }
    bilthird=false;
    collSale=false;
    bil=false;
    bilSale = false;
    bilPur = false;
    collPur=false;
    captive=false;
    approvalNo: String;
    sldcNocLine:any;
    sldcApproval:SldcApproval[];
    sldcApprovalbuyer:any;
    sldcApprovalseller:any;
    mCompService=new VCompanyService();
    sldcLine:any;
    selsldcLine:any;
    sellerWithdraw:any;
    buyerWithdraw:any;
    capacityMW:Number;
    contrDmd:string;
    capToStr:string;
    Installcap:Number;
    busAval:Number;
    tempSum:Number;

    exBusData:Number;
    ngOnInit() {

        this.sldcNoc= new SldcNOC();
        this.approvalnoc = new SldcApproval();


        this.activatedRoute.params.subscribe(x => {
            this.service.getSldcNocById(x.id).subscribe(res=>{
                console.log(x.id);

                this.sldcNoc=res;
                this.Installcap=+this.sldcNoc.installCapPlant/1000;
                if(res.nocPurpose=='Buyer'){
                this.sldcLine=this.sldcNoc.sldcNocLine.filter(data=> data.quantum==res.quantity)
            }
            if(res.nocPurpose=='Seller'){
               this.selsldcLine=this.sldcNoc.sldcNocLine.filter(data=> data.isExisting=='N')
            }
            if(res.nocPurpose=='Seller'){
               this.service.sellerWithdraw(res.compServNo,'MTOA',this.approvalNo).subscribe(x=>{
                 this.sellerWithdraw=x;
                 console.log("came");
                 console.log(this.sellerWithdraw);
               })

            }

                this.sldcNoc.sldcNocLine.forEach(data=>{
                  this.sldcNocLine=data;

                    if(data.commitType == 'Intra State Collective Purchase'){
                        this.collPur=true;

                    }else if(data.commitType == 'Intra State Bilateral Purchase TANGEDCO'){
                        this.bilPur=true;

                    }else if(data.commitType == 'Intra State Bilateral Sale'){
                        this.bilSale=true;

                    }else if(data.commitType == 'Intra State Bilateral'){
                        this.bil=true;

                    }else if(data.commitType == 'Intra State Collective'){
                        this.collSale=true;

                }else if(data.commitType == 'Intra State Bilateral Third Party'){
                    this.bilthird=true;
                }

                else if(data.commitType == 'Intra State Bilateral Captive Use'){
                    this.captive=true;
                }
                })

                this.tabIndex=this.sldcNoc.isExisting=="Y" ? 0 : 1;
                this.isCon = this.sldcNoc.nocPurpose=='Buyer' ? true : false ;
                this.service.getcompDetail(res.compServNo).subscribe(y=>{
                    this.mCompService=y;
                    this.capacityMW=+this.mCompService.capacity/1000*0.9;
                    this.capToStr=this.capacityMW.toString();
                   this.contrDmd=parseFloat(this.capToStr).toFixed(3);

                })
                if(res.nocPurpose=='Buyer'){
                this.service.getexistingbuyer(res.compServNo).subscribe(x=>{
                    this.sldcApprovalbuyer=x;
                })
            }
               if(res.nocPurpose=='Seller'){
                this.service.getexistingseller(res.compServNo).subscribe(x=>{
                    this.sldcApprovalseller=x;
                    this.sldcApprovalseller.forEach(application => {
                        if (application.eobHtConsumerNumber === "000000000000") {
                          application.eobHtConsumerNumber = "TNEB";
                        }
                    console.log(this.sldcApprovalseller);
                    })
                })
            }
            })
        })

}

    back(){
        this.router.navigateByUrl('sldcnocs/sldc-noc');
    }

    checkRemarks(){
        if(this.sldcNoc.commRemark!=null||this.sldcNoc.legalRemark!=null||this.sldcNoc.techRemark!=null ||
            this.sldcNoc.commRemark!=''||this.sldcNoc.legalRemark!=''||this.sldcNoc.techRemark!=''){
            this.isRemarks=false;
        }else{
            this.isRemarks=true;
        }
    }

    openDownload(data: Response) {
		let content_type = data.headers.get('Content-type');
		let x_filename = "sldc-noc-"+this.sldcNoc.appliedNo+".pdf";
		console.log(content_type)
		saveAs(data.blob(), x_filename);
  }

    save(type){
        if(type!='save'){
            this.sldcNoc.status=type;
        }
        this.tempSum=+this.sldcNoc.intlAuxilary + +this.sldcNoc.intlInhouseConsump;
        this.exBusData=Number(this.Installcap)- Number(this.tempSum);
        this.sldcNoc.exBusAval=this.exBusData.toFixed(2);

        this.service.saveSldcNocById(this.sldcNoc,this.sldcNoc.id).subscribe(x=>{
            this.router.navigateByUrl('sldcnocs/sldc-noc');
        })
    }

    printSldcNoc(){
        if(this.sldcNoc.nocPurpose=='Seller'){
            console.log("seller");

        this.service.printSldcNoc(this.sldcNoc.id).subscribe(x=>{
            this.openDownload(x);
        },
        error=>{
          if (JSON.parse(error._body).message) {
             console.log(JSON.parse(error._body).message);
          } else {
            console.log("Too many request please try again later");
          }
        });
}
if(this.sldcNoc.nocPurpose=='Buyer'){
    console.log("buyer");

    this.service.printCusNoc(this.sldcNoc.id).subscribe(x=>{
        this.openDownload(x);
    },
    error=>{
      if (JSON.parse(error._body).message) {
         console.log(JSON.parse(error._body).message);
      } else {
        console.log("Too many request please try again later");
      }
    });
}
    }
          ///To allow number only
  numberFormat(event){
    this.commonService.numberOnly(event)
}
}
