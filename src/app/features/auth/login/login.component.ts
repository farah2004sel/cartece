import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../../../core/services/auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  constructor(private router: Router, private authService: AuthService) { }

  login() {
    console.log('Connexion réussie (Mock)');
    // Initialiser le flux de vérification
    this.router.navigate(['/auth/verify-email']);
  }
}
