import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from '../../../environments/environment';

@Injectable({ providedIn: 'root' })
export abstract class ApiService<T> {

    protected readonly baseUrl = environment.apiBaseUrl;

    constructor(protected http: HttpClient) { }

    protected getAll(endpoint: string): Observable<T[]> {
        return this.http.get<T[]>(`${this.baseUrl}/${endpoint}`);
    }

    protected getById(endpoint: string, id: number): Observable<T> {
        return this.http.get<T>(`${this.baseUrl}/${endpoint}/${id}`);
    }

    protected create(endpoint: string, data: T): Observable<T> {
        return this.http.post<T>(`${this.baseUrl}/${endpoint}`, data);
    }

    protected update(endpoint: string, id: number, data: T): Observable<T> {
        return this.http.put<T>(`${this.baseUrl}/${endpoint}/${id}`, data);
    }

    protected delete(endpoint: string, id: number): Observable<void> {
        return this.http.delete<void>(`${this.baseUrl}/${endpoint}/${id}`);
    }
}
