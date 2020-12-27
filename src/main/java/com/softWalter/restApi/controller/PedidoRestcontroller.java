package com.softWalter.restApi.controller;

import antlr.ASTNULLType;
import com.softWalter.model.Cliente;
import com.softWalter.model.Pedido;
import com.softWalter.repository.PedidosRepository;
import com.softWalter.restApi.dto.PedidoDTO;
import com.softWalter.service.PedidoService;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoRestcontroller {
    private PedidoService pedidoService;

    public PedidoRestcontroller(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public Long save(@RequestBody PedidoDTO pedidoDTO){
        Pedido pedido = pedidoService.salvar(pedidoDTO);
        return pedido.getId();
    }

   /* @GetMapping()
    public List<Pedido> find(Pedido filtro){
        ExampleMatcher matcher = ExampleMatcher
                .matching()
                .withIgnoreCase()
                .withStringMatcher(
                        ExampleMatcher.StringMatcher.CONTAINING);
        Example example = Example.of(filtro, matcher);

        return pedidosRepository.findAll(example);
    }*/
}
