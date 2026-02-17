import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
 import { AppRoutingModule } from './app-routing.module';
 import { AuthModule } from './features/auth/auth.module';

import { AppComponent } from './app.component';
import { NavbarComponent } from './core/navbar/navbar.component';
import { FooterComponent } from './core/footer/footer.component';
import { HomeComponent } from './features/home/home.component';
import { HttpClientModule } from '@angular/common/http';
 import { FormsModule, ReactiveFormsModule } from '@angular/forms';

@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    FooterComponent,
    HomeComponent,

  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    ReactiveFormsModule,
    FormsModule,
    AuthModule

   ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
