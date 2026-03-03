import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../../../core/services/auth.service';
import { RequestService } from 'src/app/core/services/request.service';
import { RequestDocument } from 'src/app/core/models/request.model';

@Component({
  selector: 'app-new-request',
  templateUrl: './new-request.component.html',
  styleUrls: ['./new-request.component.css']
})
export class NewRequestComponent implements OnInit {

  isResident: boolean = false;
  requestCategory: 'physique' | 'morale' | 'marches' = 'physique';
  documents: RequestDocument[] = [];
  profileData: any;
  loading = false;

  constructor(
    private authService: AuthService,
    private requestService: RequestService,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.authService.getProfileData().subscribe((data: any) => {
      this.profileData = data;
    });

    this.updateDocumentList();
  }

  updateDocumentList() {
    if (this.requestCategory === 'physique') {
      this.documents = [
        { label: 'Copie du passeport', category: 'Identité', file: null },
        { label: 'Formulaire de demande de carte', category: 'Administratif', file: null }
      ];

      if (this.isResident) {
        this.documents.push(
          { label: 'Carte de séjour', category: 'Résidence', file: null },
          { label: 'Extrait du casier judiciaire (B3)', category: 'Administratif', file: null },
          { label: 'Contrat de location du local commercial', category: 'Local', file: null }
        );
      }
    } else if (this.requestCategory === 'morale') {
      this.documents = [
        { label: 'Copie des passeports des associés', category: 'Identité', file: null },
        { label: 'Formulaire de demande de carte', category: 'Administratif', file: null },
        { label: 'Statuts de la société', category: 'Juridique', file: null },
        { label: 'PV désignant le représentant légal', category: 'Juridique', file: null },
        { label: 'Extrait du casier judiciaire (B3) du représentant', category: 'Administratif', file: null },
        { label: 'Certificat de non-faillite ou Déclaration sur l’honneur', category: 'Juridique', file: null }
      ];
    } else if (this.requestCategory === 'marches') {
      this.documents = [
        { label: 'Attestation identité du responsable projet', category: 'Identité', file: null },
        { label: 'Copie 1 - Contrat enregistré des marchés', category: 'Contrat', file: null },
        { label: 'Copie 2 - Contrat enregistré des marchés', category: 'Contrat', file: null }
      ];
    }
  }

  onCategoryChange(category: 'physique' | 'morale' | 'marches') {
    this.requestCategory = category;
    this.updateDocumentList();
  }

  toggleResident() {
    this.isResident = !this.isResident;
    this.updateDocumentList();
  }

  onFileSelected(event: any, index: number) {
    const file = event.target.files[0];
    if (file?.type === 'application/pdf') {
      this.documents[index].file = file;
    } else {
      alert('Seuls les fichiers PDF sont autorisés.');
    }
  }

  submitRequest() {
    const formData = new FormData();
    formData.append('category', this.requestCategory);

    this.documents.forEach(doc => {
      if (doc.file) formData.append('files', doc.file);
    });

    this.loading = true;
    this.requestService.submitRequest(formData).subscribe({
      next: () => {
        this.loading = false;
        this.router.navigate(['/merchant/dashboard']);
      },
      error: () => {
        this.loading = false;
      }
    });
  }

  cancel() {
    this.router.navigate(['/merchant/dashboard']);
  }
}