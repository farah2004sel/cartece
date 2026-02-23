import { Component } from '@angular/core';

@Component({
  selector: 'app-principal-layout',
  templateUrl: './principal-layout.component.html',
  styles: [`
    .layout { display: flex; }
    .content { flex: 1; padding: 0; }
  `]
})
export class LayoutPrincipalComponent { }
