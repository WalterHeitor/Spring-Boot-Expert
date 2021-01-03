package com.softWalter.service;

import com.softWalter.repository.ClientesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public interface ClienteService {

  /*  private ClientesRepositoryRepository repository;

    @Autowired
    public ClienteService(ClientesRepositoryRepository repository){
        this.repository = repository;
    }*/

//    /**
//     * Metodo setRepository usado para injetar a dependencia.
//     * @param repository
//     */
//    public void setRepository(ClienteRepository repository) {
//        this.repository = repository;
//    }

 /*   public void salvarCliente(Cliente cliente){
        validarCliente(cliente);
        this.repository.persistir(cliente);
    }
    public  void validarCliente(Cliente cliente){
        //validar Cliente
    }*/
}
