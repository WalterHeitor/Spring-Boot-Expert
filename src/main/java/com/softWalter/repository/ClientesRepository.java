package com.softWalter.repository;

import com.softWalter.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClientesRepository extends JpaRepository<Cliente, Long> {
    List<Cliente> findByNomeLike(String nome);

    //List<Cliente> findNomeLike(String nome);

}
