import { TypeCommercant } from './enumerations.model';
import { Utilisateur } from './utilisateur.model';
import { Adresse, Nationalite, Activite } from './metadonnees.model';

export interface Commercant {
    id: number;
    typeCommercant: TypeCommercant;

    // Personne Physique
    nom?: string;
    prenom?: string;

    // Personne Morale
    raisonSociale?: string;

    dateNaissanceOuCreation: string;

    nationalite: Nationalite;
    adresse: Adresse;
    activite: Activite;

    utilisateur: Utilisateur;
}
