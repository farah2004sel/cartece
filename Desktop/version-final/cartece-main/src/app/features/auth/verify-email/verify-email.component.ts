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

  constructor(private authService: AuthService, private router: Router) {}

  ngOnInit(): void {
     const emailStored = localStorage.getItem('email');
    if (emailStored) this.email = emailStored;
  }

   verify() {
    console.log('Email:', this.email);
    console.log('Code:', this.code);
    this.router.navigate(['/login']);

  }

   
  onSubmit() {
    if (!this.email || !this.code) {
      this.errorMessage = 'Email ou code manquant';
      return;
    }

    this.loading = true;
    this.errorMessage = '';

    this.authService.verifyEmail(this.email, this.code).subscribe({
      next: (res: any) => {
        this.loading = false;
        if (res.success) {
          this.router.navigate(['/auth/login']); 
        } else {
          this.errorMessage = res.message || 'Code invalide';
        }
      },
      error: (err: any) => {
        this.loading = false;
        this.errorMessage = 'Erreur serveur';
      }
    });
  }
  
  
}
