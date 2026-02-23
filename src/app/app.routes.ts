import { Routes } from '@angular/router';
import { LayoutPrincipalComponent } from './widgets/layout/principal-layout.component';
import { LayoutAuthentificationComponent } from './widgets/layout/authentification-layout.component';
import { authentificationGuard } from './core/guards/authentification.guard';
import { AccueilComponent } from './widgets/home/accueil.component';

export const routes: Routes = [
    {
        path: 'authentification',
        component: LayoutAuthentificationComponent,
        loadChildren: () => import('./features/authentification/authentification.routes').then(m => m.AUTH_ROUTES)
    },
    {
        path: '',
        component: LayoutPrincipalComponent,
        canMatch: [authentificationGuard],
        children: [
            { path: 'accueil', component: AccueilComponent },
            {
                path: 'administration',
                loadChildren: () => import('./features/administration/administration.routes').then(m => m.ADMINISTRATION_ROUTES)
            },
            {
                path: 'commercant',
                loadChildren: () => import('./features/commercant/commercant.routes').then(m => m.COMMERCANT_ROUTES)
            },
            { path: '', redirectTo: 'accueil', pathMatch: 'full' },
            { path: '**', redirectTo: 'accueil' }
        ]
    }
];
