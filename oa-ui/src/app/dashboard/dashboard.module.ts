import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
import { MatCardModule, MatIconModule,MatDialogModule } from '@angular/material';
import { FlexLayoutModule } from '@angular/flex-layout';
import {
  MatSidenavModule,
  MatMenuModule,
  MatButtonModule,
  MatToolbarModule,
  MatTabsModule,
  MatListModule,
  MatSlideToggleModule,
  MatProgressBarModule } from '@angular/material';
import { DashboardComponent,masterConfirmComponent} from './dashboard.component';
import { DashboardRoutes } from './dashboard.routing';
import { DashboardService } from './dashboard.service';

import { ChartsModule } from 'ng2-charts';

@NgModule({
  imports: [
    CommonModule, ChartsModule,
    RouterModule.forChild(DashboardRoutes),
    MatCardModule, MatIconModule,MatDialogModule,
    FlexLayoutModule,
    MatSidenavModule,
    MatCardModule,
    MatMenuModule,
    MatIconModule,
    MatButtonModule,
    MatToolbarModule,
    MatListModule,
    MatSlideToggleModule,
    MatProgressBarModule,
    FlexLayoutModule,
	 MatDialogModule
  ],
  declarations: [ DashboardComponent,masterConfirmComponent],
  entryComponents: [masterConfirmComponent],
  providers: [ DashboardService ]
})

export class DashboardModule {}
