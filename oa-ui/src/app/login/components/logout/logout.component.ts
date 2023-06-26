import { Component, OnInit } from '@angular/core';
import { Router, NavigationEnd } from '@angular/router';
import { CommonService } from '../../../shared/common/common.service';

@Component({
  selector: 'app-logout',
  templateUrl: './logout.component.html'
})
export class LogoutComponent implements OnInit {

	constructor(private router: Router, private commonService : CommonService) { }

	ngOnInit() {
		sessionStorage.clear();
		this.router.navigateByUrl('/login');
	}

}
