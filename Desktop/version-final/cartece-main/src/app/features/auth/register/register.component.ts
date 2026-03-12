import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../../../core/services/auth.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  personType: 'physique' | 'morale' = 'physique';  
  commercant = {
    fullName: '',
    nom: '',
    prenom: '',
    email: '',
    password: '',
    nationalite: '',
    societe: '',
    type: 'physique'
  };

  constructor(private authService: AuthService, private router: Router) { }

  ngOnInit(): void {
   }

  onSubmit() {
    const fullNameInput = (document.getElementById('fullName') as HTMLInputElement)?.value?.trim() || '';
    const emailInput = (document.getElementById('email') as HTMLInputElement)?.value?.trim() || '';
    const passwordInput = (document.getElementById('password') as HTMLInputElement)?.value?.trim() || '';
    const nationaliteInput = (document.getElementById('nationalite') as HTMLInputElement)?.value?.trim() || '';
  
    if (!emailInput || !passwordInput || !nationaliteInput || !fullNameInput) {
      alert('Veuillez remplir tous les champs obligatoires !');
      return;
    }
  
    if (this.personType === 'physique') {
      const parts = fullNameInput.split(' ');
      this.commercant.nom = parts[0];
      this.commercant.prenom = parts.slice(1).join(' ') || ' ';
      this.commercant.societe = '';
    } else {
      this.commercant.societe = fullNameInput;
      this.commercant.nom = '';
      this.commercant.prenom = '';
    }
  
    this.commercant.email = emailInput;
    this.commercant.password = passwordInput;
    this.commercant.nationalite = nationaliteInput;
    this.commercant.type = this.personType;
  
     this.authService.register(this.commercant).subscribe({
      next: res => {
        alert('Inscription réussie ! Code envoyé sur votre email.');
        this.router.navigate(['/auth/verify-email']);
      },
      error: err => {
        console.error(err);    
        alert('Erreur lors de l\'inscription. Vérifiez vos champs.');
      }
    });
  }
  
  get nameLabel(): string {
    return this.personType === 'physique' ? 'Nom et Prénom' : 'Dénomination';
  }

  get namePlaceholder(): string {
    return this.personType === 'physique' ? 'Votre nom et prénom' : 'Nom de votre entreprise / Dénomination';
  }
}
