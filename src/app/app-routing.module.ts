import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { routes } from './app.routes';

@NgModule({
  imports: [RouterModule.forRoot(routes, {
    anchorScrolling: "enabled",
    scrollPositionRestoration: 'enabled',
    scrollOffset: [0, 80]
  })],
  exports: [RouterModule]
})
export class AppRoutingModule { }
