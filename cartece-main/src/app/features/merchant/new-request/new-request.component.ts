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

  personType: string = 'physique';
  requestCategory: 'physique' | 'morale' | 'marches' = 'physique';
  documents: RequestDocument[] = [];
  profileData: any;  
  loading = false;

  constructor(
    private authService: AuthService,
    private requestService: RequestService,
    private router: Router
  ) {}

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
        { label: 'Justificatif de domicile', category: 'Adresse', file: null }
      ];
    } else if (this.requestCategory === 'morale') {
      this.documents = [
        { label: 'Extrait du registre du commerce', category: 'Identité', file: null },
        { label: 'Pièce d\'identité du responsable légal', category: 'Responsable', file: null }
      ];
    } else if (this.requestCategory === 'marches') {
      this.documents = [
        { label: 'Contrat marché public', category: 'Contrat', file: null },
        { label: 'Attestation fiscale', category: 'Fiscale', file: null }
      ];
    }
  }

  onCategoryChange(category: 'physique' | 'morale' | 'marches') {
    this.requestCategory = category;
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