import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute, Params } from '@angular/router';
import { SdlcNocService } from '../../sldc-noc.service';
import { SldcNOC } from '../vo/sldc-noc';
@Component({
    selector: 'noc-approval-director',
    templateUrl: 'noc-approval-director-detail.component.html',
    styleUrls: ['../../../../assets/styles/scss/components/_readonly-fields.scss']
})

export class NocApprovalDirectorDetail implements OnInit {

    isValid:boolean=true;
    isCon:boolean;    
    sldcNoc:SldcNOC;

    constructor(private route: ActivatedRoute,
        private router: Router,private service:SdlcNocService) { }

    ngOnInit() {
        this.sldcNoc = new SldcNOC();
        this.route.params.subscribe(_params => {
            this.service.getSldcNocById(_params.id).subscribe(x=>{
                this.sldcNoc=x;
                this.isCon = this.sldcNoc.nocPurpose=='Buyer' ? true : false ; 
            })
            });
    }

    save(type){
        this.sldcNoc.status=type;
        this.service.saveSldcNocById(this.sldcNoc,this.sldcNoc.id).subscribe(x=>{
            this.router.navigateByUrl('sldcnocapproval/noc-approval-director');   
        })
    }

    back() {
        this.router.navigateByUrl('sldcnocapproval/noc-approval-director');
    }
}
