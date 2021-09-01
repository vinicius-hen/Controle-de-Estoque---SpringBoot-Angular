package br.com.clientes.rest;
import br.com.clientes.model.entity.Cliente;
import br.com.clientes.model.repository.ClienteRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    @Autowired
    private ClienteRepository repository;



    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cliente salvar( @RequestBody @Valid Cliente cliente) {
        return repository.save(cliente);
    }

    @GetMapping("{id}")
    public Cliente findById(@PathVariable Integer id){
        return repository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado"));
    }
    @GetMapping
    public List<Cliente> findAll(){
        return repository.findAll();
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete (@PathVariable Integer id ){
        repository
                .findById(id)
                .map( cliente -> {
                    repository.delete(cliente);
                    return Void.TYPE;
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado"));
    }
    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public Cliente atualizar(@PathVariable Integer id, @RequestBody @Valid Cliente clienteUpdate){

         Cliente clienteSalvo = repository.findById(id).get();

        if(clienteSalvo == null) {
            throw new NoSuchElementException();
        }

        BeanUtils.copyProperties(clienteUpdate, clienteSalvo, "id");
        return repository.save(clienteSalvo);
    }

//     Cliente c =  repository
//               .findById(id)
//               .map( cliente -> {
//                   clienteUpdate.setId(cliente.getId());
//                  return repository.save(clienteUpdate);
//
//               })
//               .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
//               return c;

}
