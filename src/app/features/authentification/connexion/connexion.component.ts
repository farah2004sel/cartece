import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthentificationService } from '../services/authentification.service';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-connexion',
  standalone: true,
  imports: [CommonModule, FormsModule, RouterModule],
  templateUrl: './connexion.component.html',
  styleUrls: ['./connexion.component.css']
})
export class ConnexionComponent {
  constructor(private router: Router, private authService: AuthentificationService) { }

  login(): void {
    console.log('Connexion réussie (Mock)');
    // Initialiser le flux de vérification
    this.router.navigate(['/authentification/verification-email']);
  }
}
