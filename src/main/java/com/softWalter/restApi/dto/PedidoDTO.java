package com.softWalter.restApi.dto;

import java.math.BigDecimal;
import java.util.List;

/**
 * {
 *     "cliente": 1,
 *     "total" 100,
 *     "itemPedidos": [
 *        {
 *          "produto": 1,
 *          "quantidade": 10
 *        }
 *      ]
 * }
 */
public class PedidoDTO {
    private Long cliente;
    private BigDecimal total;
    private List<ItemPedidoDTO> items;
}
