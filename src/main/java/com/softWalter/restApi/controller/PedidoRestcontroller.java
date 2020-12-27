package com.softWalter.restApi.controller;

import com.softWalter.enums.StatusPedido;
import com.softWalter.model.ItemPedido;
import com.softWalter.model.Pedido;
import com.softWalter.restApi.dto.AtualizacaoStatusPedidoDTO;
import com.softWalter.restApi.dto.InformacoesItemPedidoDTO;
import com.softWalter.restApi.dto.InformacoesPedidoDTO;
import com.softWalter.restApi.dto.PedidoDTO;
import com.softWalter.service.PedidoService;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

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

    @GetMapping("{id}")
    public InformacoesPedidoDTO getById( @PathVariable Long id ){
        return pedidoService
                .obterPedidoCompleto(id)
                .map( pedido -> converter(pedido) )
                .orElseThrow(() ->
                        new ResponseStatusException(NOT_FOUND, "Pedido não encontrado."));
    }

    @PatchMapping("{id}")
    @ResponseStatus(NO_CONTENT)
    public void updateStatus(@PathVariable Long id,
                             @RequestBody AtualizacaoStatusPedidoDTO dto){
        String novoStatus = dto.getNovoStatus();
        pedidoService.atualizarStatus(id, StatusPedido.valueOf(novoStatus));

    }

    private InformacoesPedidoDTO converter(Pedido pedido){
        return InformacoesPedidoDTO
                .builder()
                .id(pedido.getId())
                .dataPedido(pedido.getDataPedido().format(DateTimeFormatter
                        .ofPattern("dd/MM/yyyy")))
                .cpf(pedido.getCliente().getCpf())
                .nomeCliente(pedido.getCliente().getNome())
                .total(pedido.getTotal())
                .status(pedido.getStatusPedido().name())  //.name converte em uma String
                .items(converter(pedido.getItemPedidos()))
                .build();
    }

    private List<InformacoesItemPedidoDTO> converter(List<ItemPedido> itens){
        if (CollectionUtils.isEmpty(itens)) {
            return Collections.emptyList();
        }

        return itens.stream()
                .map(itemPedido -> InformacoesItemPedidoDTO
                .builder()
                .descricaoProduto(itemPedido.getProduto().getDescricao())
                .precoUnitario(itemPedido.getProduto().getPreco_unitario())
                .quantidade(itemPedido.getQuantidade())
                .build()
                ).collect(Collectors.toList());
    }


}
