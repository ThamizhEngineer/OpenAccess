import { Component, OnInit, HostBinding, Input } from '@angular/core';
import { Gc, Transformer } from './../../services/gc';
import { GcService } from './../../services/gc.service';

@Component({
  selector: 'gc-transformers',
  templateUrl: './gc-transformers.component.html',

})
export class GcTransformersComponent implements OnInit {
    gc: Gc;
    callFlag: boolean = false;
    transformer: Transformer;
    transformerRowIndex: number;

    @Input() voltages = [];

    isDisabled = false;
    isMandatory = false;

    constructor(private gcService: GcService) {
        this.gcService.gcEvent.subscribe(_gc => {
            this.gc = _gc;
        });
    }

    onPrimaryVoltageSelect(voltage) {
        this.transformer.primaryVoltageCode = voltage.valueCode;
        this.transformer.primaryVoltageName = voltage.valueDesc;
    }
    onSecondaryVoltageSelect(voltage) {
        this.transformer.secondaryVoltageCode = voltage.valueCode;
        this.transformer.secondaryVoltageName = voltage.valueDesc;
    }

    ngOnInit() {
        this.gc = new Gc();
        this.transformer = new Transformer();
        this.initTransformers();
    }
	
    initTransformers() {
        if (!this.gc.transformers) {
            this.gc.transformers = [];
        }
        this.transformer = Object.assign({}, {
            mvaRating: '',
            primaryVoltageName: '',
            secondaryVoltageName: '',
            coolingType: '',
            windingConfiguration: '',
            breakerRating: '',
            tapSetting: '',
            tapStepOffLoad: '',
            tapStepOnLoad: '',
            tapRatio: '',
            tapNumberMax: '',
            tapNumberMin: '',
            tapVoltageMax: '',
            tapVoltageMin: '',
            phaseDisplacement: '',
            impedencePercentage: '',
            leakReactance: '',
            resistance: '',
            reactance: ''
        });
        this.transformerRowIndex = -1;
    }

    addTransformer() {
        if (!this.gc.transformers) {
            this.gc.transformers = [];
        }
        this.gc.transformers.push(Object.assign({}, this.transformer));
        this.gcService.setGc(this.gc);
        this.initTransformers();
    }

    updateTransformer() {
        let tempArray = [];
        for (let index = 0; index < this.gc.transformers.length; index++) {
            if (this.transformerRowIndex == index) {
                tempArray.push(Object.assign({}, this.transformer));
            } else {
                tempArray.push(this.gc.transformers[index]);
            }

        }
        this.gc.transformers = tempArray;
        this.gcService.setGc(this.gc);
        this.initTransformers();
    }


    editTransformer(_index: number) {
        this.transformerRowIndex = _index;
        this.transformer = Object.assign({}, this.gc.transformers[_index]);
    }

    deleteTransformer(_index: number) {
        this.gc.transformers.splice(_index, 1);
    }
}