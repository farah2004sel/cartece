import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, BehaviorSubject } from 'rxjs';
import { ApiResponse } from '../models/response-api';
import { environment } from 'src/environments/environment.development';
import { CrudService } from './crud.service';
import { LoginRequest, LoginResponse } from '../models/auth.model';
import { CookieService } from 'ngx-cookie-service';
import { Profile } from '../models/profile.model';
export interface Nationalite {
  id: number;
  nom: string;
  code?:string;
}

@Injectable({
  providedIn: 'root'
})
export class AuthService extends CrudService<ApiResponse<any>, number> {
  private profileUrl = `${environment.apiUrl}/v1/commercants`;
  private baseUrl = `${environment.apiUrl}/v1/auth`;
constructor(private http: HttpClient,private cookieService: CookieService) {
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
    return this.http.post(`${this.baseUrl}/verify-account`, { email, code });
  }
resendCode(email: string): Observable<any> {
  return this.http.post(`${this.baseUrl}/resend-code`, { email: email });
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

  updateProfile(userId: number, profileData: any): Observable<any> {
    return this.http.put(`${this.profileUrl}/profile/${userId}`, profileData);
  }
  setVerified(status: boolean) {
    localStorage.setItem('verified', status ? 'true' : 'false');
    this._isVerified.next(status);
  }

   getAllNationalites(): Observable<Nationalite[]> {
    return this.http.get<Nationalite[]>(`${environment.apiUrl}/nationalites`);
  }

 /* get token from cookies */
 getToken() :any{
  if(this.cookieService.get('token'))
   return this.cookieService.get('token')
}
 /* clear cookies */
 clearCookies() {
  this.cookieService.deleteAll('/');
  var cookies = document.cookie.split(';');
  for (let value of cookies) {
    const eqPos = value.indexOf('=');
    const name = eqPos > -1 ? value.substr(0, eqPos) : value;
    document.cookie = name + '=;expires=Thu, 01 Jan 1970 00:00:00 GMT';
  }
}
setCookies(key:string,value:string,expireTime?: Date){
  this.cookieService.delete(key)
  this.cookieService.set(key,value,expireTime,'/')
}
// apiUrl = environnement.apiUrl + '/commercants'

 
getProfile(userId: number): Observable<any> {
  return this.http.get(`${this.profileUrl}/profile/${userId}`);
}
createProfile(userId: number, payload: any): Observable<any> {
  return this.http.post(`${this.profileUrl}/create-profile/${userId}`, payload);
}


}