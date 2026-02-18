import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { DashboardComponent } from './dashboard/dashboard.component';
import { NewRequestComponent } from './new-request/new-request.component';
import { FormsModule } from '@angular/forms';
import { RouterModule, Routes } from '@angular/router';

const routes: Routes = [
  { path: '', component: DashboardComponent },
  { path: 'new-request', component: NewRequestComponent },
  { path: '', redirectTo: 'dashboard', pathMatch: 'full' }
];
@NgModule({
  declarations: [
    DashboardComponent,
    NewRequestComponent
  ],
  imports: [
    CommonModule,
    FormsModule,
    RouterModule.forChild(routes)
  ]  

})
export class MerchantModule { }
