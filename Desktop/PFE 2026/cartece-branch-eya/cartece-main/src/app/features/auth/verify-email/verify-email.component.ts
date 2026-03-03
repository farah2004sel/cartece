import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from '../../../core/services/auth.service';

@Component({
  selector: 'app-verify-email',
  templateUrl: './verify-email.component.html',
  styleUrls: ['./verify-email.component.css']
})
export class VerifyEmailComponent implements OnInit {

  formEmail!: FormGroup;  // formulaire reactive
  loading = false;
  errorMessage = '';

  constructor(
    private fb: FormBuilder,
    private authService: AuthService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.formEmail = this.fb.group({
      email: [localStorage.getItem('email') || '', [Validators.required, Validators.email]],
      code: ['', Validators.required]
    });
  }

  onSubmit() {
    if (this.formEmail.invalid) {
      this.errorMessage = 'Email ou code manquant ou invalide';
      return;
    }

    this.loading = true;
    this.errorMessage = '';

    const email = this.formEmail.value.email.trim();
    const code = this.formEmail.value.code.trim();

    this.authService.verifyEmail(email, code).subscribe({
      next: (res: any) => {
        this.loading = false;
        if (res.success) {
          alert(res.message);
          this.router.navigate(['/accueil/profile']);
        } else {
          this.errorMessage = res.message || 'Code invalide';
        }
      },
      error: (err: any) => {
        this.loading = false;
        this.errorMessage = err.error?.message || 'Erreur serveur';
      }
    });
  }
}