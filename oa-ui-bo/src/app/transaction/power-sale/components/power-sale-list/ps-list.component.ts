import 'rxjs/add/operator/switchMap';
import 'rxjs/add/operator/startWith';

import { Component, OnInit, HostBinding } from '@angular/core';
import { DatePipe } from '@angular/common';
import { Router, ActivatedRoute, Params } from '@angular/router';
import { FormsModule, FormControl } from '@angular/forms';
import { CommonService } from '../../../../shared/common/common.service';
import { MatDatepickerModule } from '@angular/material';
import { MatNativeDateModule } from '@angular/material';
import { CommonUtils } from '../../../../shared/common/common-utils';
import { PowerSaleService } from '../../services/power-sale.service';
import { EsIntent ,EsIntentLine,Edc} from './../../../vo/es-intent';


import { NG_VALIDATORS,Validator,Validators,AbstractControl,ValidatorFn } from '@angular/forms';

@Component({
  selector: 'ps-list',
  templateUrl: './ps-list.component.html',
  providers: [PowerSaleService
  ]

})

export class PsListComponent {
    rows: Array<EsIntent>;

    esIntent:EsIntent;
    esIntentLine:EsIntentLine;
  constructor(
    private route: ActivatedRoute,
    private service: PowerSaleService,
    private commonService: CommonService,
    private router: Router) {

  }

  ngOnInit() {
    this.rows = [];

    this.service.getEsIntents().subscribe(
        _esIntent => {
          this.rows = _esIntent;
   
        });
  }
  editEsIntent(_id: string) {
   
    this.router.navigate(['/power-sale/fossil-fuel-captive-edit'] ,{queryParams:{id:_id}});
    
  }

  listEsIntent() {
    this.router.navigateByUrl('/sale/sale-create');
  }

  listPsStatus(_id: string){
    this.router.navigate(['/log/detail'] ,{queryParams:{id:_id}});
  }
  //   listPsStatus(){
  //   this.router.navigateByUrl('/log/detail');


  // }
}