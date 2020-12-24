package com.softWalter.repository;

import com.softWalter.model.Cliente;
import com.softWalter.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PedidosRepository extends JpaRepository<Pedido, Long> {

    List<Pedido> findByCliente(Cliente cliente);
}
