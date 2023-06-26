import { Component, OnInit, OnDestroy, ViewChild, HostListener, Inject } from '@angular/core';
import { Router, NavigationEnd } from '@angular/router';
import { Menu } from '../../shared/menu-items/menu-items';
import { Subscription } from 'rxjs/Subscription';
import { CommonUtils } from '../../shared/common/common-utils'
import { CommonService } from './../../shared/common/common.service';
import 'rxjs/Rx';

import {MatDialog, MatDialogRef, MAT_DIALOG_DATA} from '@angular/material';
import { AuthenticationService } from '../authentication.service';

import * as Ps from 'perfect-scrollbar';

@Component({
  selector: 'app-layout',
  templateUrl: './admin-layout.component.html'
})
export class AdminLayoutComponent implements OnInit, OnDestroy {
    private _router: Subscription;
    menuItems: Menu[] = [];
    today: number = Date.now();
    url: string;
    showSettings = false;
    dark: boolean;
    boxed: boolean;
    collapseSidebar: boolean;
    compactSidebar: boolean;
    currentLang = 'en';
    root = 'ltr';
    userDetail: string;
    userTypeName: string;
    currentDate: any;
    profileToggle: boolean = false;


    @ViewChild('sidemenu') sidemenu;

    constructor(private router: Router, public commonService: CommonService, public dialog: MatDialog, public authService: AuthenticationService) {

        this.userDetail = CommonUtils.getLoginUserDetails();
        this.userTypeName=  CommonUtils.getLoginUserTypeName();
        this.currentDate = new Date().toLocaleString();
    }

    ngOnInit(): void {
        const elemSidebar = < HTMLElement > document.querySelector('.sidebar-panel .mat-sidenav-focus-trap .cdk-focus-trap-content');
        const elemContent = < HTMLElement > document.querySelector('.mat-sidenav-content');

        this.getMenus();

        this._router = this.router.events.filter(event => event instanceof NavigationEnd).subscribe(event => {
            this.url = event['url'];
        });

        let authUser = JSON.parse(sessionStorage.getItem('user'));
        this.sessionActive(authUser);
    }

    @HostListener('click', ['$event'])
    onClick(e: any) {
        const elemSidebar = < HTMLElement > document.querySelector('.sidebar-panel .mat-sidenav-focus-trap .cdk-focus-trap-content');
        setTimeout(() => {}, 350);
    }

    @HostListener('document:click', ['$event']) clickedSearchOutside($event) {
        this.profileToggle = false;
    }
    clickedSearchInside($event: Event) {
        $event.preventDefault();
        $event.stopPropagation();
    }

    @HostListener('window:beforeunload', [ '$event' ])

    beforeUnloadHandler(event) {
     console.log("got it");
     this.logout();
    }

    ngOnDestroy() {
        this._router.unsubscribe();
    }

    isOver(): boolean {
        if (this.url === '/apps/messages' || this.url === '/apps/calendar' || this.url === '/apps/media' || this.url === '/maps/leaflet') {
            return true;
        } else {
            return window.matchMedia(`(max-width: 960px)`).matches;
        }
    }

    isMac(): boolean {
        let bool = false;
        if (navigator.platform.toUpperCase().indexOf('MAC') >= 0 || navigator.platform.toUpperCase().indexOf('IPAD') >= 0) {
            bool = true;
        }
        return bool;
    }

    menuMouseOver(): void {
        if (window.matchMedia(`(min-width: 960px)`).matches && this.collapseSidebar) {
            this.sidemenu.mode = 'over';
        }
    }

    menuMouseOut(): void {
        if (window.matchMedia(`(min-width: 960px)`).matches && this.collapseSidebar) {
            this.sidemenu.mode = 'side';
        }
    }

    logout(): void {
        this.authService.logout().subscribe(x=>{
			this.redirect();
		}, error=>{
			this.redirect();
		});
    }

    redirect(){
        clearInterval(this.interval)
		sessionStorage.clear();
		this.router.navigateByUrl('/login');
	}
    
    reportPage(){
        this.router.navigateByUrl('/reports/report-page');
    }

