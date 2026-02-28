import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, BehaviorSubject } from 'rxjs';
import { ApiResponse } from '../models/response-api';
import { environment } from 'src/environments/environment.development';
import { CrudService } from './crud.service';
import { LoginRequest, LoginResponse } from '../models/auth.model';

export interface Nationalite {
  id: number;
  nom: string;
  code?:string;
}

@Injectable({
  providedIn: 'root'
})
export class AuthService extends CrudService<ApiResponse<any>, number> {

  private baseUrl = `${environment.apiUrl}/auth`;

  constructor(private http: HttpClient) {
    super();
  }

  private _isVerified = new BehaviorSubject<boolean>(false);
  get isVerified(): Observable<boolean> {
    return this._isVerified.asObservable();
  }

  private _isFirstLogin = new BehaviorSubject<boolean>(true);
  get isFirstLogin(): Observable<boolean> {
    return this._isFirstLogin.asObservable();
  }

  private _personType = new BehaviorSubject<'physique' | 'morale'>('physique');
  get personType(): Observable<'physique' | 'morale'> {
    return this._personType.asObservable();
  }
  setPersonType(type: 'physique' | 'morale') {
    this._personType.next(type);
  }

  private _profileData = new BehaviorSubject<any>(null);
  get profileData$(): Observable<any> {
    return this._profileData.asObservable();
  }
  setProfileData(data: any) {
    this._profileData.next(data);
  }
  getProfileData(): any {
    return this._profileData.getValue();
  }

  register(data: any): Observable<ApiResponse<any>> {
    return this.http.post<ApiResponse<any>>(`${this.baseUrl}/register`, data);
  }

  login(data: LoginRequest): Observable<LoginResponse> {
    return this.http.post<LoginResponse>(`${this.baseUrl}/login`, data );
  }

  verifyEmail(email: string, code: string): Observable<any> {
    return this.http.post(`${this.baseUrl}/verify-email`, { email, code });
  }

  forgotPassword(email: string): Observable<ApiResponse<any>> {
    return this.http.post<ApiResponse<any>>(`${this.baseUrl}/forgot-password?email=${email}`, {});
  }

  resetPassword(email: string, code: string, newPassword: string): Observable<ApiResponse<any>> {
    return this.http.post<ApiResponse<any>>(`${this.baseUrl}/reset-password?email=${email}&key=${code}&newPassword=${newPassword}`, {});
  }

  updatePassword(newPassword: string): Observable<ApiResponse<any>> {
    this._isFirstLogin.next(false);
    return this.http.put<ApiResponse<any>>(`${this.baseUrl}/change-password`, { newPassword });
  }

  updateProfile(data: FormData): Observable<ApiResponse<any>> {
    return this.http.post<ApiResponse<any>>(`${this.baseUrl}/profile`, data);
  }

  setVerified(status: boolean) {
    localStorage.setItem('verified', status ? 'true' : 'false');
    this._isVerified.next(status);
  }

   getAllNationalites(): Observable<Nationalite[]> {
    return this.http.get<Nationalite[]>(`${environment.apiUrl}/nationalites`);
  }
}