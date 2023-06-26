import { Component, OnInit, HostBinding, Input } from '@angular/core';

import { Gc ,DocCheckListItem } from './../../services/gc'; 
import { GcService } from './../../services/gc.service';

@Component({
  selector: 'gc-checklist',
  templateUrl: './gc-checklist.component.html',
  
})
export class GcChecklistComponent implements OnInit {

    gc: Gc;
    checkList = [];
    @Input() checkListCodes = [];

    ischecked = false;

    constructor(private gcService: GcService) {

        this.gcService.gcEvent.subscribe(_gc => {
            this.gc = _gc;
            this.checkList = this.gc.checkList;
            if (this.gc.checkList && this.gc.checkList.length < 1) {
                this.constructCheckLists();
            }

        });

    }

    ngOnInit() {
        this.gc = new Gc();
    }

    onValueChange() {
        let temp = [];
        if (this.checkList.length > 0) {
            this.gc.checkList = [];
            this.checkList.forEach(function(clItem) {
                if (clItem.docSubmitted == true || clItem.docSubmitted == "Y") {
                    clItem.docSubmitted = "Y";
                } else {
                    clItem.docSubmitted = "N";
                }
                temp.push(clItem);
            });
            this.gc.checkList = temp;
        }
    }

    constructCheckLists() {
        let temp = this.checkListCodes;
        let c: DocCheckListItem = new DocCheckListItem();
        temp.forEach(clItem => {
            c = new DocCheckListItem();
            c.documentCode = clItem.valueCode;
            c.documentDesc = clItem.valueDesc;
            c.docSubmitted = 'Y';
            c.docUrl = '';
            this.checkList.push(c);
        });
    }

}