package com.softWalter.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Cliente implements Serializable{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Campo nome é obrigatório.")
    private String nome;
    @NotEmpty(message = "Campo CPF é obrigatório")
    //@CPF(message = "Informe um CPF válido")
    private String cpf;
    @JsonIgnore
    //@JsonManagedReference
    @OneToMany(mappedBy = "cliente",fetch = FetchType.LAZY)
    private Set<Pedido> pedidos = new HashSet<>();

    public Cliente(String nome) {
        this.nome = nome;
    }

    public Cliente(Long id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public Cliente(String nome, String cpf) {
        this.nome = nome;
        this.cpf = cpf;
    }
}
