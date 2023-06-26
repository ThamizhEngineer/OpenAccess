import 'rxjs/add/operator/switchMap';
import { Component, OnInit, HostBinding } from '@angular/core';
import { Router, ActivatedRoute, Params } from '@angular/router';

// import { MeterReadingDetailComponent } from './../../components/meter-reading-detail/meter-reading-detail.component';
import { CommonUtils } from '../../../../shared/common/common-utils';
import { IexNocService } from './../../services/iex-noc.service';
import { CommonService } from './../../../../shared/common/common.service';
import { IexNoc } from './../../../vo/iex-noc';
@Component({
  selector: 'iex-noc-list',
  templateUrl: './iex-noc-list.component.html',
  providers: [IexNocService, CommonUtils]
})
export class IexNocListComponent implements OnInit {

    iexNoc: IexNoc;
    rows: Array<IexNoc>;
    pageNumber = 0;
    pageSize = 10;
    
  constructor(
    private commonUtils: CommonUtils,
    private route: ActivatedRoute,
    private router: Router,
    private commonService: CommonService,
    private service: IexNocService
  ) {

  }


  ngOnInit() {
    this.searchResultsByPage({ offset: 0 });
    }

    searchResultsByPage(pageInfo) {
        this.pageNumber = pageInfo.offset;

        this.service.getAllIexNocs(this.pageNumber, this.pageSize).subscribe(
          _iexnoc => {
            this.rows = _iexnoc.content;
            console.log("In Iex Noc search result")
            console.log(this.rows);
          });
      }

      editNoc(_id: string) {
        try {
          console.log("Edit id value" + _id);
          this.router.navigateByUrl('/iex-noc/iex-noc-edit/' + _id);
        }
        catch (e) {
          console.log(e);
        }
      }


}