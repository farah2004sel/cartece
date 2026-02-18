import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './widgets/home/home.component';
import { ChangePasswordComponent } from './features/auth/change-password/change-password.component';
import { LoginComponent } from './features/auth/login/login.component';
import { RegisterComponent } from './features/auth/register/register.component';
import { VerifyEmailComponent } from './features/auth/verify-email/verify-email.component';
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
        path: 'admin',
        loadChildren: () =>
          import('./features/admin/admin.module').then(m => m.AdminModule)
      },
    
    ]
  },

  {
    path: 'auth',
    loadChildren: () =>
      import('./features/auth/auth.module').then(m => m.AuthModule)
  },


  { path: '', redirectTo: '/accueil', pathMatch: 'full' },
  { path: '**', redirectTo: '/accueil' },

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
