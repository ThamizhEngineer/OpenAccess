import {Component, Input} from '@angular/core'
import { Cs} from './../../../vo/cs'; 
@Component({
  selector: 'summary',
  templateUrl: './summary.html'
})

export class Summary {
  @Input() cs: Cs;
  @Input() stepDisable: boolean;
  
  constructor() { 
  }
  

}