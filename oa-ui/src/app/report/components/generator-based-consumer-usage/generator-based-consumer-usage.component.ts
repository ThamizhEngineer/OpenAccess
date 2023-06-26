import { Component, OnInit, HostBinding, Injectable } from '@angular/core';
import { Router, ActivatedRoute, Params } from '@angular/router';
import { Subject } from 'rxjs/Subject';
import { FormsModule } from '@angular/forms';
import { CommonService } from '../../../shared/common/common.service';
import { ReportService } from './../../services/report.service';
import { Criteria } from './../../vo/report';


@Component({
    selector: 'generator-based-consumer-usage',
    templateUrl: './generator-based-consumer-usage.component.html',
    //styleUrls: [],
    providers: []
})

export class GeneratorBasedConsumerUsageComponent implements OnInit {
    searchIsCaptive: string = "";
    searchParentOrgCode: string = "";
    searchVoltageCode: string = "";
    regions: string;
    voltages: string;
    criteria: Criteria;
    isCaptives = [
        { "key": "Y", "name": "Y" },
        { "key": "N", "name": "N" }];
    rows: Array<Criteria>;

    months = [];
    constructor(
        private commonService: CommonService,
        private service: ReportService,
        private router: Router,
    ) {

    }
    ngOnInit() {
        this.criteria = new Criteria();
        this.service.getAllRegions().subscribe(res => {
            this.regions = res;        
        });

        this.commonService.fetchCodes('VOLTAGE_CODE').subscribe(
            result => {
                this.voltages = result;
             },

            error => {
                console.error('Error loading Voltages');
                console.error(error);
            });
    }

    searchReport() {
        this.service.fetchGeneratorBasedConsumerUsage(this.searchVoltageCode, this.searchParentOrgCode, this.searchIsCaptive).subscribe(
            res => {
                this.rows = res;
            }
        )
    }

	printPage(){
		var originalContents = document.body.innerHTML;
		var printReport= document.getElementById('printSection').innerHTML;
		document.body.innerHTML = printReport;
		window.print();
		document.body.innerHTML = originalContents;
	}






}