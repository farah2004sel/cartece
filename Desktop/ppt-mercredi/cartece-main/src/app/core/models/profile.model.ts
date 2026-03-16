export interface Profile {
  typePersonne: 'physique' | 'morale';
  nom?: string;           
  firstName?: string;     
  lastName?: string;      
  societe?: string;       
  birthDate?: string;
  birthPlace?: string;
  residenceDate?: string;
  address?: string;
  nationaliteId?: number | null;
  legalForm?: string;
  repName?: string;
  repNationality?: string;
}