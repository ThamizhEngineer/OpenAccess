import { Injectable, Component } from '@angular/core';
import { CanActivate, Router } from '@angular/router';

@Component({
  selector: 'app-route-guard',
  templateUrl: './route-guard.component.html',
  styleUrls: ['./route-guard.component.scss']
})

@Injectable()
export class RouteGuardComponent implements  CanActivate {

  constructor(private _router: Router) { }
  
  canActivate() {
    let user = JSON.parse(sessionStorage.getItem('user'));
	let ud = new Date(user.tokenValidityDt);
	let d = new Date();
	if(ud.getTime() >= d.getTime()){
		return true;
	}
	else {
		this._router.navigate(['/login'], { queryParams: { 'reason': "Login Token Expired" } });
		return false;
	}
  }

}
