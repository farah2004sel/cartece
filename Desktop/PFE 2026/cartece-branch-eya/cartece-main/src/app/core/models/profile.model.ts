export interface Profile {
    fullName: string;
    birthDate: string;
    birthPlace: string;
    nationality: string;
    residenceDate: string;
    address: string;
    legalForm?: string;
    repName?: string;
    repNationality?: string;
    repBirthDate?: string;
    repBirthPlace?: string;
    repAddress?: string;
    type: 'physique' | 'morale';
  }