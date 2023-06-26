import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
import { MatCardModule, MatIconModule } from '@angular/material';
import { FlexLayoutModule } from '@angular/flex-layout';

import { DashboardComponent } from './dashboard.component';
import { DashboardRoutes } from './dashboard.routing';
import { DashboardService } from './dashboard.service';

import { ChartsModule } from 'ng2-charts';

@NgModule({
  imports: [
    CommonModule, ChartsModule,
    RouterModule.forChild(DashboardRoutes),
    MatCardModule, MatIconModule,
    FlexLayoutModule
  ],
  declarations: [ DashboardComponent ],
  providers: [ DashboardService ]
})

export class DashboardModule {}
