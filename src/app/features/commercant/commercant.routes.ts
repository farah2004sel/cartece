import { Routes } from '@angular/router';
import { TableauDeBordComponent } from './tableau-de-bord/tableau-de-bord.component';
import { NouvelleDemandeComponent } from './nouvelle-demande/nouvelle-demande.component';

export const COMMERCANT_ROUTES: Routes = [
    { path: 'tableau-de-bord', component: TableauDeBordComponent },
    { path: 'nouvelle-demande', component: NouvelleDemandeComponent },
    { path: '', redirectTo: 'tableau-de-bord', pathMatch: 'full' }
];
