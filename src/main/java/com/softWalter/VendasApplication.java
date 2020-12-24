package com.softWalter;

import com.softWalter.model.Cliente;
import com.softWalter.repository.ClienteRepository;
import com.softWalter.repository.ClienteRepositoryJdbc;
import com.softWalter.repository.ClientesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RestController;

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
    public CommandLineRunner init(@Autowired ClientesRepository clienteRepository ){
        return args -> {
            System.out.println("salvando Clientes");
            clienteRepository.save(new Cliente("Walter"));
            clienteRepository.save(new Cliente("Heitor"));
            clienteRepository.save(new Cliente("Freitas"));

            List<Cliente>todosCliente = clienteRepository.findAll();
            todosCliente.forEach(System.out::println);

            boolean existe = clienteRepository.existsByNome("Walter");
            System.out.println("existe um cliente chamado Walter? "+existe);

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
