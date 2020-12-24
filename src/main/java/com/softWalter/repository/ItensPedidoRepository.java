package com.softWalter.repository;

import com.softWalter.model.ItemPedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItensPedidoRepository extends JpaRepository<ItemPedido, Long> {
}
