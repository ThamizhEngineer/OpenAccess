import { Component, OnInit, HostBinding, Input } from '@angular/core';
import { Gc, GeneratorUnit } from './../../services/gc';
import { GcService } from './../../services/gc.service';

@Component({
  selector: 'gc-gen-units',
  templateUrl: './gc-gen-units.component.html'
})
export class GcGeneratorUnitsComponent implements OnInit {

    gc: Gc;

    genUnit: GeneratorUnit;
    genUnitRowIndex: number;
    isWind: boolean = false;
    generatorMakes = ["Suzlon", "Yamaha", "GE"];

    @Input() generatorModelTypes = [];

    isDisabled = false;
    isMandatory = false;

    constructor(private gcService: GcService) {
        this.gcService.gcEvent.subscribe(_gc => {
            this.gc = _gc;
        })
    }

    ngOnInit() {
        this.gc = new Gc();
        this.genUnit = new GeneratorUnit();
        this.initGenUnits();

    }

    initGenUnits() {
        if (!this.gc.genUnits) {
            this.gc.genUnits = [];
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
        this.genUnitRowIndex = -1;
    }

    addGenUnit() {
        if (!this.gc.genUnits) {
            this.gc.genUnits = [];
        }
        this.gc.genUnits.push(Object.assign({}, this.genUnit));
        this.gcService.setGc(this.gc);
        this.initGenUnits();
    }

    updateGenUnit() {
        let tempArray = [];
        for (let index = 0; index < this.gc.genUnits.length; index++) {
            if (this.genUnitRowIndex == index) {
                tempArray.push(Object.assign({}, this.genUnit));
            } else {
                tempArray.push(this.gc.genUnits[index]);
            }

        }
        this.gc.genUnits = tempArray;
        this.gcService.setGc(this.gc);
        this.initGenUnits();
    }

    editGenUnit(_index: number) {
        this.genUnitRowIndex = _index;
        this.genUnit = Object.assign({}, this.gc.genUnits[_index]);
    }

    deleteGenUnit(_index: number) {
        this.gc.genUnits.splice(_index, 1);
    }

}
