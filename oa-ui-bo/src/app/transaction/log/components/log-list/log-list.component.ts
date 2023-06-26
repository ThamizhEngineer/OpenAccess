import { Component } from '@angular/core';
import { Router, ActivatedRoute, Params } from '@angular/router';

import { LogService } from './../../services/log.service';

@Component({
	selector: 'log-list',
	templateUrl: './log-list.component.html'
})
export class LogListComponent {
    constructor(private route: ActivatedRoute, private router: Router, private service: LogService) {}

}