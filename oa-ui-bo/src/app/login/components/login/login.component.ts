import 'rxjs/add/operator/switchMap';
import { Component, OnInit, HostBinding } from '@angular/core';
import { Router, ActivatedRoute, Params } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { User } from './../../../user/services/user';
import {  LoginService } from './../../services/login.service';
import { AuthUser } from '../../../user/services/authuser';
import * as CryptoJS from 'crypto-js'; 
import * as uuid from 'uuid';
import { environment } from '../../../../environments/environment';
@Component({
  selector: 'login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss'],
  providers: [LoginService]
})
export class LoginComponent implements OnInit {
  user: User;
  authUser: AuthUser;
  message: string;
  logging:boolean = false;
  browserObject: String =  navigator.userAgent;
	isChrome:boolean= false;
  constructor(private router: Router, private route: ActivatedRoute, private service: LoginService) {
    this.user = Object.assign({},{
      userName:'', 
      password:''
    });
  }

  ngOnInit() {
    // this.isChrome = (this.browserObject.search('Chrome') >0 ) 
    // // console.log("this.isChrome-"+this.isChrome );
    
    // if(!this.isChrome){
    //     alert("Please use Google Chrome browser!!!");
    // }
    this.route.queryParams.subscribe(params => {
        if(params['reason']) this.message = params['reason'];
      });
  }


  login() {
    const key = CryptoJS.enc.Utf8.parse(environment.cryptoKey);
    // const iv = CryptoJS.enc.Utf8.parse('openaccesstneb12');
    const encryptedPassword=CryptoJS.AES.encrypt(this.user.password, key, {
          keySize: 16,
          // iv: iv,
          mode: CryptoJS.mode.ECB,
          padding: CryptoJS.pad.Pkcs7
        }).toString(); 
   
    this.user.password=encryptedPassword;

    if (!this.user.userName) {
      this.message = "UserName is mandatory";
      return false;
    }
    else {
      if (!this.user.password) {
        this.message = "Password is mandatory";
        return false;
      }
    }

    this.logging = true;
    //-------------------------------------------------------unique id for security audit purpose------------
    const myId = uuid.v4();
   
    this.user.uniqueId = myId;
   // const key = CryptoJS.enc.Utf8.parse(environment.cryptoKey);
    const encryptedUID=CryptoJS.AES.encrypt(this.user.uniqueId, key, {
      keySize: 16,
      // iv: iv,
      mode: CryptoJS.mode.ECB,
      padding: CryptoJS.pad.Pkcs7
    }).toString(); 

    this.user.uniqueId = encryptedUID;
    //-------------------------------------------------
    this.service.login(this.user).subscribe(
      result => {
        //this.user._id = result;
        this.message = "Login Successfull";
        this.authUser = result;
       console.log(this.authUser.isSuperUser);

       this.authUser.isSuperUser=atob(this.authUser.isSuperUser);
       if(this.authUser.id != 'null' && this.authUser.id != null){
       this.authUser.id=atob(this.authUser.id);
       }
        if(this.authUser.firstName != 'null' && this.authUser.firstName != null){
        this.authUser.firstName=atob(this.authUser.firstName);
        }
        if(this.authUser.lastName != 'null' && this.authUser.lastName != null){
        this.authUser.lastName=atob(this.authUser.lastName);
        }
        if(this.authUser.userName != 'null' && this.authUser.userName != null){
        this.authUser.userName=atob(this.authUser.userName);
        }
        if(this.authUser.password != 'null' && this.authUser.password != null){
        this.authUser.password=atob(this.authUser.password);
        }
        
        if(this.authUser.systemKeyCode != 'null' && this.authUser.systemKeyCode != null){
        this.authUser.systemKeyCode=atob(this.authUser.systemKeyCode);
        }
        if(this.authUser.systemRefKey != 'null' && this.authUser.systemRefKey != null){
        this.authUser.systemRefKey=atob(this.authUser.systemRefKey);
        }

        if(this.authUser.edcCode != 'null' && this.authUser.edcCode != null){
        this.authUser.edcCode=atob(this.authUser.edcCode);
        }
        if(this.authUser.companyId != 'null' && this.authUser.companyId != null){
        this.authUser.companyId= atob(this.authUser.companyId);
        }
        if(this.authUser.token != 'null' && this.authUser.token != null){
        this.authUser.token=atob(this.authUser.token);
        }
        if(this.authUser.companyServiceId != 'null' && this.authUser.companyServiceId != null){
        this.authUser.companyServiceId=atob(this.authUser.companyServiceId);
        }
        if(this.authUser.loginStopMessage != 'null' && this.authUser.loginStopMessage != null){
          this.authUser.loginStopMessage=atob(this.authUser.loginStopMessage);
          }
          if(this.authUser.isLoggedIn != 'null' && this.authUser.isLoggedIn != null){
            this.authUser.isLoggedIn=atob(this.authUser.isLoggedIn);
            }
 
      
      
       
       if(this.authUser.isSuperUser.includes("Y")){

        this.authUser.isSuperUser='Y';

       }
       if(this.authUser.isSuperUser.includes("N")){

        this.authUser.isSuperUser='N';

       }

       this.authUser.accessList.forEach(function(value){

        if(value.feature != 'null' && value.feature != null){
          value.feature=atob(value.feature);
          }
        if(value.functionality != 'null' && value.functionality != null){
            value.functionality=atob(value.functionality);
            }
        if(value.orgType != 'null' && value.orgType != null){
              value.orgType=atob(value.orgType);
              }
        if(value.systemKey != 'null' && value.systemKey != null){
                value.systemKey=atob(value.systemKey);
                }


       });
       
      
        if(this.authUser.loginStopMessage!=null && this.authUser.loginStopMessage!=''){

          this.logging = false;
          this.message = this.authUser.loginStopMessage;
        }
        if (this.authUser.isLoggedIn=='Y' || this.authUser.isLoggedIn == null ){
        
          this.logging = false;
          this.message = 'Login already in use';

        }
       
        if (this.authUser.uniqueId != myId ){

             this.logging = false;
             this.message = 'Un Authorized User';


        }
        // if (this.authUser.isLoggedIn=='N' || this.authUser.isLoggedIn == null ){
          
        //   // this.service.SetLogged(this.user).subscribe(
        //   //   result => {
        //   //  console.log(result);
           
        //   //   });

        // }
        else{
          this.message = "Login Successfull";
          this.service.setToken(this.authUser);
          this.openDash();
          if (this.authUser.isLoggedIn=='N' || this.authUser.isLoggedIn == null ){
          
          this.service.setIsloggedin(this.authUser).subscribe(
            result => {
           console.log(result);
           
            });

        }
        }
      },

        

      error => {
        this.logging = false;
        if (JSON.parse(error._body).message) {
          this.message = JSON.parse(error._body).message;
        } else {
          this.message = "System Issue: Please contact system administrator!"

        }

      }
    );
  }
  

  openDash() {
    //this.router.navigate ( [ '/dashboard' ] );
     this.router.navigateByUrl('/home');
  }
}
