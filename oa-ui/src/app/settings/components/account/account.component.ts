import 'rxjs/add/operator/switchMap';
import { Component, OnInit, HostBinding } from '@angular/core';
import { Router, ActivatedRoute, Params } from '@angular/router';

import { MatSnackBar } from '@angular/material';

import { NG_VALIDATORS,Validator,Validators,AbstractControl,ValidatorFn, NgForm } from '@angular/forms';
import { AccountService } from './../../services/account.service';


@Component({
  selector: 'settings-account',
  templateUrl: './account.component.html',
  providers: [ AccountService ]
})
export class SettingsAccountComponent implements OnInit {

    currentUser: any;
    pword: string;
    newPword: string = '';
    cnewPword: string;

    constructor(private router: Router, private service: AccountService, public snackBar: MatSnackBar) {
        
    }

    ngOnInit() {
		this.currentUser = this.service.getUser();
    }

    updatePassword() {
		let token = this.currentUser.token;
		let obj = { userName: this.currentUser.userName, password: this.newPword };
		this.service.updatePassword(obj, token).subscribe(y=>{
			this.newPword = this.cnewPword = '';
			this.snackBar.open("Password updated Successfully", "", {
			  duration: 3000,
			});
		}, e=>{ 
			this.snackBar.open("", "Error in Update password", {
			  duration: 3000,
			});
		});
        
    }
}