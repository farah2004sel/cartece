import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../../../core/services/auth.service';

@Component({
  selector: 'app-change-password',
  template: `
    <div class="auth-container d-flex align-items-center justify-content-center min-vh-100 bg-light">
      <div class="change-card shadow-lg p-5 bg-white rounded-4 border-top border-5 border-danger animate__animated animate__fadeIn">
        <div class="text-center mb-4">
          <div class="icon-circle bg-light-warning text-warning mx-auto mb-3">
             <i class='bx bx-lock-open-alt fs-1'></i>
          </div>
          <h2 class="fw-bold">Sécurisez votre compte</h2>
          <p class="text-muted">Pour votre première connexion, vous devez obligatoirement modifier votre mot de passe temporaire.</p>
        </div>

        <div class="mb-4">
          <label class="form-label fw-bold small text-muted">Nouveau mot de passe</label>
          <div class="input-group">
            <span class="input-group-text bg-light border-0"><i class='bx bx-lock'></i></span>
            <input [type]="showPw ? 'text' : 'password'" class="form-control bg-light border-0 py-3" placeholder="********" #newPw>
            <button class="btn bg-light border-0" (click)="showPw = !showPw">
                <i class='bx' [ngClass]="showPw ? 'bx-hide' : 'bx-show'"></i>
            </button>
          </div>
        </div>

        <div class="mb-4">
          <label class="form-label fw-bold small text-muted">Confirmer le mot de passe</label>
          <div class="input-group">
            <span class="input-group-text bg-light border-0"><i class='bx bx-lock-alt'></i></span>
            <input [type]="showPw ? 'text' : 'password'" class="form-control bg-light border-0 py-3" placeholder="********" #confirmPw>
          </div>
        </div>

        <button class="btn btn-danger w-100 py-3 fw-bold shadow-sm" (click)="save(newPw.value, confirmPw.value)" [disabled]="loading">
          <span *ngIf="!loading">Enregistrer et Continuer</span>
          <span *ngIf="loading" class="spinner-border spinner-border-sm me-2"></span>
        </button>
      </div>
    </div>
  `,
  styles: [`
    .change-card { width: 100%; max-width: 480px; }
    .icon-circle { width: 80px; height: 80px; border-radius: 50%; display: flex; align-items: center; justify-content: center; }
    .bg-light-warning { background-color: #fff9e6; }
  `]
})
export class ChangePasswordComponent {
  loading = false;
  showPw = false;

  constructor(private authService: AuthService, private router: Router) { }

  save(pw1: string, pw2: string) {
    if (!pw1 || pw1 !== pw2) {
      alert('Les mots de passe ne correspondent pas ou sont vides.');
      return;
    }

    this.loading = true;
    this.authService.updatePassword(pw1).subscribe(() => {
      this.loading = false;
      alert('Mot de passe mis à jour avec succès !');
      this.router.navigate(['/auth/profile']);
    });
  }
}
