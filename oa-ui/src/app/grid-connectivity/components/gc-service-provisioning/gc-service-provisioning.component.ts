import 'rxjs/add/operator/startWith';
import { Component, OnInit, HostBinding, Input } from '@angular/core';
import { FormControl } from '@angular/forms';
import { GcService } from './../../services/gc.service';
import { Gc, GeneratorUnit } from './../../services/gc';
import { OrgService } from './../../../master/organisation/services/org.service';
import { DatePipe } from '@angular/common';
@Component({
  selector: 'gc-service-provisioning',
  templateUrl: './gc-service-provisioning.component.html',
  providers: [OrgService,DatePipe]

})
export class GcServiceProvisioningComponent implements OnInit {

    @Input() meterMake = [];
    gc: Gc;

    genUnit: GeneratorUnit;
    reactiveEDCNames: any;
    EDCNameCtrl: FormControl;
    isABTMeter = [{
            "key": "Y",
            "name": "Y"
        },
        {
            "key": "N",
            "name": "N"
        }
    ];
    accuracyclass = [{
            "key": "0.2",
            "name": "0.2"
        },
        {
            "key": "0.5",
            "name": "0.5"
        }
    ]

    EDCs = [];

    isDisabled = false;
    isMandatory = false;

    constructor(private gcService: GcService, private orgService: OrgService, private datePipe: DatePipe) {
        this.gcService.gcEvent.subscribe(_gc => {
            this.gc = _gc;

        })
        this.orgService.getEDCs().subscribe(
            _edcs => {
                _edcs.forEach(edc => {
                    this.EDCs.push({
                        'code': edc.code,
                        'name': edc.name
                    });
                });
            });

        this.EDCNameCtrl = new FormControl();
        this.reactiveEDCNames = this.EDCNameCtrl.valueChanges
            .startWith(this.EDCNameCtrl.value)
            .map(val => this.displayFn(val))
            .map(name => this.filterEDCName(name));
    }

    ngOnInit() {
        this.gc = new Gc();
        this.gc.genUnits = new Array < GeneratorUnit > ();
        this.initGenServiceDetails();

        this.genUnit = new GeneratorUnit();
    }

    displayFn(value: any): string {
        return value && typeof value === 'object' ? value.name : value;
    }

    filterEDCName(val: string) {
        return val ? this.EDCs.filter((edc) => edc.name.match(new RegExp(val, 'gi'))) : this.EDCs;
    }
    updateFinalCod(finalCod: string, val: string) {
        this.genUnit = this.gc.genUnits[val];
        this.genUnit.finalCod = finalCod;
        this.genUnit.finalCod = this.datePipe.transform(this.genUnit.finalCod, 'yyyy-MM-dd');
        this.gc.genUnits[val] = this.genUnit;
        this.gcService.setGc(this.gc);

    }
    updateFinalCopd(finalCopd: string, val: string) {
        this.genUnit = this.gc.genUnits[val];
        this.genUnit.finalCopd = finalCopd;
        this.genUnit.finalCopd = this.datePipe.transform(this.genUnit.finalCopd, 'yyyy-MM-dd');
        this.gc.genUnits[val] = this.genUnit;
        this.gcService.setGc(this.gc);

    }

    initGenServiceDetails() {
        if (!this.gc.genServiceDetails) {
            this.gc.genServiceDetails = [];
        }
        this.genUnit = Object.assign({}, {
            name: '',
            capacity: '',
            make: '',
            serialNumber: '',
            mwRating: '',
            mvRating: '',
            kvRating: '',
            amateurResistance: '',
            directAssistanceReactance: '',
            negativeSequenceReactance: '',
            zeroSequenceReactance: '',
            directAxisTransientReactance: '',
            quadratureAxisTransientReactance: '',
            directAxisSubTransientReactance: '',
            inertia: '',
            dampingFactor: '',
            windingConnection: '',
            massNumber: '',
            stiffnessCoefficient: '',
            status: ''
        });
    }
}