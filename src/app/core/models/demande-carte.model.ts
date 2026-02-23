import { TypeDemande, StatutDemande } from './enumerations.model';
import { Commercant } from './commercant.model';

export interface DemandeCarte {
    id: number;
    typeDemande: TypeDemande;
    dateDepot: string;
    statut: StatutDemande;
    commercant: Commercant;
}
