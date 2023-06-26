import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { MatInputModule, MatCardModule, MatButtonModule } from '@angular/material';
import { FlexLayoutModule } from '@angular/flex-layout';

import { NgxDatatableModule } from '@swimlane/ngx-datatable';

import { routes } from './login.routing';
import { LoginService } from './services/login.service';
import { CommonService } from '../shared/common/common.service';

import { LoginComponent } from './components/login/login.component';
import { LogoutComponent } from './components/logout/logout.component';


@NgModule({
  imports: [
    CommonModule,
    RouterModule.forChild(routes),
    MatInputModule, MatCardModule, MatButtonModule,
    NgxDatatableModule,
    FormsModule,
    FlexLayoutModule
  ],
  declarations: [
    LoginComponent,
    LogoutComponent,

  ],
  providers: [CommonService, LoginService//, LocalStorageService
  ]
})



export class LoginModule {
  public static routes = routes;
}
