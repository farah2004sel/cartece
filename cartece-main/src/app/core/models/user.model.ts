 export interface User {
    id?: number;
    email: string;
    firstName?: string;
    lastName?: string;
    phone?: string;
    role: 'commercant' | 'agent' | 'admin';
    isEmailVerified: boolean;
    profileCompleted?: boolean;
    pdfFile?: string; 
  }