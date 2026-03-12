import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, BehaviorSubject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private baseUrl = 'http://localhost:8080/api/auth';

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

  constructor(private http: HttpClient) { }

  register(data: any): Observable<any> {
    return this.http.post(`${this.baseUrl}/register`, data);
  }

  verifyEmail(email: string, code: string): Observable<any> {
    return this.http.post(`${this.baseUrl}/verify-email`, { email, code });
  }

  login(username: string, password: string) {
    return this.http.post<any>(
      'http://localhost:8080/api/auth/login',
      { username, password }
    );
  }
  
  updatePassword(newPassword: string): Observable<any> {
    this._isFirstLogin.next(false);
    return this.http.put(`${this.baseUrl}/change-password`, { newPassword });
  }

  updateProfile(data: FormData) {
    return this.http.post(
      'http://localhost:8080/api/auth/profile',
      data
    );
  }
  

  setVerified(status: boolean) {
    localStorage.setItem('verified', status ? 'true' : 'false');
    this._isVerified.next(status);
  }
}
