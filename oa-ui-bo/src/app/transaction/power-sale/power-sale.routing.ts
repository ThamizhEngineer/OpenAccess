import { Routes } from '@angular/router';
import {PsFossilFuelCaptiveComponent } from './components/power-sale-fossil-fuel-captive/ps-fossil-fuel-captive.component';
import {PsIexGeneratorComponent } from './components/power-sale-iex-gen/ps-iex-generator.component';
import {PsListComponent } from './components/power-sale-list/ps-list.component';
import {PsNcesCaptiveComponent } from './components/power-sale-nces-captive/ps-nces-captive.component';

export const routes: Routes = [
{ path: '', 
  children: [
    { path: '', redirectTo: 'ps-list' },
    { path: 'ps-list', component: PsListComponent,  pathMatch: 'full' },
    { path: 'fossil-fuel-captive-new',component: PsFossilFuelCaptiveComponent,  pathMatch: 'full'},
    { path: 'fossil-fuel-captive-edit',component: PsFossilFuelCaptiveComponent,  pathMatch: 'full'},
    { path: 'iex-generator',component: PsIexGeneratorComponent ,  pathMatch: 'full'},
    { path: 'nces-captive-new',component: PsNcesCaptiveComponent ,  pathMatch: 'full'},
    { path: 'nces-captive-edit',component: PsNcesCaptiveComponent ,  pathMatch: 'full'}


    
  ]
}
];