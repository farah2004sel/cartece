import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './features/home/home.component';
const routes: Routes = [
  { path: 'home', component: HomeComponent },
  { path: 'auth', loadChildren: () => import('./features/auth/auth.module').then(m => m.AuthModule) },
  { path: 'admin', loadChildren: () => import('./features/admin/admin.module').then(m => m.AdminModule) },
  { path: 'merchant', loadChildren: () => import('./features/merchant/merchant.module').then(m => m.MerchantModule) },
  { path: '', redirectTo: '/home', pathMatch: 'full' },
  { path: '**', redirectTo: '/home' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes, {
    anchorScrolling: "enabled"
  })],
  exports: [RouterModule]
})
export class AppRoutingModule { }
