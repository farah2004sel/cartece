import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../../../core/services/auth.service';

@Component({
  selector: 'app-new-request',
  template: `
    <div class="merchant-container bg-light min-vh-100 py-5">
      <div class="container">
        <div class="row justify-content-center">
          <div class="col-lg-10">
            <div class="card border-0 shadow-sm rounded-4 overflow-hidden animate__animated animate__fadeIn">
              <div class="card-header bg-dark-merchant text-white p-4 border-0">
                <div class="d-flex align-items-center gap-3">
                  <div class="icon-circle bg-brand text-white">
                    <i class='bx bx-plus-circle fs-3'></i>
                  </div>
                  <div>
                    <h3 class="fw-bold mb-0">Nouvelle Demande de Carte</h3>
                    <p class="mb-0 opacity-75 small text-white">Merci de remplir les détails de votre demande</p>
                  </div>
                </div>
              </div>

              <div class="card-body p-5">
                <div class="alert alert-info border-0 rounded-3 d-flex align-items-center gap-3 mb-5">
                   <i class='bx bx-info-circle fs-4'></i>
                   <p class="mb-0 small">Certains champs ont été pré-remplis automatiquement à partir de votre profil pour vous faire gagner du temps.</p>
                </div>

                <form (ngSubmit)="submitRequest()">
                  <!-- SECTION : INFORMATIONS PRE-REMPLIES -->
                  <div class="section-title mb-4">
                    <h5 class="fw-bold text-dark border-start border-danger border-4 ps-2">Informations d'Identité <small class="text-muted fw-normal">(Pré-remplies)</small></h5>
                  </div>

                  <div class="row g-4 mb-5">
                    <div class="col-md-6">
                      <label class="form-label small fw-bold text-muted">Nom et prénom / Dénomination</label>
                      <input type="text" class="form-control bg-light border-0 py-3" [value]="profileData?.fullName" readonly>
                    </div>
                    <div class="col-md-6">
                      <label class="form-label small fw-bold text-muted">Nationalité</label>
                      <input type="text" class="form-control bg-light border-0 py-3" [value]="profileData?.nationality" readonly>
                    </div>
                    <div class="col-12">
                      <label class="form-label small fw-bold text-muted">Domicile / Siège social</label>
                      <textarea class="form-control bg-light border-0" rows="2" [value]="profileData?.address" readonly></textarea>
                    </div>

                    <!-- SI PERSONNE MORALE -->
                    <ng-container *ngIf="personType === 'morale'">
                      <div class="col-md-6">
                        <label class="form-label small fw-bold text-muted">Prénom du responsable légal</label>
                        <input type="text" class="form-control bg-light border-0 py-3" [value]="profileData?.repName" readonly>
                      </div>
                      <div class="col-md-6">
                        <label class="form-label small fw-bold text-muted">Nationalité du responsable</label>
                        <input type="text" class="form-control bg-light border-0 py-3" [value]="profileData?.repNationality" readonly>
                      </div>
                    </ng-container>
                  </div>

                  <!-- SECTION : DETAILS DE LA DEMANDE -->
                  <div class="section-title mb-4">
                    <h5 class="fw-bold text-dark border-start border-danger border-4 ps-2">Détails de la demande</h5>
                  </div>

                  <div class="row g-4 mb-5">
                    <div class="col-md-6">
                      <label class="form-label small fw-bold text-muted">Catégorie de la demande</label>
                      <select class="form-select bg-light border-0 py-3" [(ngModel)]="requestCategory" name="reqCat" (change)="onCategoryChange(requestCategory)">
                         <option value="physique">Personne physique</option>
                         <option value="morale">Personne morale</option>
                         <option value="marches">Marchés Publics</option>
                      </select>
                    </div>
                    <div class="col-md-6">
                      <label class="form-label small fw-bold text-muted">Type de carte souhaitée</label>
                      <select class="form-select bg-light border-0 py-3" name="cardType">
                         <option selected disabled>Choisir un type...</option>
                         <option>Première demande</option>
                         <option>Renouvellement</option>
                         <option>Modification</option>
                      </select>
                    </div>
                    <div class="col-12">
                      <label class="form-label small fw-bold text-muted">Secteur d'activité</label>
                      <input type="text" class="form-control bg-light border-0 py-3" placeholder="Ex: Commerce international" name="secteur">
                    </div>
                  </div>

                  <!-- SECTION : DOCUMENTS A FOURNIR -->
                  <div class="section-title mb-4">
                    <h5 class="fw-bold text-dark border-start border-danger border-4 ps-2">Documents à fournir <small class="text-muted fw-normal">(Format PDF)</small></h5>
                  </div>

                  <div class="row g-3 mb-5">
                    <div class="col-12" *ngFor="let doc of documents; let i = index">
                      <div class="document-upload-card d-flex align-items-center justify-content-between p-3 border rounded-3 transition-all" [class.border-success]="doc.file" [class.bg-white]="!doc.file" [class.bg-light-success]="doc.file">
                        <div class="d-flex align-items-center gap-3">
                          <div class="doc-icon" [class.text-danger]="!doc.file" [class.text-success]="doc.file">
                            <i class='bx' [ngClass]="doc.file ? 'bxs-file-pdf' : 'bx-file-blank'"></i>
                          </div>
                          <div>
                            <p class="mb-0 fw-bold small text-dark">{{ doc.label }}</p>
                            <span class="badge bg-light text-muted smaller">{{ doc.category }}</span>
                          </div>
                        </div>
                        
                        <div class="upload-action">
                          <label [for]="'file-' + i" class="btn btn-sm px-3 rounded-pill fw-bold" [class.btn-outline-danger]="!doc.file" [class.btn-success]="doc.file">
                            <span *ngIf="!doc.file"><i class='bx bx-upload me-1'></i> Télécharger</span>
                            <span *ngIf="doc.file"><i class='bx bx-check me-1'></i> Modifié</span>
                          </label>
                          <input type="file" [id]="'file-' + i" hidden accept=".pdf" (change)="onFileSelected($event, i)">
                        </div>
                      </div>
                      <p *ngIf="doc.file" class="smaller text-success mb-0 mt-1 animate__animated animate__fadeIn">
                        <i class='bx bx-check-circle me-1'></i> {{ doc.file.name }} prêt à l'envoi
                      </p>
                    </div>
                  </div>

                  <div class="col-12 mb-4">
                    <label class="form-label small fw-bold text-muted">Commentaires additionnels</label>
                    <textarea class="form-control bg-light border-0" rows="3" placeholder="Précisez votre demande ici..." name="comments"></textarea>
                  </div>

                  <div class="d-flex gap-3 mt-5">
                    <button type="button" class="btn btn-light px-4 py-3 fw-bold rounded-3" (click)="cancel()">
                      Annuler
                    </button>
                    <button type="submit" class="btn btn-danger flex-grow-1 py-3 fw-bold rounded-3 shadow-sm" [disabled]="loading">
                      <span *ngIf="!loading">Soumettre la demande <i class='bx bx-send ms-2'></i></span>
                      <span *ngIf="loading" class="spinner-border spinner-border-sm me-2"></span>
                    </button>
                  </div>
                </form>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  `,
  styles: [`
    .bg-dark-merchant { background-color: #1e1e2d; }
    .icon-circle { width: 50px; height: 50px; border-radius: 12px; display: flex; align-items: center; justify-content: center; }
    .bg-brand { background-color: #ff4d29; }
    .btn-light { background-color: #f1f5f9; color: #64748b; }
    .btn-light:hover { background-color: #e2e8f0; }

    .document-upload-card {
      border: 1px dashed #dee2e6;
      transition: all 0.3s ease;
    }

    .document-upload-card:hover {
      border-color: #ff4d29;
      background-color: #fff9f8;
    }

    .bg-light-success { background-color: #f0fdf4 !important; }
    .doc-icon { font-size: 1.8rem; }
    .transition-all { transition: all 0.2s ease-in-out; }
  `]
})
export class NewRequestComponent implements OnInit {
  profileData: any;
  personType: string = 'physique';
  requestCategory: 'physique' | 'morale' | 'marches' = 'physique';
  loading = false;

