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

    this.RegisterForm = this.fb.group({
      type: ['physique', Validators.required],
      nom: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      telephone: ['', [
        Validators.required,
        Validators.pattern('^[0-9]{8}$')
      ]],
      nationaliteId: [null, Validators.required],
      password: ['', [
        Validators.required,
        Validators.minLength(8),
        Validators.maxLength(100) // ← تعديل maxLength
      ]]
    });

    // Charger les nationalités
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

    const formValue = this.RegisterForm.value;

     let payload: any = {
      email: formValue.email,
      password: formValue.password,
      nationaliteId: Number(formValue.nationaliteId), // تأكد رقم
      telephone: formValue.telephone,
      type: formValue.type,
     
    };

    if (formValue.type === 'physique') {
      const parts = formValue.nom.trim().split(' ');
      payload.nom = parts[0];
      payload.prenom = parts.slice(1).join(' ') || '';
      payload.societe = '';
    } else {
      payload.societe = formValue.nom;
      payload.nom = '';
      payload.prenom = '';
    }

    this.loading = true;
    this.errorMessage = '';

    this.authService.register(payload).subscribe({
      next: () => {
        this.loading = false;
        alert('Inscription réussie ! Un code a été envoyé à votre email.');
        this.router.navigate(['/auth/verify-email']);
      },
      error: (err) => {
        this.loading = false;
        console.error(err); // أفضل للطباعة
        this.errorMessage =
          err?.error?.message ||
          "Erreur lors de l'inscription. Réessayez.";
      }
    });
  }
}