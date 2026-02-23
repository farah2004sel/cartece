import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { provideHttpClient, withInterceptors } from '@angular/common/http';
import { RouterModule } from '@angular/router';
import { AppRoutingModule } from './app-routing.module';

import { AppComponent } from './app.component';
import { BarreNavigationComponent } from './widgets/navbar/barre-navigation.component';
import { PiedDePageComponent } from './widgets/footer/pied-de-page.component';
import { BarreLateraleComponent } from './widgets/sidebar/barre-laterale.component';
import { AccueilComponent } from './widgets/home/accueil.component';
import { LayoutPrincipalComponent } from './widgets/layout/principal-layout.component';
import { LayoutAuthentificationComponent } from './widgets/layout/authentification-layout.component';
import { authentificationInterceptor } from './core/interceptors/authentification.interceptor';

@NgModule({
  declarations: [
    AppComponent,
    BarreNavigationComponent,
    PiedDePageComponent,
    BarreLateraleComponent,
    AccueilComponent,
    LayoutPrincipalComponent,
    LayoutAuthentificationComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    RouterModule
  ],
  providers: [
    provideHttpClient(withInterceptors([authentificationInterceptor]))
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
