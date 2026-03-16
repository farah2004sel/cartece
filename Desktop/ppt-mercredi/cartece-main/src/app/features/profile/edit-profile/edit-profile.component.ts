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
  selector: 'app-edit-profile',
  templateUrl: './edit-profile.component.html',
  styleUrls: ['./edit-profile.component.css']
})
export class EditProfileComponent implements OnInit {

  profileForm!: FormGroup;
  nationalites: Nationalite[] = [];
  loading = false;
  isNew = true;
  userId!: number;

  constructor(
    private fb: FormBuilder,
    private authService: AuthService,
    private router: Router,
    private toastr: ToastrService
  ) {}

  ngOnInit(): void {

    this.userId = this.authService.getProfileData()?.id!;

    this.profileForm = this.fb.group({
      typePersonne: ['physique', Validators.required],
      societe: [''],
      repName: ['', Validators.required],
      birthDate: ['', Validators.required],
      birthPlace: ['', Validators.required],
      residenceDate: ['', Validators.required],
      address: ['', Validators.required],
      nationaliteId: [null, Validators.required],
      legalForm: [''],
      repNationality: ['']
    });

     this.authService.getAllNationalites().subscribe({
      next: (data) => {
        this.nationalites = data.map((n: any) => ({
          ...n,
          id: Number(n.id),
          flagUrl: n.code
            ? `assets/flags/${n.code.toLowerCase()}.png`
            : 'assets/flags/no-flag.png'
        }));
      }
    });

     if (this.userId) {
      this.authService.getProfile(this.userId).subscribe({
        next: (data: any) => {

          if (data) {

            this.isNew = !data.id;

            this.profileForm.patchValue({
              typePersonne: data.typePersonne || 'physique',
              societe: data.societe || '',
              repName: data.repName || '',
              birthDate: data.birthDate || '',
              birthPlace: data.birthPlace || '',
              residenceDate: data.residenceDate || '',
              address: data.address || '',
              nationaliteId: data.nationaliteId ? Number(data.nationaliteId) : null,
              legalForm: data.legalForm || '',
              repNationality: data.repNationality || ''
            });

          }

        }
      });
    }

  }

  save(): void {

    if (this.profileForm.invalid) {
      this.profileForm.markAllAsTouched();
      return;
    }

    const payload = this.profileForm.value;

    this.loading = true;

    const obs = this.isNew
      ? this.authService.createProfile(this.userId, payload)
      : this.authService.updateProfile(this.userId, payload);

    obs.subscribe({
      next: () => {

        this.loading = false;

        this.toastr.success('Profil enregistré avec succès');

        this.router.navigate(['/accueil/commercant']);

      },
      error: () => {

        this.loading = false;

        this.toastr.error('Erreur serveur');

      }
    });

  }

}