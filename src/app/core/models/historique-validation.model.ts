import { StatutDemande } from './enumerations.model';
import { Utilisateur } from './utilisateur.model';
import { DemandeCarte } from './demande-carte.model';

export interface HistoriqueValidation {
    id: number;
    dateAction: string;
    commentaire: string;

    agent: Utilisateur;
    demande: DemandeCarte;
    statut: StatutDemande;
}
