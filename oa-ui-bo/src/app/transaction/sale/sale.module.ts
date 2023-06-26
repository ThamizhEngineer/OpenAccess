import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { MatButtonToggleModule, MatCheckboxModule, MatStepperModule, MatExpansionModule, MatGridListModule, MatIconModule, MatMenuModule, MatPaginatorModule, MatProgressBarModule, MatProgressSpinnerModule, MatRadioModule, MatRippleModule, MatSidenavModule, MatSliderModule, MatSlideToggleModule, MatSnackBarModule, MatSortModule, MatTableModule, MatTabsModule, MatTooltipModule} from '@angular/material';
import { MatButtonModule, MatCardModule, MatDatepickerModule, MatNativeDateModule, MatInputModule, MatListModule, MatSelectModule, MatToolbarModule, MatAutocompleteModule, MatChipsModule, MatDialogModule } from '@angular/material';
import { NgxDatatableModule } from '@swimlane/ngx-datatable';
import { FlexLayoutModule } from '@angular/flex-layout';

import { SaleCreateComponent } from './components/sale-create/sale-create.component';
import { SaleDetailComponent } from './components/sale-detail/sale-detail.component';
import { SaleListComponent, DialogDetailViewDialog } from './components/sale-list/sale-list.component';
import { routes } from './sale.routing';

import { SaleService } from './services/sale.service';
import { CustomDirective } from '../../shared/services/custom.directive';

@NgModule({
  imports: [
    CommonModule,
    RouterModule.forChild(routes),
    FormsModule,
    FlexLayoutModule,
  NgxDatatableModule, CustomDirective,
  MatAutocompleteModule, MatButtonModule, MatCardModule, MatChipsModule, MatDatepickerModule, MatDialogModule, MatInputModule, MatListModule, MatNativeDateModule, MatSelectModule, MatToolbarModule,

    MatButtonModule, MatCardModule, MatDatepickerModule, MatNativeDateModule, MatInputModule, MatListModule, MatSelectModule, MatToolbarModule, MatAutocompleteModule, MatChipsModule, MatDialogModule
  ],
  declarations: [ SaleCreateComponent, SaleDetailComponent, SaleListComponent, DialogDetailViewDialog ],
  entryComponents: [ DialogDetailViewDialog ],
  providers: [ SaleService ],
})

export class SaleModule {
  public static routes = routes;
}
