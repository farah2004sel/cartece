import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';

@Component({
    selector: 'app-reference-unique',
    standalone: true,
    imports: [CommonModule],
    template: `
    <div class="admin-page p-4">
      <h2 class="fw-bold mb-4"><i class='bx bx-barcode me-2'></i> Référence Unique</h2>
      <div class="card border-0 shadow-sm rounded-4 p-4">
        <p class="text-muted">Paramétrage des identifiants uniques en cours de développement...</p>
      </div>
    </div>
  `
})
export class ReferenceUniqueComponent { }
