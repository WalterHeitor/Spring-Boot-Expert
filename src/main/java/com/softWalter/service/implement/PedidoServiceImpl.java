package com.softWalter.service.implement;

import com.softWalter.repository.PedidosRepository;
import com.softWalter.service.PedidoService;
import org.springframework.stereotype.Service;

@Service
public class PedidoServiceImpl implements PedidoService {
    private PedidosRepository pedidosRepository;

    public PedidoServiceImpl(PedidosRepository pedidosRepository) {
        this.pedidosRepository = pedidosRepository;
    }
}
