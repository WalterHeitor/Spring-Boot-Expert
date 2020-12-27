package com.softWalter.service;

import com.softWalter.model.Pedido;
import com.softWalter.restApi.dto.PedidoDTO;

public interface PedidoService {
    Pedido salvar(PedidoDTO pedidoDTO);
}
