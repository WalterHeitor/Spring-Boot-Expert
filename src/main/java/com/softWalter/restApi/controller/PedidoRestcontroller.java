package com.softWalter.restApi.controller;

import com.softWalter.service.PedidoService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoRestcontroller {
    private PedidoService pedidoService;

    public PedidoRestcontroller(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

}
