import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../../../core/services/auth.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

interface Nationalite {
  id: number;
  nom: string;
  code?: string;
  flagUrl?: string;
}

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  RegisterForm!: FormGroup;
  nationalites: Nationalite[] = [];
  loading = false;
  errorMessage = '';

  constructor(
    private fb: FormBuilder,
    private authService: AuthService,
    private router: Router
  ) {}

  ngOnInit(): void {

    // 🔹 FormGroup
    this.RegisterForm = this.fb.group({
      nom: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      telephone: ['', [Validators.required, Validators.pattern('^[0-9]{8}$')]],
      nationaliteId: [null, Validators.required],
      password: ['', [
        Validators.required,
        Validators.minLength(8),
        Validators.maxLength(8)
      ]]
    });

    // 🔹 Charger nationalités
    this.authService.getAllNationalites().subscribe({
      next: (data) => {
        this.nationalites = data.map(n => ({
          ...n,
          flagUrl: n.code?.trim()
            ? `assets/flags/${n.code.trim().toLowerCase()}.png`
            : 'assets/flags/no-flag.png'
        }));
      }
    });
  }

  get f() {
    return this.RegisterForm.controls;
  }

  onSubmit(): void {

    if (this.RegisterForm.invalid) {
      this.RegisterForm.markAllAsTouched();
      return;
    }

    this.loading = true;
    this.errorMessage = '';

    this.authService.register(this.RegisterForm.value).subscribe({
      next: () => {
        this.loading = false;

        alert('Inscription réussie ! Un code a été envoyé à votre email.');

        // 🔹 Aller vers page verify
        this.router.navigate(['/auth/verify-email']);
      },
      error: (err) => {
        this.loading = false;
        this.errorMessage =
          err?.error?.message ||
          "Erreur lors de l'inscription. Réessayez.";
      }
    });
  }
}