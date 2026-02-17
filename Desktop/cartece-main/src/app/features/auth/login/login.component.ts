import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../../../core/services/auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  username = '';
  password = '';
  loading = false;
  errorMessage = '';
  constructor(private router: Router, private authService: AuthService) { }
  login() {
    this.authService.login(this.username, this.password)
      .subscribe({
        next: (res) => {

          if (res.success) {
            console.log("Login réussi");

             this.router.navigate(['/auth/profile']);
          } else {
            this.errorMessage = res.message;
          }
        },
        error: (err) => {
          this.errorMessage = err.error?.message || "Erreur serveur";
        }
      });
  }
}
