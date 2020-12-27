package com.softWalter.repository;

import com.softWalter.enums.StatusPedido;
import com.softWalter.model.Cliente;
import com.softWalter.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PedidosRepository extends JpaRepository<Pedido, Long> {

    List<Pedido> findByCliente(Cliente cliente);

    @Query(" select p from Pedido p left join fetch p.itemPedidos where p.id = :id ")
    Optional<Pedido> findByIdFetchItemPedidos(@Param("id") Long id);


}
