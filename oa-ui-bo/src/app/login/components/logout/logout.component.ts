import { Component, OnInit } from '@angular/core';
import { Router, NavigationEnd } from '@angular/router';
import { CommonService } from '../../../shared/common/common.service';
import { LoginService } from '../../services/login.service';
@Component({
  selector: 'app-logout',
  templateUrl: './logout.component.html'
})
export class LogoutComponent implements OnInit {

	constructor(private router: Router, private commonService : CommonService, private loginService: LoginService) { }

	ngOnInit() {
		console.log("logout ngOnInit")
		this.loginService.logout().subscribe(x=>{
			this.redirect();
		}, error=>{
			this.redirect();
		});
	}

	redirect(){
		sessionStorage.clear();
		// this.router.navigateByUrl('/login');
		this.router.navigateByUrl('/in/login');

	}

}
