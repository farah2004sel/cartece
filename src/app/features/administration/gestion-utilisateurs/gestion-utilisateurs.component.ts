import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';

@Component({
    selector: 'app-gestion-utilisateurs',
    standalone: true,
    imports: [CommonModule],
    template: `
    <div class="admin-page p-4">
      <h2 class="fw-bold mb-4"><i class='bx bx-user-circle me-2'></i> Gestion des Utilisateurs</h2>
      <div class="card border-0 shadow-sm rounded-4 p-4">
        <p class="text-muted">Gestion des comptes agents et administrateurs en cours de développement...</p>
      </div>
    </div>
  `
})
export class GestionUtilisateursComponent { }
