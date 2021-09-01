import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router'


import { ClientesService } from 'src/app/clientes.service';
import { Cliente } from '../cliente';


@Component({
  selector: 'app-clientes-lista',
  templateUrl: './clientes-lista.component.html',
  styleUrls: ['./clientes-lista.component.css']
})
export class ClientesListaComponent implements OnInit {

  clientes: Cliente[] = [];
  id: number;
  success: boolean = false;
  errors: String[];
  clienteSelecionado: Cliente;
  mensagemSucesso: string;
  mensagemErro: string;


  constructor( private service: ClientesService,
     private router: Router,
     private activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {

    this.service
    .getClientes()
    .subscribe( resposta => this.clientes = resposta );
  }

  novoCadastro(){
    this.router.navigate(['/clientes/form'])
  }

      
  preparaDelecao( cliente: Cliente){
    this.clienteSelecionado = cliente;
  }
  deletarCliente(){
    this.service
    .deletar( this.clienteSelecionado)
    .subscribe(
      response => {this.mensagemSucesso = 'Cliente deletado com seucesso'
      this.ngOnInit() 
      },
      erro => this.mensagemErro = 'cliente nao deletado'
    )
}
    }
  


