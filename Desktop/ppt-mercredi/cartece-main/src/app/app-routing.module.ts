import { NgModule } from '@angular/core';
import { RouterModule, Routes, ExtraOptions } from '@angular/router';
import { HomeComponent } from './widgets/home/home.component';
 import { LayoutComponent } from './widgets/layout/layout.component';

const routes: Routes = [
  {
    path: 'accueil', component: HomeComponent, children: [
      { path: '', component: LayoutComponent },
      {
        path: 'commercant',
        loadChildren: () =>
          import('./features/merchant/merchant.module').then(m => m.MerchantModule)
      },
     
      {
      path: 'profile',
      loadChildren: () =>
        import('./features/profile/profile.module').then(m => m.ProfileModule)
    },
    
    ]
  },
 {
        path: 'admin',
        loadChildren: () =>
          import('./features/admin/admin.module').then(m => m.AdminModule)
      },
  {
    path: 'auth',
    loadChildren: () =>
      import('./features/auth/auth.module').then(m => m.AuthModule)
  },


  { path: '', redirectTo: '/accueil', pathMatch: 'full' },
  { path: '**', redirectTo: '/accueil' },

];
const routerOptions: ExtraOptions = {
  scrollPositionRestoration: 'enabled',
  anchorScrolling: 'enabled',
 };
@NgModule({
  imports: [
    RouterModule.forRoot(routes,routerOptions)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