  // Documents required based on category
  documents: { label: string, category: string, file: File | null }[] = [];

  constructor(private authService: AuthService, private router: Router) { }

  ngOnInit(): void {
    // this.authService.profileData().subscribe(data => {
    //   this.profileData = data;
    // });

    this.authService.personType.subscribe(type => {
      this.personType = type;
      this.requestCategory = type as any;
      this.updateDocumentList();
    });
  }

  updateDocumentList() {
    this.documents = [];
    if (this.requestCategory === 'physique') {
      this.documents = [
        { label: 'Copie du passeport', category: 'Identité', file: null },
        { label: 'Formulaire de demande de carte', category: 'Administratif', file: null },
        { label: 'Carte de séjour (si résident)', category: 'Spécifique', file: null },
        { label: 'Extrait de casier judiciaire (B3)', category: 'Spécifique', file: null },
        { label: 'Contrat de location du local', category: 'Spécifique', file: null }
      ];
    } else if (this.requestCategory === 'morale') {
      this.documents = [
        { label: 'Copie des passeports des associés', category: 'Identité', file: null },
        { label: 'Formulaire de demande de carte', category: 'Administratif', file: null },
        { label: 'Statuts de la société', category: 'Spécifique', file: null },
        { label: 'PV désignant le représentant légal', category: 'Spécifique', file: null },
        { label: 'B3 du représentant légal', category: 'Spécifique', file: null },
        { label: 'Certificat de non-faillite', category: 'Spécifique', file: null }
      ];
    } else if (this.requestCategory === 'marches') {
      this.documents = [
        { label: 'Attestation identité responsable projets', category: 'Identité', file: null },
        { label: '2 copies du contrat des marchés (État)', category: 'Administratif', file: null },
        { label: 'Contrat de location (si accord accordé)', category: 'Spécifique', file: null }
      ];
    }
  }

  onFileSelected(event: any, index: number) {
    const file = event.target.files[0];
    if (file && file.type === 'application/pdf') {
      this.documents[index].file = file;
    } else {
      alert('Veuillez sélectionner un fichier PDF valide.');
      event.target.value = '';
    }
  }

  onCategoryChange(type: any) {
    this.requestCategory = type;
    this.updateDocumentList();
  }

  submitRequest() {
    const missingDocs = this.documents.filter(d => !d.file);
    if (missingDocs.length > 0) {
      alert(`Veuillez télécharger tous les documents obligatoires (${missingDocs.length} manquant(s)).`);
      return;
    }

    this.loading = true;
    setTimeout(() => {
      this.loading = false;
      alert('Demande et documents soumis avec succès !');
      this.router.navigate(['/merchant/dashboard']);
    }, 2000);
  }

  cancel() {
    this.router.navigate(['/merchant/dashboard']);
  }
}
