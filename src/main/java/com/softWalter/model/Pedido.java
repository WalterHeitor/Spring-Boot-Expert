package com.softWalter.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.softWalter.enums.StatusPedido;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Pedido implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate dataPedido;

    @Enumerated(EnumType.STRING)
    private StatusPedido statusPedido;

    @Column(precision = 20, scale = 2)
    private BigDecimal total;
    @ManyToOne
    @JoinColumn(name = "cliente_id")
   // @JsonBackReference
    private Cliente cliente;

    @OneToMany(mappedBy = "pedido")
    List<ItemPedido>itemPedidos;


    public Pedido(LocalDate dataPedido, BigDecimal total, Cliente cliente) {
        this.dataPedido = dataPedido;
        this.total = total;
        this.cliente = cliente;
    }

  /*  public List<ItemPedido> getItemPedidos(){
        if(itemPedidos == null){
            itemPedidos = new ArrayList<>();
        }
        return this.itemPedidos;
    }*/
}
