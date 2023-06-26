import 'rxjs/add/operator/switchMap';
import { Component, OnInit, HostBinding } from '@angular/core';
import { Router, ActivatedRoute, Params } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { DatePipe } from '@angular/common';
import { CommonUtils } from '../../../../shared/common/common-utils'
import { IexNocService } from './../../services/iex-noc.service';
import { CommonService } from './../../../../shared/common/common.service';
import { IexNoc } from './../../../vo/iex-noc';
import { FormControl } from '@angular/forms';

@Component({
  selector: 'iex-noc-detail',
  templateUrl: './iex-noc-detail.component.html',
  providers: [CommonService, IexNocService, DatePipe, CommonUtils]
})

export class IexNocDetailComponent {
  addScreen: boolean = true;
  iexnoc: IexNoc;
  rows = [];
  disableControls: boolean = true;
  exemptRc = [
    { "key": "Y", "name": "Y" },
    { "key": "N", "name": "N" }
  ]
  hasDues = [
    { "key": "Y", "name": "Y" },
    { "key": "N", "name": "N" }
  ]

  constructor(
    private commonUtils: CommonUtils,
    private route: ActivatedRoute,
    private router: Router,
    private commonService: CommonService,
    private datePipe: DatePipe,
    private service: IexNocService) {
  }

  ngOnInit() {
    this.iexnoc = new IexNoc();
    console.log(this.route.params['_value']);
    if (this.route.params['_value']['_id']) {
      //route.params['value'] will have the '_id' in it, during edit 
      this.addScreen = false;
      this.route.params
        .switchMap((params: Params) => this.service.getIexNocById(this.route.params['_value']['_id']))
        .subscribe((_iexnoc: IexNoc) => {
          this.iexnoc = _iexnoc;
          console.log(this.iexnoc)

        });

    }
  }


  approveIexNoc() {
    this.service.approveIexNoc(this.iexnoc).subscribe(
      result => {
        this.listNoc();
      },
      error => {
        console.error('Error approving Noc!');
        console.error(error);
      }
    );
  }

  listNoc() {
    this.router.navigateByUrl('/iex-noc/iex-noc-list');
  }

}