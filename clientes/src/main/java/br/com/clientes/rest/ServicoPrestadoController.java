package br.com.clientes.rest;

import br.com.clientes.model.entity.Cliente;
import br.com.clientes.model.entity.ServicoPrestado;
import br.com.clientes.model.repository.ClienteRepository;
import br.com.clientes.model.repository.ServicoPrestadoRepository;
import br.com.clientes.rest.dto.ServicoPrestadoDTO;
import br.com.clientes.util.BigDecimalConvert;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;


@RestController
@RequestMapping("api/servicos-prestados")
@RequiredArgsConstructor
public class ServicoPrestadoController {


    @Autowired
    private final ClienteRepository clienteRepository = null;

    @Autowired
    private final ServicoPrestadoRepository repository = null;

    @Autowired
    private final BigDecimalConvert bigDecimalConvert = null;


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ServicoPrestado salvar (@RequestBody @Valid ServicoPrestadoDTO dto) {
        LocalDate data = LocalDate.parse(dto.getData(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        Integer idCliente = dto.getIdCliente();
        Cliente cliente = clienteRepository
                .findById(idCliente)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cliente inexistente"));

        ServicoPrestado servicoPrestado = new ServicoPrestado();
        servicoPrestado.setDescricao(dto.getDescricao());
        servicoPrestado.setDate(data);
        servicoPrestado.setCliente(cliente);
        servicoPrestado.setValor( bigDecimalConvert.converter(dto.getPreco()));

        return repository.save(servicoPrestado);
        
    }

    @GetMapping
    public List<ServicoPrestado> pesquisar(
            @RequestParam(value = "nome", required = false, defaultValue = "") String nome,
            @RequestParam(value = "mes", required = false) Integer mes
    ){
        return repository.findByNomeClienteAndMes("%" + nome + "%", mes);

    }




}