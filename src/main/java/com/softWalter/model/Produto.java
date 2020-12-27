package com.softWalter.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Produto implements Serializable{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "O campo descrição e obridatório.")
    private String descricao;
    @NotNull(message = "O campo preço e obrigatório")
    private BigDecimal preco_unitario;

    public Produto(String descricao, BigDecimal preco_unitario) {
        this.descricao = descricao;
        this.preco_unitario = preco_unitario;
    }

}
