import { Routes } from '@angular/router';
import { NocApprovalDirectorList } from './components/noc-approval-director/noc-approval-director-list.component';
import { NocApprovalDirectorDetail } from './components/noc-approval-director/noc-approval-director-detail.component';
import { NocApprovalCeList } from './components/noc-approval-ce/noc-approval-ce-list.component';
import { NocApprovalCeDetail } from './components/noc-approval-ce/noc-approval-ce-detail.component';
import { SldcNocComponent } from './components/sldc-noc/sldc-noc.component';
import { SldcNocFormComponent } from './components/sldc-noc-form/sldc-noc-form.component';
import { SamastImportComponent } from './components/samast-import/samast-import.component'
import { SamastApplnComponent } from './components/samast-appln-report/samast-appln.component';
import { NocApprovalList } from './components/noc-approval-list/noc-approval-list.component';



export const sldcroutes:Routes=[
    {
        path: '',
        children: [
            { path: 'samast-import', component: SamastImportComponent, pathMatch: 'full'},
            { path: 'sldc-noc', component: SldcNocComponent, pathMatch: 'full'},
            { path: 'sldc-noc-form/:id', component: SldcNocFormComponent, pathMatch: 'full'},                        
            { path: 'noc-approval-director', component: NocApprovalDirectorList, pathMatch: 'full'},
            { path: 'noc-approval-director-detail/cons/:id', component: NocApprovalDirectorDetail, pathMatch: 'full'},
            { path: 'noc-approval-ce', component: NocApprovalCeList, pathMatch: 'full'},
            { path: 'noc-approval-ce-detail/cons/:id', component: NocApprovalCeDetail, pathMatch: 'full'},
            { path: 'samast-appln', component: SamastApplnComponent, pathMatch: 'full'},
            { path: 'noc-approvallist', component: NocApprovalList, pathMatch: 'full'},
         
        ]
    }
];