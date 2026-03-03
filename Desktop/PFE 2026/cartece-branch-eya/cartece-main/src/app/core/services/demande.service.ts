import { Injectable } from '@angular/core';
import { CrudService } from './crud.service';
import { environment } from 'src/environments/environment.development';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { ApiResponse } from '../models/response-api';

@Injectable({
  providedIn: 'root'
})
export class DemandeService extends CrudService<ApiResponse<any>, number>{
  
  
  private baseUrl = `${environment.apiUrl}/auth`;

  constructor(private http: HttpClient ,private router:Router) {
    super()
  }
}

