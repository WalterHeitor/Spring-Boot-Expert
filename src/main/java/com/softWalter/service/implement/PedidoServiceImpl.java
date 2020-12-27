package com.softWalter.service.implement;

import com.softWalter.exception.RegraNegocioException;
import com.softWalter.model.Cliente;
import com.softWalter.model.ItemPedido;
import com.softWalter.model.Pedido;
import com.softWalter.model.Produto;
import com.softWalter.repository.ClientesRepository;
import com.softWalter.repository.ItensPedidoRepository;
import com.softWalter.repository.PedidosRepository;
import com.softWalter.repository.ProdutosRepository;
import com.softWalter.restApi.dto.ItemPedidoDTO;
import com.softWalter.restApi.dto.PedidoDTO;
import com.softWalter.service.PedidoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor // cria construtor nas variaveis final
public class PedidoServiceImpl implements PedidoService {
    private final PedidosRepository pedidosRepository;
    private final ClientesRepository clientesRepository;
    private final ProdutosRepository produtosRepository;
    private final ItensPedidoRepository itensPedidoRepository;

    @Override
    @Transactional // salva commit ou nao salva rollback.
    public Pedido salvar(PedidoDTO pedidoDTO) {
        Long idCliente = pedidoDTO.getCliente();
        Cliente cliente = clientesRepository
                .findById(idCliente).orElseThrow(() ->
                new RegraNegocioException("Código de cliente inválido"));
        Pedido pedido = new Pedido();
        pedido.setTotal(pedidoDTO.getTotal());
        pedido.setDataPedido(LocalDate.now());
        pedido.setCliente(cliente);

        List<ItemPedido> itemPedidos = converterItems(pedido, pedidoDTO.getItems());
        pedidosRepository.save(pedido);
        itensPedidoRepository.saveAll(itemPedidos);
        pedido.setItemPedidos(itemPedidos);
        return pedido;
    }

    @Override
    public Optional<Pedido> obterPedidoCompleto(Long id) {
        return pedidosRepository.findByIdFetchItemPedidos(id);
    }

    private List<ItemPedido> converterItems (Pedido pedido,
                                          List<ItemPedidoDTO> items){
        if (items.isEmpty()){
            throw new RegraNegocioException("Não é possivel realizar" +
                    " um pedido sem items.");
        }
        return items
                .stream()
                .map(itemPedidoDTO -> {
                    Long idProduto = itemPedidoDTO.getProduto();
                    Produto produto = produtosRepository.findById(idProduto)
                            .orElseThrow(() -> new RegraNegocioException("Código" +
                                    " de produto inválido: " + idProduto));
                    ItemPedido itemPedido = new ItemPedido();
                    itemPedido.setQuantidade(itemPedidoDTO.getQuantidade());
                    itemPedido.setPedido(pedido);
                    itemPedido.setProduto(produto);
                    return itemPedido;
                }).collect(Collectors.toList());//transforma stream() em uma lista.
    }
}
