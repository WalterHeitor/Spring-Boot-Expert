package com.softWalter.restApi.controller;

import com.softWalter.model.Cliente;
import com.softWalter.repository.ClientesRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/api/clientes")
public class Clientecontroller {

    @RequestMapping(
            value = {"/hello/{nome}", "/api/hello"},
            method = RequestMethod.GET,
            consumes = {"aplication/json", "application/xml"},
            produces = {"aplication/json", "application/xml"}
    )
    @ResponseBody
    public String hello(@PathVariable("nome") String nomeCliente){
        return String.format("Hello %s", nomeCliente);
    }



    private ClientesRepository clientesRepository;
    public Clientecontroller(ClientesRepository clientesRepository) {
        this.clientesRepository = clientesRepository;
    }
    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity getClienteById(@PathVariable("id") Long id){
        Optional<Cliente> cliente = clientesRepository.findById(id);

        if (cliente.isPresent()){
            return ResponseEntity.ok(cliente.get());
        }

    return ResponseEntity.notFound().build();
    }

    @PostMapping("/salvar")
    @ResponseBody
    public  ResponseEntity save(@RequestBody Cliente cliente){
        Cliente clienteSalvo = clientesRepository.save(cliente);
        return ResponseEntity.ok(clienteSalvo);

    }
    @DeleteMapping("/remover/{id}")
    @ResponseBody
    public ResponseEntity deletar(@PathVariable Long id){
        Optional<Cliente> cliente = clientesRepository.findById(id);

        if (cliente.isPresent()){
            clientesRepository.delete(cliente.get());
            return ResponseEntity.noContent().build();
        }
    return ResponseEntity.notFound().build();
    }
}
