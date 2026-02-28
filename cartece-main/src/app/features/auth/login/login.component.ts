import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../../../core/services/auth.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { LoginRequest, LoginResponse } from 'src/app/core/models/auth.model';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  FormLogin!: FormGroup;  // Nom identique au HTML
  loading = false;
  errorMessage: string = '';

  constructor(
    private router: Router,
    private authService: AuthService,
    private formBuilder: FormBuilder
  ) {}

  ngOnInit(): void {
    this.FormLogin = this.formBuilder.group({
      usernameOrEmail: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required]]
    });
  }

  login(): void {
    // Valider le formulaire
    if (this.FormLogin.invalid) {
      this.FormLogin.markAllAsTouched(); // marque tous les champs comme touchés pour afficher les erreurs
      return;
    }

    const payload: LoginRequest = this.FormLogin.value;
    this.loading = true;
    this.errorMessage = '';

    this.authService.login(payload).subscribe({
      next: (res: LoginResponse) => {
        this.loading = false;

        // Vérifier si le compte est activé
        if (!res.userEnable) {
          this.errorMessage = 'Utilisateur non activé';
          return;
        }

        // Stocker le token dans le localStorage
        localStorage.setItem('token', res.token);

        // Redirection selon le rôle
        if (res.role?.toLowerCase() === 'commercant') {
          this.router.navigate(['/accueil/commercant']);
        } else {
          this.router.navigate(['/accueil']);
        }
      },
      error: (err: any) => {
        this.loading = false;

        // Messages d'erreur détaillés selon le code HTTP
        if (err.status === 401) {
          this.errorMessage = "Utilisateur non trouvé ou mot de passe incorrect";
        } else if (err.status === 403) {
          this.errorMessage = "Compte bloqué temporairement";
        } else {
          this.errorMessage = "Erreur lors de la connexion, réessayez plus tard";
        }
      }
    });
  }
}