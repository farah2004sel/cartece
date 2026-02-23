import { Routes } from '@angular/router';
import { TableauDeBordComponent } from './tableau-de-bord/tableau-de-bord.component';
import { GestionCommercantComponent } from './gestion-commercant/gestion-commercant.component';
import { GestionDemandesComponent } from './gestion-demandes/gestion-demandes.component';
import { GestionComiteComponent } from './gestion-comite/gestion-comite.component';
import { GestionUtilisateursComponent } from './gestion-utilisateurs/gestion-utilisateurs.component';
import { GestionRolesComponent } from './gestion-roles/gestion-roles.component';
import { ReferenceUniqueComponent } from './reference-unique/reference-unique.component';

import { AdministrationAccueilComponent } from './administration-accueil/administration-accueil.component';

export const ADMINISTRATION_ROUTES: Routes = [
    {
        path: '',
        component: TableauDeBordComponent,
        children: [
            { path: 'accueil', component: AdministrationAccueilComponent },
            { path: 'gestion-commercant', component: GestionCommercantComponent },
            { path: 'gestion-demandes', component: GestionDemandesComponent },
            { path: 'gestion-comite', component: GestionComiteComponent },
            { path: 'gestion-utilisateurs', component: GestionUtilisateursComponent },
            { path: 'gestion-roles', component: GestionRolesComponent },
            { path: 'reference-unique', component: ReferenceUniqueComponent },
            { path: '', redirectTo: 'accueil', pathMatch: 'full' }
        ]
    }
];
