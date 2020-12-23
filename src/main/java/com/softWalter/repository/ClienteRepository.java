package com.softWalter.repository;

import antlr.StringUtils;
import com.softWalter.model.Cliente;
import com.sun.jmx.snmp.SnmpStatusException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import java.util.List;

@Repository
public class ClienteRepository {

    @Autowired
    private EntityManager entityManager;

    @Transactional
    public Cliente salvar(Cliente cliente){
        entityManager.persist(cliente);
        return cliente;
    }
    @Transactional
    public Cliente atualiza(Cliente cliente){
        entityManager.merge(cliente);
        return cliente;
    }
    @Transactional
    public void deletar(Cliente cliente){
        if (!entityManager.contains(cliente)) {
            cliente = entityManager.merge(cliente);
        }
        entityManager.remove(cliente);
    }
    @Transactional
    public void deletar(Long id){
        Cliente cliente = entityManager.find(Cliente.class, id);
    }
   // @Transactional(readOnly = true)
    @Transactional(readOnly = true)
    public List<Cliente>buscarPorNome(String nome){
        String jpql = "select c from Cliente c where c.nome = :nome ";
       TypedQuery<Cliente> query = entityManager.createQuery(jpql, Cliente.class);
       query.setParameter("nome", "%"+ nome +"%");
       return query.getResultList();
    }
    @Transactional(readOnly = true)
    public List<Cliente>buscarTodos(){
        return entityManager.createQuery("from Cliente", Cliente.class).
                getResultList();
    }


}
