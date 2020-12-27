package com.softWalter.service;

import com.softWalter.enums.StatusPedido;
import com.softWalter.model.Pedido;
import com.softWalter.restApi.dto.PedidoDTO;

import java.util.Optional;

public interface PedidoService {
    Pedido salvar(PedidoDTO pedidoDTO);

    Optional<Pedido> obterPedidoCompleto(Long id);

    void atualizarStatus(Long id, StatusPedido statusPedido);
}