    getMenus() {
        let tempUserAccess: Array < String > = ["SIGNUP", "METER-READING-IMPORT", "METER-READING", "GENERATOR-STATEMENT", "ENERGY-SALE", "ENERGY-LEDGER", "DEFAULT", "GENERATOR-WISE-CONSUMER-REPORT"];
        let tempPPPAccess: Array < String > = ["SELLER-SETUP", "GRID-CONNECTIVITY-APPLICATION", "ENERGY-SALE-INTENT", "NOC", "CONSENT", "EWA", "OAA", "IPAA", "NOC-GENERATOR", "STANDING-CLEARENCE"];
        let authUser = JSON.parse(sessionStorage.getItem('user'));

        this.commonService.fetchMenuItems().subscribe(
            result => {
                let items = [];
                if (authUser.isSuperUser == "Y") {
                    items = result;
                } else
                    result.forEach(menuEntry => {
                        if (menuEntry.functionality == "DEFAULT") {
                            if (authUser.userName.includes("usr")) {
                                if (tempUserAccess.includes(menuEntry.functionality)) {
                                    items.push(menuEntry);
                                }
                            } else {
                                items.push(menuEntry);
                            }

                        } else if (menuEntry.feature == "MENU-LINK") {
                            var children = menuEntry.children;
                            menuEntry.children = [];
                            children.forEach(child => {
                                authUser.accessList.forEach(userAccess => {

                                    if ((child.functionality == "DEFAULT") || ((child.functionality == userAccess.functionality) && (userAccess.feature == child.feature))) {
                                        menuEntry.children.push(child);
                                    }
                                })
                            })

                            if (menuEntry.children.length > 0) {
                                items.push(menuEntry)
                            }
                        } else {
                            authUser.accessList.forEach(userAccessList => {
                                if ((menuEntry.functionality == userAccessList.functionality) && (userAccessList.feature == menuEntry.feature)) {
                                    items.push(menuEntry);
                                }

                            })
                        }
                    })
                this.menuItems = items;
            },
            error => {
                console.error('Error loading menu items!');
                console.error(error);
            }
        );
    }


    sessionWarning: boolean = false;
    interval: any;
    sessionActive(authUser) {
        let time = new Date(authUser.tokenValidityDt).getTime();
        console.log(time)
        console.log(authUser.tokenValidityDt)
        let sessExpire = time + (3600 * 1000);
        let sessPopup = time + (2700 * 1000);
        this.timer(time, sessExpire, sessPopup);
    }

    timer(curTime, sessExpire, sessPopup) {
        let i = 0;
        this.interval = setInterval(() => {
            i += 5000;
            if ((curTime + i) > sessExpire) {
                this.logout();
            } else if ((curTime + i) > sessPopup && !this.sessionWarning) {
                this.sessionWarning = true;
                this.openDialog();
            }
        }, 5000);
    }

    sessOK() {
        this.authService.refresh().subscribe(data => {
            clearInterval(this.interval)
            this.sessionWarning = false;
            this.sessionActive(data)
        });
    }

    openDialog(): void {
        let dialogRef = this.dialog.open(SessionDialogHandlerComponent, {
            width: '300px',
            data: {}
        });

        dialogRef.afterClosed().subscribe(result => {
            if (result) this.sessOK();
            else this.logout();
        });
    }
}

@Component({
  selector: 'session-dialog-handler',
  template: `
	<div mat-dialog-content>
		Session About to expire<br>Click Extend to Continue OR Logout
	</div>
	<div mat-dialog-actions>
		<button type="button" color="primary" mat-raised-button (click)="sessOK()">Extend Session</button>
		<button type="button" color="amber" mat-raised-button (click)="sessClose()">Logout</button>
	</div>
 `,
})
export class SessionDialogHandlerComponent {

	constructor(public dialogRef: MatDialogRef<SessionDialogHandlerComponent>, @Inject(MAT_DIALOG_DATA) public data: any) { }

	sessClose(): void {	
		this.dialogRef.close(false);
	}

	sessOK(): void {	
		this.dialogRef.close(true);
	}
}
