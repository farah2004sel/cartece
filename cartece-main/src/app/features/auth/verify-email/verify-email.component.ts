import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../../../core/services/auth.service';

@Component({
  selector: 'app-verify-email',
  templateUrl: './verify-email.component.html',
  styleUrls: ['./verify-email.component.css']
})
export class VerifyEmailComponent implements OnInit {

  email: string = '';
  code: string = '';
  loading = false;
  errorMessage = '';

  constructor(private authService: AuthService, private router: Router) { }

  ngOnInit(): void {
    const emailStored = localStorage.getItem('email');
    if (emailStored) this.email = emailStored;
  }
  onSubmit() {
    if (!this.email || !this.code) {
      this.errorMessage = 'Email ou code manquant';
      return;
    }

    this.loading = true;
    this.errorMessage = '';

    const codeToSend = this.code.toString().trim();
    const emailToSend = this.email.trim();

    this.authService.verifyEmail(emailToSend, codeToSend).subscribe({
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