import { Component } from '@angular/core';
import { AuthService } from '../../../core/services/auth.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-change-password',
  templateUrl: './change-password.component.html',
  styleUrls: ['./change-password.component.css']
})
export class ChangePasswordComponent {

  passwordForm: FormGroup;
  successMessage = '';
  errorMessage = '';

  constructor(private authService: AuthService, private fb: FormBuilder) {
    this.passwordForm = this.fb.group({
      newPassword: ['', [Validators.required, Validators.minLength(6)]],
      confirmPassword: ['', [Validators.required]]
    }, { validator: this.passwordMatch });
  }

   passwordMatch(group: FormGroup) {
    const pass = group.get('newPassword')?.value;
    const confirm = group.get('confirmPassword')?.value;
    return pass === confirm ? null : { notMatching: true };
  }

   changePassword() {
    if (this.passwordForm.invalid) {
      this.errorMessage = ' Veuillez remplir correctement les champs ';
      this.successMessage = '';
      return;
    }

    const newPassword = this.passwordForm.get('newPassword')?.value;

    this.authService.updatePassword(newPassword).subscribe({
      next: () => {
        this.successMessage = 'Le mot de passe a été changé avec succès ';
        this.errorMessage = '';
        this.passwordForm.reset();
      },
      error: () => {
        this.errorMessage = 'Une erreur est survenue lors du changement du mot de passe ';
        this.successMessage = '';
      }
    });
  }
}
