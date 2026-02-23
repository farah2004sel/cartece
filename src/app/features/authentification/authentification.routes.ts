import { Routes } from '@angular/router';
import { ConnexionComponent } from './pages/connexion/connexion.component';
import { InscriptionComponent } from './pages/inscription/inscription.component';
import { ProfilComponent } from './pages/profil/profil.component';
import { VerificationEmailComponent } from './pages/verification-email/verification-email.component';
import { ChangementMotDePasseComponent } from './pages/changement-mot-de-passe/changement-mot-de-passe.component';

export const AUTH_ROUTES: Routes = [
    { path: 'connexion', component: ConnexionComponent },
    { path: 'inscription', component: InscriptionComponent },
    { path: 'profil', component: ProfilComponent },
    { path: 'verification-email', component: VerificationEmailComponent },
    { path: 'changement-mot-de-passe', component: ChangementMotDePasseComponent },
    { path: '', redirectTo: 'connexion', pathMatch: 'full' }
];
