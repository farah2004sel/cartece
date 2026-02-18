import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
 import { FormsModule, ReactiveFormsModule } from '@angular/forms';
 import { RouterModule, Routes } from '@angular/router'; 

 
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { VerifyEmailComponent } from './verify-email/verify-email.component';
import { ChangePasswordComponent } from './change-password/change-password.component';
import { HomeComponent } from '../../widgets/home/home.component';

const routes: Routes = [
   { path: 'login', component: LoginComponent },
   { path: 'register', component: RegisterComponent },
   { path: 'verify-email', component: VerifyEmailComponent },
    { path: 'change-password', component: ChangePasswordComponent },
]
@NgModule({
  declarations: [
    LoginComponent,
    RegisterComponent,
    VerifyEmailComponent,
    ChangePasswordComponent,
  ],
  imports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    RouterModule.forChild(routes)
   ]
})
export class AuthModule { }
