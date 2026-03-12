import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../../../core/services/auth.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { LoginRequest, LoginResponse } from 'src/app/core/models/auth.model';
import { LocalStorageService } from 'src/app/shared/local-storage.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  FormLogin!: FormGroup;  
  loading = false;
  errorMessage: string = '';

  constructor(
    private router: Router,
    private authService: AuthService,
    private formBuilder: FormBuilder,
    private localStorageService:LocalStorageService
  ) {}

  ngOnInit(): void {
    this.FormLogin = this.formBuilder.group({
      usernameOrEmail: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required]]
    });
  }
  login(): void {
    if (this.FormLogin.invalid) {
      this.FormLogin.markAllAsTouched();
      return;
    }
  
    const payload: LoginRequest = this.FormLogin.value;
    this.loading = true;
    this.errorMessage = '';
  
    this.authService.login(payload).subscribe({
      next: (res: LoginResponse) => {
        this.loading = false;
        console.log("res backend",res)
        this.authService.setCookies('token', res.token);
        this.localStorageService.saveData('userConnected', JSON.stringify(res.user));
  
        if (res.user?.role?.roleName?.toLowerCase() === 'commercant') {
          this.router.navigate(['/accueil/commercant']);
        } else {
          this.router.navigate(['/accueil']);
        }
      },
      error: (err: any) => {
        this.loading = false;
  
        if (err.status === 400) {
          this.errorMessage = "Login ou mot de passe incorrect";
        } else {
          this.errorMessage = "Erreur lors de la connexion";
        }
      }
    });
  }}