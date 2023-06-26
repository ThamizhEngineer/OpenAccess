import { NgModule } from '@angular/core';

//import { MenuItems } from './menu-items/menu-items';
import { CommonService } from './common/common.service';
import { AccordionAnchorDirective, AccordionLinkDirective, AccordionDirective } from './accordion';
import { ToggleFullscreenDirective } from './fullscreen/toggle-fullscreen.directive';
import { RouteGuardComponent } from './route-guard/route-guard.component';

@NgModule({
  declarations: [
    AccordionAnchorDirective,
    AccordionLinkDirective,
    AccordionDirective,
    ToggleFullscreenDirective,
    RouteGuardComponent
  ],
  exports: [
    AccordionAnchorDirective,
    AccordionLinkDirective,
    AccordionDirective,
    ToggleFullscreenDirective
   ],
  providers: [ CommonService ]
})
export class SharedModule { }
