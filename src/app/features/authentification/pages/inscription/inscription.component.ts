import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthentificationService } from '../../services/authentification.service';

import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-inscription',
  standalone: true,
  imports: [CommonModule, FormsModule, RouterModule],
  templateUrl: './inscription.component.html',
  styleUrls: ['./inscription.component.css']
})
export class InscriptionComponent {
  personType: 'physique' | 'morale' = 'physique';

  constructor(private authService: AuthentificationService, private router: Router) { }

  onSubmit(): void {
    this.authService.setPersonType(this.personType);
    this.router.navigate(['/authentification/connexion']);
  }

  get nameLabel(): string {
    return this.personType === 'physique' ? 'Nom et Prénom' : 'Dénomination';
  }

  get namePlaceholder(): string {
    return this.personType === 'physique' ? 'Votre nom et prénom' : 'Nom de votre entreprise / Dénomination';
  }
}
