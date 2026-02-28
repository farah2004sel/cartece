import { HttpClient, HttpHeaders } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment.development';
import { CrudOperations } from '../models/crud-operations';

@Injectable({
  providedIn: 'root',
})
export abstract class CrudService<T, ID> implements CrudOperations<T, ID> {


   constructor(){}


   
  _http = inject(HttpClient);

   
  
  protected readonly resourceEndpoint!: string;
 
  
  create(t: T| FormData): Observable<T> {
    return this._http.post<T>(`${environment.apiUrl}${this.resourceEndpoint}/`, t);
  }
  update(id: ID, t: T | FormData): Observable<T> {
   return this._http.put<T>(`${environment.apiUrl}${this.resourceEndpoint}/${id}`,t);
  }

  findOne(id: ID): Observable<T> {
   return this._http.get<T>(`${environment.apiUrl}${this.resourceEndpoint}/${id}`);
  }

  findAll(): Observable<T[]> {
   return this._http.get<T[]>(`${environment.apiUrl}${this.resourceEndpoint}/`);
  }


  delete(id: ID): Observable<void> {
    return this._http.delete<void>(`${environment.apiUrl}${this.resourceEndpoint}/${id}`);
  }
  
}







