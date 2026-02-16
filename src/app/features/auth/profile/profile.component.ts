import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../../../core/services/auth.service';

@Component({
  selector: 'app-profile',
  template: `
    <div class="auth-container d-flex align-items-center justify-content-center min-vh-100 bg-light py-5">
      <div class="profile-card shadow-lg p-5 bg-white rounded-4 border-top border-5 border-danger animate__animated animate__fadeIn">
        <div class="text-center mb-5">
          <div class="icon-circle bg-light-danger text-danger mx-auto mb-3">
             <i class='bx bx-id-card fs-1'></i>
          </div>
          <h2 class="fw-bold">Profil Professionnel</h2>
          <p class="text-muted small">Ces informations seront enregistrées une seule fois et réutilisées pour vos futures demandes.</p>
        </div>

        <form (ngSubmit)="save()">
          <!-- SECTION 1: IDENTITE COMMUNE -->
          <div class="section-title mb-4">
            <h5 class="fw-bold text-dark border-start border-danger border-4 ps-2">Identité</h5>
          </div>

          <div class="row g-3 mb-4">
            <div class="col-12">
              <label class="form-label small fw-bold text-muted">{{ type === 'physique' ? 'Nom et prénom' : 'Dénomination' }}</label>
              <input type="text" class="form-control bg-light border-0 py-3" placeholder="Entrez le nom complet" name="fullName" [(ngModel)]="profileData.fullName" required>
            </div>
            <div class="col-md-6">
              <label class="form-label small fw-bold text-muted">{{ type === 'physique' ? 'Date de naissance' : 'Date de constitution' }}</label>
              <input type="date" class="form-control bg-light border-0 py-3" name="birthDate" [(ngModel)]="profileData.birthDate" required>
            </div>
            <div class="col-md-6">
              <label class="form-label small fw-bold text-muted">{{ type === 'physique' ? 'Lieu de naissance' : 'Lieu de constitution' }}</label>
              <input type="text" class="form-control bg-light border-0 py-3" placeholder="Ville, Pays" name="birthPlace" [(ngModel)]="profileData.birthPlace" required>
            </div>
            <div class="col-md-6">
              <label class="form-label small fw-bold text-muted">Nationalité</label>
              <input type="text" class="form-control bg-light border-0 py-3" placeholder="Ex: Italienne" name="nationality" [(ngModel)]="profileData.nationality" required>
            </div>
            <div class="col-md-6">
              <label class="form-label small fw-bold text-muted">Date de résidence en Tunisie</label>
              <input type="date" class="form-control bg-light border-0 py-3" name="residenceDate" [(ngModel)]="profileData.residenceDate" required>
            </div>
            <div class="col-12">
              <label class="form-label small fw-bold text-muted">{{ type === 'physique' ? 'Domicile' : 'Siège social' }}</label>
              <textarea class="form-control bg-light border-0" rows="3" placeholder="Adresse complète..." name="address" [(ngModel)]="profileData.address" required></textarea>
            </div>
          </div>

          <!-- SECTION 2: SI PERSONNE MORALE -->
          <div *ngIf="type === 'morale'" class="animate__animated animate__fadeIn">
            <div class="section-title mb-4 pt-3">
              <h5 class="fw-bold text-dark border-start border-danger border-4 ps-2">Informations de la Société</h5>
            </div>
            <div class="row g-3 mb-4">
               <div class="col-12">
                <label class="form-label small fw-bold text-muted">Forme juridique</label>
                <select class="form-select bg-light border-0 py-3" name="legalForm" [(ngModel)]="profileData.legalForm">
                   <option selected disabled value="">Choisir la forme...</option>
                   <option value="SARL">SARL</option>
                   <option value="SUARL">SUARL</option>
                   <option value="SA">SA</option>
                </select>
              </div>
            </div>

            <div class="section-title mb-4 pt-3">
              <h5 class="fw-bold text-dark border-start border-danger border-4 ps-2">Responsable Légal</h5>
            </div>
            <div class="row g-3">
               <div class="col-md-6">
                <label class="form-label small fw-bold text-muted">Nom & Prénom</label>
                <input type="text" class="form-control bg-light border-0 py-3" placeholder="Nom du responsable" name="repName" [(ngModel)]="profileData.repName">
              </div>
              <div class="col-md-6">
                <label class="form-label small fw-bold text-muted">Nationalité</label>
                <input type="text" class="form-control bg-light border-0 py-3" placeholder="Nationalité" name="repNationality" [(ngModel)]="profileData.repNationality">
              </div>
              <div class="col-md-6">
                <label class="form-label small fw-bold text-muted">Date de naissance</label>
                <input type="date" class="form-control bg-light border-0 py-3" name="repBirthDate" [(ngModel)]="profileData.repBirthDate">
              </div>
              <div class="col-md-6">
                <label class="form-label small fw-bold text-muted">Lieu de naissance</label>
                <input type="text" class="form-control bg-light border-0 py-3" placeholder="Lieu" name="repBirthPlace" [(ngModel)]="profileData.repBirthPlace">
              </div>
              <div class="col-12">
                <label class="form-label small fw-bold text-muted">Domicile</label>
                <textarea class="form-control bg-light border-0" rows="2" placeholder="Adresse du responsable..." name="repAddress" [(ngModel)]="profileData.repAddress"></textarea>
              </div>
            </div>
          </div>

          <button type="submit" class="btn btn-danger w-100 py-3 fw-bold mt-5 shadow-sm" [disabled]="loading">
            <span *ngIf="!loading">Finaliser mon Profil <i class='bx bx-check-double ms-2'></i></span>
            <span *ngIf="loading" class="spinner-border spinner-border-sm me-2"></span>
          </button>
        </form>
      </div>
    </div>
  `,
  styles: [`
    .profile-card { width: 100%; max-width: 750px; }
    .icon-circle { width: 80px; height: 80px; border-radius: 50%; display: flex; align-items: center; justify-content: center; }
    .bg-light-danger { background-color: #fff0ed; }
  `]
})
export class ProfileComponent implements OnInit {
  type: 'physique' | 'morale' = 'physique';
  loading = false;

  // Data object to capture form fields
  profileData: any = {
    fullName: '',
    birthDate: '',
    birthPlace: '',
    nationality: '',
    residenceDate: '',
    address: '',
    legalForm: '',
    repName: '',
    repNationality: '',
    repBirthDate: '',
    repBirthPlace: '',
    repAddress: ''
  };

  constructor(private authService: AuthService, private router: Router) { }

  ngOnInit(): void {
    this.authService.personType.subscribe(t => {
      this.type = t;
    });
  }

  save() {
    this.loading = true;
    // Enregistrer les données dans le service
    this.authService.setProfileData(this.profileData);

    setTimeout(() => {
      this.loading = false;
      alert('Profil enregistré avec succès !');
      this.router.navigate(['/merchant/dashboard']);
    }, 1500);
  }
}
