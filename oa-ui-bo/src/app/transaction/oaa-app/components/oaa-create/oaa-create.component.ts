import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute, Params } from '@angular/router';

const types: any = {
	'STOA': 'Short Term',
	'MTOA': 'Mid Term',
	'LTOA': 'Long Term',
};

@Component({
  selector: 'oaa-create',
  templateUrl: './oaa-create.component.html',
  styleUrls: ['pricing.scss' ]
})

export class OaaCreateComponent implements OnInit {

	constructor(private route: ActivatedRoute, private router: Router) {}

	ngOnInit() {
	}

}
