import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from 'src/environments/environment.development';
import { Dashboard } from '../models/dashboard.model';
import { Observable } from 'rxjs';

@Injectable({
    providedIn: 'root'
})
export class DashboardService {

    private apiUrl = `${environment.apiUrl}/commercant/dashboard`;

    constructor(private http: HttpClient) { }

    getDashboard(): Observable<Dashboard> {
        return this.http.get<Dashboard>(this.apiUrl);
    }
}
