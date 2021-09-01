package br.com.clientes;

import br.com.clientes.model.entity.Cliente;
import br.com.clientes.model.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ClientesApllication {


    public static void main(String[] args) {
        SpringApplication.run(ClientesApllication.class, args);



    }
}
