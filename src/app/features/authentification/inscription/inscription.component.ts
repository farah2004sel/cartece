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
  selectedCountryCode: string = '+216';
  email: string = '';

  countries = [
    { code: '+216', name: 'Tunisie', flag: '🇹🇳' },
    { code: '+33', name: 'France', flag: '🇫🇷' },
    { code: '+212', name: 'Maroc', flag: '🇲🇦' },
    { code: '+213', name: 'Algérie', flag: '🇩🇿' },
    { code: '+218', name: 'Libye', flag: '🇱🇾' },
    { code: '+20', name: 'Égypte', flag: '🇪🇬' },
    { code: '+1', name: 'USA/Canada', flag: '🇺🇸' },
    { code: '+44', name: 'UK', flag: '🇬🇧' },
    { code: '+49', name: 'Allemagne', flag: '🇩🇪' },
    { code: '+39', name: 'Italie', flag: '🇮🇹' },
    { code: '+34', name: 'Espagne', flag: '🇪🇸' },
    { code: '+966', name: 'Arabie Saoudite', flag: '🇸🇦' },
    { code: '+971', name: 'UAE', flag: '🇦🇪' }
  ];

  constructor(private authService: AuthentificationService, private router: Router) { }

  onSubmit(): void {
    this.authService.setPersonType(this.personType);
    this.router.navigate(['/authentification/verification-email'], {
      queryParams: { email: this.email, mode: 'register' }
    });
  }

  get nameLabel(): string {
    return this.personType === 'physique' ? 'Nom et Prénom' : 'Dénomination';
  }

  get namePlaceholder(): string {
    return this.personType === 'physique' ? 'Votre nom et prénom' : 'Nom de votre entreprise / Dénomination';
  }
}
