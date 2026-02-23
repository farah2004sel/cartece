import { TypeCommercant } from '../../../core/models/enums.model';
import { Adresse, Nationalite, Activite } from '../../../core/models/metadata.model';

export interface CommercantRequest {
    typeCommercant: TypeCommercant;
    nom?: string;
    prenom?: string;
    raisonSociale?: string;
    dateNaissanceOuCreation: string;
    nationaliteId: number;
    adresse: Adresse;
    activiteId: number;
}
