import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
 import { FormsModule, ReactiveFormsModule } from '@angular/forms';
 import { RouterModule } from '@angular/router'; 

 
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { VerifyEmailComponent } from './verify-email/verify-email.component';
import { ChangePasswordComponent } from './change-password/change-password.component';
import { ProfileComponent } from './profile/profile.component';

@NgModule({
  declarations: [
    LoginComponent,
    RegisterComponent,
    VerifyEmailComponent,
    ChangePasswordComponent,
    ProfileComponent
  ],
  imports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    RouterModule
   ]
})
export class AuthModule { }
