package com.softWalter.repository;

import com.softWalter.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ClientesRepository extends JpaRepository<Cliente, Long> {
    List<Cliente> findByNomeLike(String nome);

    List<Cliente> findByNomeOrId(String nome, Long id);

    boolean existsByNome(String nome);

    @Query(value = " select c from Cliente c where c.nome like :nome ")
    List<Cliente> encontrarPorNome(@Param("nome")String nome);

    @Query(value = " select * from Cliente c where c.nome like '%:nome%' ", nativeQuery = true)
    List<Cliente> encontrarPorNomeSql(@Param("nome")String nome);

    @Query(" delete from Cliente c where c.nome = :nome ")
    @Modifying
    void deleteByNome(@Param("nome") String nome);
}
