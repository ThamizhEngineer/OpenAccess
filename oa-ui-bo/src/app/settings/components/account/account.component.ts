import 'rxjs/add/operator/switchMap';
import { Component, OnInit, HostBinding } from '@angular/core';
import { Router, ActivatedRoute, Params } from '@angular/router';

import { MatSnackBar } from '@angular/material';

import { FormBuilder, FormGroup, Validators } from '@angular/forms';
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
    validpassword: string;
    IsOldVerified: boolean = false;
    constructor(private router: Router, private service: AccountService, public snackBar: MatSnackBar) {
        
    }

    ngOnInit() {
      [
        '',
        [
          Validators.required,
          Validators.pattern('(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[$@$!%*?&])[A-Za-z\d$@$!%*?&].{8,}')
         ]
      ]
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
      this.IsOldVerified=false;
		}, e=>{ 
			this.snackBar.open("", "Error in Update password", {
			  duration: 3000,
			});
		});
        
    }
    validoldpassword(){
      let token = this.currentUser.token;
		let obj = { userName: this.currentUser.userName, password: this.validpassword };
		this.service.validatePassword(obj, token).subscribe(y=>{
			console.log(y.result);
      if(y.result == 'FAILURE'){
        this.snackBar.open("", "Password do not match", {
          duration: 3000,
        });
      }
      if(y.result == 'SUCCESS'){
			this.snackBar.open("Password valided Successfully", "", {
			  duration: 3000,
			});
      this.IsOldVerified=true;
    }
      
		}, e=>{ 
			this.snackBar.open("", "Error in password validation", {
			  duration: 3000,
			});
		});
    }
}