import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable, of } from 'rxjs';
import { delay, tap } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private _isVerified = new BehaviorSubject<boolean>(false);
  private _isFirstLogin = new BehaviorSubject<boolean>(true);
  private _personType = new BehaviorSubject<'physique' | 'morale'>('physique');
  private _profileData = new BehaviorSubject<any>(null);
  private _verificationCode: string = '';

  setPersonType(type: 'physique' | 'morale') {
    this._personType.next(type);
  }

  get personType(): Observable<'physique' | 'morale'> {
    return this._personType.asObservable();
  }

  setProfileData(data: any) {
    this._profileData.next(data);
  }

  get profileData(): Observable<any> {
    return this._profileData.asObservable();
  }

  constructor() { }

  // Simulates sending a verification code
  sendVerificationCode(email: string): Observable<boolean> {
    this._verificationCode = Math.floor(100000 + Math.random() * 900000).toString();
    console.log(`[SIMULATION] Code de vérification envoyé à ${email} : ${this._verificationCode}`);
    return of(true).pipe(delay(1000));
  }

  verifyCode(code: string): Observable<boolean> {
    if (code === this._verificationCode) {
      this._isVerified.next(true);
      return of(true);
    }
    return of(false);
  }

  updatePassword(newPassword: string): Observable<boolean> {
    // In a real app, this would be an API call
    this._isFirstLogin.next(false);
    return of(true).pipe(delay(800));
  }

  get isVerified(): Observable<boolean> {
    return this._isVerified.asObservable();
  }

  get isFirstLogin(): Observable<boolean> {
    return this._isFirstLogin.asObservable();
  }
}
