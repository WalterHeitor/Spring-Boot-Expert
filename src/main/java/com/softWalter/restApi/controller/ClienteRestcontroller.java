package com.softWalter.restApi.controller;

import ch.qos.logback.core.net.server.Client;
import com.softWalter.model.Cliente;
import com.softWalter.repository.ClientesRepository;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

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
    @GetMapping("/{id}")
    public Cliente getClienteById(@PathVariable("id") Long id){
        return clientesRepository
                .findById(id)
                .orElseThrow(()->
                        new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Cliente não encontrado"
                        ));


    }

    @PostMapping("/salvar")
    @ResponseStatus(HttpStatus.CREATED)
    public Cliente save(@RequestBody Cliente cliente){
        return clientesRepository.save(cliente);
    }
    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
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
    @ResponseStatus(HttpStatus.NO_CONTENT)
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
    @GetMapping("/buscar")
    public List<Cliente> find(Cliente filtro){
        ExampleMatcher matcher = ExampleMatcher
                .matching()
                .withIgnoreCase()
                .withStringMatcher(
                        ExampleMatcher.StringMatcher.CONTAINING
                );
        Example example = Example.of(filtro, matcher);
        List<Cliente>lista = clientesRepository.findAll(example);
        return clientesRepository.findAll(example);
    }
}
