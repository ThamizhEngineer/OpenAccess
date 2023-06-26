import { Component, OnInit } from '@angular/core';
import { ReportService } from '../../../services/report.service';
import { CommonService } from '../../../../shared/common/common.service';
import { ExcelExportService } from '../../../../shared/services/excelExport';
import { ActivatedRoute, Params, Router } from '@angular/router';
import { Menu } from '../../../../shared/menu-items/menu-items';
import { Subscription } from 'rxjs/Subscription';



@Component({
  selector: 'report-page',
  templateUrl: './report-page.component.html',
})
export class ReportPageComponent implements OnInit {
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
    constructor(private service:ReportService,private route: ActivatedRoute, private commonService:CommonService,private excelService: ExcelExportService) {
    }

    ngOnInit() {
this.getMenus();
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
              this.menuItems = items.filter(c => c.state=='reports');
              console.log(this.menuItems);
              
          },
          error => {
              console.error('Error loading menu items!');
              console.error(error);
          }
      );
  }

}

