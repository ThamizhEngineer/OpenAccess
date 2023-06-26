import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms'; 
import { MatAutocompleteModule, MatButtonModule, MatCardModule, MatDatepickerModule, MatExpansionModule, MatIconModule, MatInputModule, MatSelectModule, MatListModule } from '@angular/material';
import { FlexLayoutModule } from '@angular/flex-layout';
import { CommonUtils} from '../../shared/common/common-utils';

import { NgxDatatableModule } from '@swimlane/ngx-datatable';

import { PowerplantViewComponent } from './components/powerplant-view/powerplant-view.component';

import { routes } from './powerplant.routing';
//import { GeneratoranisationEvent } from './services/generator.event'; 
import { PowerplantService } from './services/powerplant.service'; 

@NgModule({
  imports: [
    CommonModule,
    RouterModule.forChild(routes),
    MatAutocompleteModule, MatButtonModule, MatCardModule, MatDatepickerModule, MatExpansionModule, MatIconModule, MatInputModule, MatSelectModule, MatListModule,
    NgxDatatableModule,
    FormsModule,
    FlexLayoutModule
  ],
  declarations: [ 
	PowerplantViewComponent
    ],
  providers: [   PowerplantService,CommonUtils ]
})

export class PowerplantModule {
  public static routes = routes;
}
