import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute, Params } from '@angular/router'

import { Cliente } from '../cliente';
import {ClientesService} from '../../clientes.service'
import { Observable } from 'rxjs';


@Component({
  selector: 'app-clientes-form',
  templateUrl: './clientes-form.component.html',
  styleUrls: ['./clientes-form.component.css']
})
export class ClientesFormComponent implements OnInit {

  cliente: Cliente;
  success: boolean = false;
  errors: String[];
  dataCadastro: string;
  id: number;
  

  constructor( private service: ClientesService,
    private router: Router,
    private activatedRoute: ActivatedRoute)  {
    this.cliente = new Cliente();
   }

  ngOnInit(): void {
   let params : Observable <Params> = this.activatedRoute.params;
   params.subscribe( urlParams => {
     this.id = urlParams ['id'];
     if(this.id){
     this.service
     .getClienteById(this.id)
     .subscribe(
       response => this.cliente = this.cliente = response,
       errorResponse => this.cliente = new Cliente()
     )
     }
   })

  }

  onSubmit(){
  if(this.id){
    this.service
    .atualizar(this.cliente)
    .subscribe(response => {
      this.success = true;
      this.errors = null;
    }, errorResponse =>{
      this.errors = ['Erro a atualizar'];
    })


  }else{
    this.service
    .salvar(this.cliente)
    .subscribe( response => {
      this.success = true;
      this.errors = [];
      this.cliente = response;
    }, errorResponse => {
      this.errors = errorResponse.error.errors;
      this.success = false;
    
    }
    )
    }
    }

    voltar(){
      this.router.navigate(['/clientes/lista']);
      
  }

}
