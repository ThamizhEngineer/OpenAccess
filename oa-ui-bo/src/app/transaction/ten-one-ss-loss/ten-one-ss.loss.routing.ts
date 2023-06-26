import { Routes } from '@angular/router';
import { TenOneSSLossList } from '../ten-one-ss-loss/components/ten-one-ss-loss-list.component';
import { TenOneSSLossDetail } from './components/ten-one-ss-detail/ten-one-ss-loss-detail.component';
import { TenOneSSLossView } from './components/ten-one-ss-view/ten-one-ss-loss-view.component';
import { TenOneSSLossSub } from './components/ten-one-ss-sub/ten-one-ss-loss-sub.component';

export const routes: Routes = [
    { path: '', 
    children: [
      { path: '', redirectTo: 'ten-one-ss-loss-list' },
      { path: 'ten-one-ss-loss-list', component: TenOneSSLossList,  pathMatch: 'full' },
      { path: 'ten-one-ss-loss-view', component: TenOneSSLossView,  pathMatch: 'full' },
      { path: 'ten-one-ss-loss-sub', component: TenOneSSLossSub,  pathMatch: 'full' },
      { path: 'ten-one-ss-loss-detail/:_id',component:TenOneSSLossDetail,  pathMatch: 'full'},
      { path: '**',redirectTo: 'ten-one-ss-loss-list' }
    ]
  }
  ];