import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { environment } from 'src/environments/environment.development';
import { Request } from '../models/request.model';

@Injectable({
  providedIn: 'root'
})
export class RequestService {
  constructor(private http: HttpClient) {}

   private requests: Request[] = [
    { reference: '#RT-99', type: 'Première Demande', date: '10 Jan 2026', status: 'En attente' },
    { reference: '#RT-100', type: 'Renouvellement', date: '15 Jan 2026', status: 'Traitement' },
    { reference: '#RT-101', type: 'Modification', date: '20 Jan 2026', status: 'Validé' },
  ];

  getAllRequests(): Observable<Request[]> {
    return of(this.requests);
  }

   getAllRequestsFromBackend(): Observable<Request[]> {
    return this.http.get<Request[]>(`${environment.apiUrl}/requests`);
  };
  submitRequest(formData: FormData): Observable<any> {
    return this.http.post(`${environment.apiUrl}/requests`, formData);
  }
}