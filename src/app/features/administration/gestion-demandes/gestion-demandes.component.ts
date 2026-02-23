import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';

@Component({
    selector: 'app-gestion-demandes',
    standalone: true,
    imports: [CommonModule],
    template: `
    <div class="admin-page p-4">
      <h2 class="fw-bold mb-4"><i class='bx bx-file me-2'></i> Gestion des Demandes</h2>
      <div class="card border-0 shadow-sm rounded-4 p-4">
        <p class="text-muted">Suivi et validation des dossiers en cours de développement...</p>
      </div>
    </div>
  `
})
export class GestionDemandesComponent { }
