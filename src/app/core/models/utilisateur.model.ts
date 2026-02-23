import { RoleUtilisateur } from './enumerations.model';

export interface Utilisateur {
    id: number;
    email: string;
    motDePasse?: string;
    role: RoleUtilisateur;
    actif: boolean;
}
