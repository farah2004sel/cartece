 import { User } from './user.model';

export interface LoginRequest {
  
  usernameOrEmail: string;
  password: string;
}


export interface RegisterRequest {
  nom:string;
  prenom? :string;
  societe? :string;
   email: string;
  password: string;
  nationalite?: string;
  telephone?: string;
  type?: string; 
}
 
export interface VerifyEmailRequest {
  email: string;
  code: string;
}
export interface LoginResponse {
  token: string;
  user: {
    id: number;
    userName: string | null;
    userEmail: string;
    role: {
      roleName: string;
    };
  };
  profileCompleted: boolean;

  email: string;
  role: string;
  verified: boolean;
  userEnable: boolean;
}


export interface AuthResponse {
  token: string;
  user: User;
}