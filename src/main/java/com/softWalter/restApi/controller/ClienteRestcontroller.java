package com.softWalter.restApi.controller;

import com.softWalter.model.Cliente;
import com.softWalter.repository.ClientesRepository;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/apirest/clientes")
public class ClienteRestcontroller {

    @RequestMapping(
            value = {"/hello/{nome}", "/api/hello"},
            method = RequestMethod.GET,
            consumes = {"aplication/json", "application/xml"},
            produces = {"aplication/json", "application/xml"}
    )
    public String hello(@PathVariable("nome") String nomeCliente){
        return String.format("Hello %s", nomeCliente);
    }



    private ClientesRepository clientesRepository;
    public ClienteRestcontroller(ClientesRepository clientesRepository) {
        this.clientesRepository = clientesRepository;
    }
    @GetMapping("{id}")
    public Cliente getClienteById(@PathVariable("id") Long id){
        return clientesRepository
                .findById(id)
                .orElseThrow(()->
                        new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Cliente não encontrado"
                        ));


    }

    @PostMapping
    @ResponseStatus(CREATED)
    public Cliente save(@RequestBody Cliente cliente){
        return clientesRepository.save(cliente);
    }
    @DeleteMapping("{id}")
    @ResponseStatus(NO_CONTENT)
    public void deletar(@PathVariable Long id){
        clientesRepository.findById(id)
                    .map(cliente -> {
                        clientesRepository.delete(cliente);
                        return cliente;
                    })
        .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,
                "cliente não encontrado"));
    }
    @PutMapping("{id}")
    @ResponseStatus(NO_CONTENT)
    public void update(@PathVariable Long id,
                                 @RequestBody Cliente cliente){
        clientesRepository
                .findById(id)
                .map(clienteExistente ->{
                        cliente.setId(clienteExistente.getId());
                        clientesRepository.save(cliente);
                        return cliente;
                        })
                        .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "cliente não encontrado"));

    }
    @GetMapping()
    public List<Cliente> find(Cliente filtro){
        ExampleMatcher matcher = ExampleMatcher
                .matching()
                .withIgnoreCase()
                .withStringMatcher(
                        ExampleMatcher.StringMatcher.CONTAINING);
        Example example = Example.of(filtro, matcher);
        return clientesRepository.findAll(example);
    }
}
