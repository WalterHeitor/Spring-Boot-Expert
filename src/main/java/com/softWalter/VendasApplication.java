package com.softWalter;

import com.softWalter.model.Cliente;
import com.softWalter.model.Pedido;
import com.softWalter.repository.ClienteRepository;
import com.softWalter.repository.ClienteRepositoryJdbc;
import com.softWalter.repository.ClientesRepository;
import com.softWalter.repository.PedidosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
@RestController
public class VendasApplication {
    /*@Autowired
    @Qualifier("applicationName")
    @Value("${application.name}")
    private String applicationName;

    @GetMapping("/hello")
    public String helloWord(){
        return applicationName;
    }
     */

    @Bean
    public CommandLineRunner init(@Autowired ClientesRepository clienteRepository,
                                  @Autowired PedidosRepository pedidosRepository){
        return args -> {
           /* System.out.println("salvando Clientes");
            Cliente walter = new Cliente("Walter");
            Cliente heitor = new Cliente("Heitor");
            Cliente freitas = new Cliente("Freitas");

            */
            /*clienteRepository.save(walter);
            clienteRepository.save(heitor);
            clienteRepository.save(freitas);*/
         /*  clienteRepository.saveAll(Arrays.asList(walter,heitor,freitas));

            Pedido p1 = new Pedido();
            p1.setCliente(walter);
            p1.setDataPedido(LocalDate.now());
            p1.setTotal(BigDecimal.valueOf(100));
            pedidosRepository.save(p1);

          */

            /*Cliente cliente = clienteRepository.findClienteFetchPedidos(walter.getId());
            System.out.println(cliente);
            System.out.println(cliente.getPedidos());

            pedidosRepository.findByCliente(walter).forEach(System.out::println);


            List<Cliente>todosCliente = clienteRepository.findAll();
            todosCliente.forEach(System.out::println);

            boolean existe = clienteRepository.existsByNome("Walter");
            System.out.println("existe um cliente chamado Walter? "+existe);
            System.out.println("_____________________________");
            List<Cliente>result = clienteRepository.encontrarPorNome("Heitor");
            result.forEach(System.out::println);
            System.out.println("____________________________");
            List<Cliente> resultado =  clienteRepository.encontrarPorNomeSql("Heitor");*/


           // clienteRepository.deleteByNome("Freitas");
           /* clienteRepository.deleteAll(result);
            todosCliente = clienteRepository.findAll();
            todosCliente.forEach(System.out::println);*/

            /*System.out.println("atuaizando Clientes");
            todosCliente.forEach(c ->{
                c.setNome(c.getNome() + " Atualizado");
                clienteRepository.save(c);
            });
            System.out.println("buscando Clientes");
            clienteRepository.findByNomeLike("W").forEach(System.out::println);
            todosCliente.forEach(System.out::println);
            System.out.println("---------------------");
            clienteRepository.findAll();
            todosCliente.forEach(System.out::println);*/

        };
    }

    public static void main(String[] args) {
        SpringApplication.run(VendasApplication.class,args);
    }

    /* @Cachorro
    private Animal animal;

    @Bean(name = "executarAnimal")
    public CommandLineRunner executar(){
        return args -> {
            this.animal.fazerBarulho();
        };
    }

    */
}
