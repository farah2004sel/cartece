export enum RoleUtilisateur {
    ADMIN = 'ADMIN',
    AGENT_VALIDATION = 'AGENT_VALIDATION',
    COMMERCANT = 'COMMERCANT'
}

export enum TypeCommercant {
    PHYSIQUE = 'PHYSIQUE',
    MORALE = 'MORALE'
}

export enum TypeDemande {
    OBTENTION = 'OBTENTION',
    RENOUVELLEMENT = 'RENOUVELLEMENT',
    MODIFICATION = 'MODIFICATION',
    EXTENSION = 'EXTENSION'
}

export enum StatutDemande {
    EN_ATTENTE = 'EN_ATTENTE',
    ACCEPTEE = 'ACCEPTEE',
    REFUSEE = 'REFUSEE',
    EN_COURS = 'EN_COURS'
}
