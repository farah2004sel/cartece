import { DemandeCarte } from '../../../core/models/demande-carte.model';

export interface PieceJustificative {
    id: number;
    libelle: string;
    cheminFichier: string;
    demande: DemandeCarte;
}
