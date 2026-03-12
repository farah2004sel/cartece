import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../../../core/services/auth.service';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent {

  profile: any = {
    nom: '',
    prenom: '',
    dateNaissance: '',
    nationalite: ''
  };

  loading = false;

  constructor(
    private authService: AuthService,
    private router: Router
  ) {}

  saveProfile() {
    if (!this.profile.nom || !this.profile.prenom || !this.profile.dateNaissance || !this.profile.nationalite) {
      alert('Veuillez remplir tous les champs');
      return;
    }

    this.loading = true;

    this.authService.updateProfile(this.profile).subscribe({
      next: () => {
        this.loading = false;
        alert('Profil sauvegardé avec succès !');
        this.router.navigate(['/merchant/dashboard']);
      },
      error: () => {
        this.loading = false;
        alert('Erreur serveur');
      }
    });
  }
}
