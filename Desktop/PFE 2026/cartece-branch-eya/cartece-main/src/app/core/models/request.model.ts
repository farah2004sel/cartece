export interface Request {
    reference: string;
    type: 'Première Demande' | 'Renouvellement' | 'Modification';
    date: string;
    status: 'En attente' | 'Traitement' | 'Validé' | 'Refusé';
  }
  export interface RequestDocument {
    label: string;
    category: string;
    file: File | null;
  }
  
   