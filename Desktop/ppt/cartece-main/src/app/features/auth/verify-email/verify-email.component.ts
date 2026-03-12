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

  formEmail!: FormGroup;  
  loading = false;
  errorMessage = '';
 // timer
 timeLeft: number = 300; // 5 minutes
 interval: any;
 codeExpired = false;
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
    this.startTimer();
  }
  ngOnDestroy() {
    clearInterval(this.interval);
  }
  startTimer() {

    clearInterval(this.interval);

    this.interval = setInterval(() => {

      if (this.timeLeft > 0) {

        this.timeLeft--;

      } else {

        this.codeExpired = true;
        clearInterval(this.interval);

      }

    }, 1000);

  }

  get displayTime() {

    const minutes = Math.floor(this.timeLeft / 60);
    const seconds = this.timeLeft % 60;

    return `${minutes}:${seconds < 10 ? '0' + seconds : seconds}`;

  }
  onSubmit() {

    if (this.formEmail.invalid) {
      this.errorMessage = 'Email ou code invalide';
      return;
    }

    this.loading = true;
    this.errorMessage = '';

    const email = this.formEmail.value.email;
    const code = this.formEmail.value.code;

    this.authService.verifyEmail(email, code).subscribe({

      next: (res: any) => {

        this.loading = false;

        if (res.success) {

          alert(res.message);

          this.router.navigate(['/accueil/login']);

        } else {

          this.errorMessage = res.message;

        }

      },

      error: (err: any) => {

        this.loading = false;

        this.errorMessage =
          err.error?.message || 'Code invalide ou expiré';

      }

    });

  }
  resendCode() {

    const email = this.formEmail.value.email;
  
    if (!email) {
      alert("Email introuvable");
      return;
    }
  
    this.authService.resendCode(email).subscribe({
  
      next: (res: any) => {
  
        alert(res.message);
  
        this.formEmail.get('code')?.reset();
  
      },
  
      error: (err: any) => {
  
        alert(err.error?.message || "Erreur lors de l'envoi du code");
  
      }
  
    });
    
  
  }}