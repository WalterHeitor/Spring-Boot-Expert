package com.softWalter.restApi.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InformacoesPedidoDTO {

    private Long id;
    private String cpf;
    private String nomeCliente;
    private String dataPedido;
    private BigDecimal total;
    private List<InformacoesItemPedidoDTO>items;

}
