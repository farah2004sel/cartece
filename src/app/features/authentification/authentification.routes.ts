import { Routes } from '@angular/router';
import { ConnexionComponent } from './connexion/connexion.component';
import { InscriptionComponent } from './inscription/inscription.component';
import { ProfilComponent } from '../profil/modifier-profil.component';
import { VerificationEmailComponent } from './verification-email/verification-email.component';
import { ChangementMotDePasseComponent } from './changement-mot-de-passe/changement-mot-de-passe.component';
import { MotDePasseOublieComponent } from './mot-de-passe-oublie/mot-de-passe-oublie.component';

export const AUTH_ROUTES: Routes = [
    { path: 'connexion', component: ConnexionComponent },
    { path: 'inscription', component: InscriptionComponent },
    { path: 'mot-de-passe-oublie', component: MotDePasseOublieComponent },
    { path: 'profil', component: ProfilComponent },
    { path: 'verification-email', component: VerificationEmailComponent },
    { path: 'changement-mot-de-passe', component: ChangementMotDePasseComponent },
    { path: '', redirectTo: 'connexion', pathMatch: 'full' }
];
