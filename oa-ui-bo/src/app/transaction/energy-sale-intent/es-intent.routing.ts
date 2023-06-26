import { Routes } from '@angular/router';
import { EsIntentListComponent } from './components/es-intent-list/es-intent-list.component';
import { EsIntentDetailComponent } from './components/es-intent-detail/es-intent-detail.component';
import {EsIntentIexConsumerComponent } from './components/es-intent-iex-consumer/es-intent-iex-consumer.component';
import {EsIntentStbComponent } from './components/es-intent-stb/es-intent-stb.component';
import {EsIntentIexGeneratorComponent } from './components/es-intent-iex-generator/es-intent-iex-generator.component';
import {EsIntentOaaListComponent } from './components/es-intent-oaa-list/es-intent-oaa-list.component';
import {EsIntentOaaDetailComponent } from './components/es-intent-oaa-detail/es-intent-oaa-detail.component';
export const routes: Routes = [
{ path: '', 
  children: [
    { path: '', redirectTo: 'es-intent-list' },
    { path: 'es-intent-list', component: EsIntentListComponent,  pathMatch: 'full' },
    { path: 'es-intent-edit',component: EsIntentDetailComponent,  pathMatch: 'full'},
    { path: 'es-intent-new',component: EsIntentDetailComponent,  pathMatch: 'full'},
    { path: 'es-intent-iex-consumer',component: EsIntentIexConsumerComponent,  pathMatch: 'full'},
    { path: 'es-intent-iex-generator',component: EsIntentIexGeneratorComponent,  pathMatch: 'full'},
    { path: 'es-intent-stb',component: EsIntentStbComponent,  pathMatch: 'full'},
    { path: 'es-intent-oaa-list/:_id',component: EsIntentOaaListComponent,  pathMatch: 'full'},
    { path: 'es-intent-oaa-detail/:_id',component: EsIntentOaaDetailComponent,  pathMatch: 'full'},
  
    
  ]
}
];