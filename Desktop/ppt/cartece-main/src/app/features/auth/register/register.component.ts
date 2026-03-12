import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../../../core/services/auth.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ToastrService } from 'ngx-toastr';

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
    private router: Router,
    private toastr: ToastrService
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
        Validators.maxLength(100) 
      ]]
    });

     this.authService.getAllNationalites().subscribe({
      next: (data) => {
        this.nationalites = data.map(n => ({
          ...n,
          flagUrl: n.code?.trim()
            ? `assets/flags/${n.code.trim().toLowerCase()}.png`
            : 'assets/flags/no-flag.png'
        }));
        console.log('الجنسيات:', this.nationalites);

            },
            error: (err) => console.error('خطأ في تحميل الجنسيات', err)

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
      username: formValue.email,       // required
      email: formValue.email,          // required
      password: formValue.password,    // required
      roleId: 2,                       // required
      nationaliteId: Number(formValue.nationaliteId), // required
      phone: formValue.telephone,
      typePersonne: formValue.type     // typePersonne بدل type
    };
    
    if (formValue.type === 'physique') {
      const parts = formValue.nom.trim().split(' ');
      payload.firstName = parts[0];              // obligatoire si physique
      payload.lastName = parts.slice(1).join(' ') || '';
      payload.societe = '';
    } else { // morale
      payload.societe = formValue.nom;           // obligatoire si morale
      payload.firstName = '';
      payload.lastName = '';
    }
    this.loading = true;
    this.errorMessage = '';

    this.authService.register(payload).subscribe({
      next: () => {
        this.loading = false;
        //  localStorage.setItem("email", formValue.email);
        alert('Inscription réussie ! Un code a été envoyé à votre email.');
        this.router.navigate(['/auth/verify-email']);
      },
      error: (err) => {
        this.loading = false;
        console.error(err); 
        if(err?.error?.detail==='Username is already taken!'){
          this.toastr.error('Email deja existe !');
        }
        // this.errorMessage =
        //   err?.error?.detail ||
        //   "Username is already taken!";
      }
    });
  }
}