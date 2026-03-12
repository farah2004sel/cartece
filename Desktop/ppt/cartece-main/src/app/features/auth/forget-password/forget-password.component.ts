import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { AuthService } from '../../../core/services/auth.service';
import { Router } from '@angular/router';
@Component({
  selector: 'app-forgot-password',
  templateUrl: './forget-password.component.html',
  styleUrls: ['./forget-password.component.css']})
export class ForgotPasswordComponent {

  forgotForm: FormGroup;
  resetForm: FormGroup;

  successMessage = '';
  errorMessage = '';

  step = 1; // 1 = email , 2 = reset password
  loading = false;
  constructor(
    private fb: FormBuilder,
     private authService: AuthService,
     private router: Router
    ) {

    this.forgotForm = this.fb.group({
      email: ['', [Validators.required, Validators.email]]
    });

    this.resetForm = this.fb.group({
      key: ['', Validators.required],
      newPassword: ['', [Validators.required, Validators.minLength(6)]]
    });

  }

  sendEmail() {

    if (this.forgotForm.invalid) {
      this.errorMessage = "Veuillez entrer un email valide";
      return;
    }
    this.loading = true;

    const email = this.forgotForm.value.email;

    this.authService.forgotPassword(email).subscribe({

      next: () => {
        this.successMessage = "Code envoyé à votre email";
        this.errorMessage = '';
        this.step = 2;
        this.loading = false;
            },

      error: () => {
        this.errorMessage = "Erreur lors de l'envoi";
        this.successMessage = '';
        this.loading = false;
      }

    });

  }

  resetPassword() {

    if (this.resetForm.invalid) {
      this.errorMessage = "Veuillez remplir les champs";
      return;
    }

    const email = this.forgotForm.value.email;
    const key = this.resetForm.value.key;
    const newPassword = this.resetForm.value.newPassword;

    this.authService.resetPassword(email, key, newPassword).subscribe({

      next: () => {

  this.successMessage = "Mot de passe changé avec succès";
  this.errorMessage = '';

  setTimeout(() => {
    this.router.navigate(['/auth/login']);
  }, 1500);

},

      error: () => {
        this.errorMessage = "Code invalide ou erreur serveur";
        this.successMessage = '';
      }

    });

  }

}