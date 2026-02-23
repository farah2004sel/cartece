import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable, of } from 'rxjs';
import { delay, tap } from 'rxjs/operators';
import { Utilisateur } from '../../../core/models/utilisateur.model';

@Injectable({
  providedIn: 'root'
})
export class AuthentificationService {
  private _isVerified = new BehaviorSubject<boolean>(false);
  private _isFirstLogin = new BehaviorSubject<boolean>(true);
  private _personType = new BehaviorSubject<'physique' | 'morale'>('physique');
  private _profileData = new BehaviorSubject<Utilisateur | null>(null);
  private _verificationCode: string = '';

  setPersonType(type: 'physique' | 'morale'): void {
    this._personType.next(type);
  }

  get personType(): Observable<'physique' | 'morale'> {
    return this._personType.asObservable();
  }

  setProfileData(data: Utilisateur): void {
    this._profileData.next(data);
  }

  get profileData(): Observable<Utilisateur | null> {
    return this._profileData.asObservable();
  }

  constructor() { }

  // Simulates sending a verification code
  sendVerificationCode(email: string): Observable<boolean> {
    this._verificationCode = Math.floor(100000 + Math.random() * 900000).toString();
    console.log(`[SIMULATION] Code de vérification envoyé à ${email} : ${this._verificationCode}`);
    return of(true).pipe(
      delay(1000),
      tap((result: boolean) => console.log('Statut de l\'envoi:', result))
    );
  }

  verifyCode(code: string): Observable<boolean> {
    const success = code === this._verificationCode;

    if (success) {
      this._isVerified.next(true);
    }

    return of(success).pipe(delay(600));
  }

  updatePassword(newPassword: string): Observable<boolean> {
    // In a real app, this would be an API call
    this._isFirstLogin.next(false);
    return of(true).pipe(
      delay(800),
      tap((result: boolean) => console.log('Mise à jour du mot de passe:', result))
    );
  }

  get isVerified(): Observable<boolean> {
    return this._isVerified.asObservable();
  }

  get isFirstLogin(): Observable<boolean> {
    return this._isFirstLogin.asObservable();
  }
}
