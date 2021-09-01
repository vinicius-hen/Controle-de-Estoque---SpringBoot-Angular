import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../auth.service';
import { Login } from './login';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent  {

  username: string;
  password: string;
  loginError: boolean;
  cadastrando: boolean;
  login:Login;
  success: string ;


  constructor( private router: Router, private service: AuthService) { }

  onSubmit(){
    this.router.navigate(['/home'])
    }
    

  preparaCadastrar(event){
    event.preventDefault();
    this.cadastrando = true;
  }
  cancelaCadastro(){
    this.cadastrando = false;
  }
  cadastrar(){
    const login: Login = new Login();
    login.username = this.username;
    login.password = this.password
    this.service.salvar(login).subscribe( response => {
      this.success = "Usuario Cadastrado";
    }, error => {
      this.loginError = true;
    })
  }

}
