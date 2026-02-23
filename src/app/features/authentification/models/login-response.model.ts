import { Utilisateur } from '../../../core/models/utilisateur.model';

export interface LoginResponse {
    token: string;
    utilisateur: Utilisateur;
}
