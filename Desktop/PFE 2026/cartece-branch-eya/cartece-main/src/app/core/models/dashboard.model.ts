export interface Dashboard {
    totalDemandes: number;
    demandesEnAttente: number;
    demandesValidees: number;
    derniereDemande?: {
        reference: string;
        type: string;
        date: string;
        status: string;
    };
}
