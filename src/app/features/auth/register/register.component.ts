import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../../../core/services/auth.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent {
  personType: 'physique' | 'morale' = 'physique';

  constructor(private authService: AuthService, private router: Router) { }

  onSubmit() {
    this.authService.setPersonType(this.personType);
    this.router.navigate(['/auth/login']);
  }

  get nameLabel(): string {
    return this.personType === 'physique' ? 'Nom et Prénom' : 'Dénomination';
  }

  get namePlaceholder(): string {
    return this.personType === 'physique' ? 'Votre nom et prénom' : 'Nom de votre entreprise / Dénomination';
  }
}
