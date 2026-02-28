 export interface CommercantRegister {
    fullName: string;
    nom: string;
    prenom: string;
    email: string;
    password: string;
    nationalite: string;
    societe: string;
    telephone: string;
    type: 'physique' | 'morale';
  }