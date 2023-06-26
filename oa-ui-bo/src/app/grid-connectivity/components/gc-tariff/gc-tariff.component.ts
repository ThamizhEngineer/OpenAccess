import { Component, OnInit, HostBinding } from '@angular/core';
import { GcService } from './../../services/gc.service';
import { Gc, GeneratorUnit } from './../../services/gc';

@Component({
  selector: 'gc-tariff',
  templateUrl: './gc-tariff.component.html',
})
export class GcTariffComponent implements OnInit {

    gc: Gc;

    isDisabled = false;
    isMandatory = false;

    constructor(private gcService: GcService) {

        this.gcService.gcEvent.subscribe(_gc => {
            this.gc = _gc;
            this.initTariff();
        })
    }

    ngOnInit() {
        this.gc = new Gc();
    }

    initTariff() {}
}