import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { RouterModule } from '@angular/router';
import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpModule, Http } from '@angular/http';
import {BidiModule} from '@angular/cdk/bidi';

import { MatDialogModule } from '@angular/material';

import {
  MatSidenavModule,
  MatCardModule,
  MatMenuModule,
  MatTableModule,
  MatIconModule,
  MatButtonModule,
  MatToolbarModule,
  MatTabsModule,
  MatListModule,
  MatSlideToggleModule,
  MatProgressBarModule } from '@angular/material';
import { FlexLayoutModule } from '@angular/flex-layout';

import { AppRoutes } from './app.routing';
import { AppComponent } from './app.component';
import { AdminLayoutComponent, SessionDialogHandlerComponent } from './layouts/admin/admin-layout.component';
import { VerifyContactInfoDialogComponent } from './layouts/verify-contact-info-dialog/verify-contact-info-dialog.component';
import { AuthLayoutComponent } from './layouts/auth/auth-layout.component';
import { SharedModule } from './shared/shared.module';
import { RouteGuardComponent } from './shared/route-guard/route-guard.component';
import { CompanyService } from './master/company/services/company.service';
import { CommonUtils } from './shared/common/common-utils';
import { AuthenticationService } from './layouts/authentication.service';

@NgModule({
  declarations: [
    AppComponent,
    AdminLayoutComponent, SessionDialogHandlerComponent,VerifyContactInfoDialogComponent,
    AuthLayoutComponent
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    SharedModule,
    RouterModule.forRoot(AppRoutes),
    FormsModule,
    HttpModule,
    MatSidenavModule,
    MatCardModule,
    MatMenuModule,
    MatIconModule,
    MatButtonModule,
    MatTableModule,
    MatToolbarModule,
    MatListModule,
    MatSlideToggleModule,
    MatProgressBarModule,
    FlexLayoutModule,
     BidiModule,
	 MatDialogModule
  ],
  providers: [RouteGuardComponent, CompanyService, CommonUtils, AuthenticationService],
  entryComponents: [ SessionDialogHandlerComponent, VerifyContactInfoDialogComponent ],
  bootstrap: [AppComponent],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class AppModule { }
