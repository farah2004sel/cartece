import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';

@Component({
    selector: 'app-gestion-commercant',
    standalone: true,
    imports: [CommonModule],
    template: `
    <div class="admin-page p-4">
      <h2 class="fw-bold mb-4"><i class='bx bx-store me-2'></i> Gestion des Commerçants</h2>
      <div class="card border-0 shadow-sm rounded-4 p-4">
        <p class="text-muted">Interface de gestion des profils commerçants en cours de développement...</p>
      </div>
    </div>
  `
})
export class GestionCommercantComponent { }
