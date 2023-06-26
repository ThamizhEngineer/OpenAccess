import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms'; 
import { MatInputModule, MatCardModule, MatButtonModule, MatSnackBarModule } from '@angular/material';
import { FlexLayoutModule } from '@angular/flex-layout';

import { NgxDatatableModule } from '@swimlane/ngx-datatable';

import { SettingsRoutes } from './settings.routing'; 
import { AccountService } from './services/account.service'; 

import { SettingsAccountComponent } from './components/account/account.component';


@NgModule({
  imports: [
    CommonModule,
    RouterModule.forChild(SettingsRoutes),
    MatInputModule, MatCardModule, MatButtonModule, MatSnackBarModule,
    NgxDatatableModule,
    FormsModule,
    FlexLayoutModule
  ],
  declarations: [ 
    SettingsAccountComponent
    ],
  providers: [ AccountService ]
})



export class SettingsModule {
  
}
