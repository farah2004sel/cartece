import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute, RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { AuthentificationService } from '../../services/authentification.service';

@Component({
  selector: 'app-verification-email',
  standalone: true,
  imports: [CommonModule, FormsModule, RouterModule],
  template: `
    <div class="auth-container d-flex align-items-center justify-content-center min-vh-100 bg-light">
      <div class="verify-card shadow-lg p-5 bg-white rounded-4 border-top border-5 border-danger animate__animated animate__fadeIn">
        <div class="text-center mb-4">
          <div class="icon-circle bg-light-danger text-danger mx-auto mb-3">
             <i class='bx bx-envelope fs-1'></i>
          </div>
          <h2 class="fw-bold">Vérification de l'e-mail</h2>
          <p class="text-muted">Un code de vérification a été envoyé à <strong>{{ email }}</strong>. Veuillez l'entrer ci-dessous.</p>
        </div>

        <div class="code-inputs d-flex justify-content-between mb-4">
          <input type="text" maxlength="6" class="form-control text-center fs-2 fw-bold tracking-widest py-3" 
                 placeholder="000000" #codeInput (keyup.enter)="verify(codeInput.value)">
        </div>

        <button class="btn btn-danger w-100 py-3 fw-bold mb-3 shadow-sm" (click)="verify(codeInput.value)" [disabled]="loading">
          <span *ngIf="!loading">Vérifier le code</span>
          <span *ngIf="loading" class="spinner-border spinner-border-sm me-2"></span>
        </button>

        <div class="text-center">
          <p class="mb-0 text-muted smaller">Vous n'avez pas reçu le code ? 
            <a href="javascript:void(0)" class="text-danger fw-bold text-decoration-none ms-1" (click)="resend()">Renvoyer</a>
          </p>
        </div>
      </div>
    </div>
  `,
  styles: [`
    .verify-card { width: 100%; max-width: 450px; }
    .icon-circle { width: 80px; height: 80px; border-radius: 50%; display: flex; align-items: center; justify-content: center; }
    .bg-light-danger { background-color: #fff0ed; }
    .tracking-widest { letter-spacing: 0.5rem; }
  `]
})
export class VerificationEmailComponent implements OnInit {

  loading = false;
  email = '';

  constructor(
    private authService: AuthentificationService,
    private router: Router,
    private route: ActivatedRoute
  ) { }

  ngOnInit(): void {
    this.email = this.route.snapshot.queryParamMap.get('email') ?? '';

    if (!this.email) {
      this.router.navigate(['/authentification/connexion']);
    }
  }

  verify(code: string): void {
    if (!code || code.length !== 6) {
      return;
    }

    this.loading = true;

    this.authService.verifyCode(code).subscribe({
      next: (success: boolean) => {
        this.loading = false;

        if (success) {
          const mode = this.route.snapshot.queryParamMap.get('mode');

          if (mode === 'reset') {
            this.router.navigate(['/authentification/changement-mot-de-passe']);
          } else {
            this.router.navigate(['/authentification/profil']);
          }
        } else {
          alert('Code invalide.');
        }
      },
      error: () => {
        this.loading = false;
        alert('Erreur serveur. Veuillez réessayer.');
      }
    });
  }

  resend(): void {
    if (!this.email) {
      return;
    }

    this.authService.sendVerificationCode(this.email).subscribe({
      next: () => {
        alert('Un nouveau code a été envoyé.');
      },
      error: () => {
        alert('Erreur lors de l’envoi du code.');
      }
    });
  }
}
