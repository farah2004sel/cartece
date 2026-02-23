export interface Adresse {
    id: number;
    pays: string;
    ville: string;
    rue: string;
    codePostal: string;
}

export interface Nationalite {
    id: number;
    libelle: string;
}

export interface SecteurActivite {
    id: number;
    libelle: string;
}

export interface Activite {
    id: number;
    libelle: string;
    secteur: SecteurActivite;
}
