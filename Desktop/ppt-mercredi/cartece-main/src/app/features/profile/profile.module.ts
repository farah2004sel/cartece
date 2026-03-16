import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { EditProfileComponent } from './edit-profile/edit-profile.component';
import { RouterModule, Routes } from '@angular/router';

 import { MatFormFieldModule } from '@angular/material/form-field';
import { MatSelectModule } from '@angular/material/select'; 
import { MatOptionModule } from '@angular/material/core';
import { MatIconModule } from '@angular/material/icon';
 
const routes: Routes = [
  { path: '', component: EditProfileComponent }
];

@NgModule({
  declarations: [
    EditProfileComponent,
  ],
  imports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    RouterModule.forChild(routes),
    
     MatFormFieldModule,
    MatSelectModule,      
    MatOptionModule,
    MatIconModule,
     
  ]
})
export class ProfileModule { }