import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { MatButtonModule, MatCardModule, MatDatepickerModule, MatNativeDateModule, MatInputModule, MatListModule, MatSelectModule, MatToolbarModule, MatAutocompleteModule, MatChipsModule, MatDialogModule } from '@angular/material';
import { NgxDatatableModule } from '@swimlane/ngx-datatable';
import { FlexLayoutModule } from '@angular/flex-layout';

import { OaaCreateComponent } from './components/oaa-create/oaa-create.component'
import { routes } from './oaa-app.routing';


@NgModule({
  imports: [
    CommonModule,
    RouterModule.forChild(routes),
    FormsModule,
    FlexLayoutModule,
	NgxDatatableModule,
    MatButtonModule, MatCardModule, MatDatepickerModule, MatNativeDateModule, MatInputModule, MatListModule, MatSelectModule, MatToolbarModule, MatAutocompleteModule, MatChipsModule, MatDialogModule
  ],
  declarations: [  OaaCreateComponent],
  entryComponents: [ OaaCreateComponent ],
  providers: [  ],
})

export class OaaAppModule {
  public static routes = routes;
}
