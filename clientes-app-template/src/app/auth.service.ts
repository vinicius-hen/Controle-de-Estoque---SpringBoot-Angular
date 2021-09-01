import { Injectable } from '@angular/core';
import{ HttpClient } from '@angular/common/http'
import{environment} from '../environments/environment'
import { Observable } from 'rxjs';
import { Login } from './login/login';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  apiURL: string = environment.apiUrl + '/api/usuarios';

  constructor(private http: HttpClient) {}
  
  salvar( login:Login) : Observable<Login> {
    return this.http.post<Login>(`${this.apiURL}`,login);
  }

}