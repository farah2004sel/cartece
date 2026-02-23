import { TypeDemande } from '../../../core/models/enums.model';

export interface DemandeCarteRequest {
    typeDemande: TypeDemande;
    commercantId: number;
    // other fields like pieces justificatives IDs if needed
}
