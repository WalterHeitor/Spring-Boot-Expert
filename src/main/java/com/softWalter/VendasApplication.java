package com.softWalter;

import com.softWalter.model.Cliente;
import com.softWalter.repository.ClienteRepository;
import com.softWalter.repository.ClienteRepositoryJdbc;
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
    public CommandLineRunner init(@Autowired ClienteRepository clienteRepository ){
        return args -> {
            System.out.println("salvando Clientes");
            clienteRepository.salvar(new Cliente("Walter"));
            clienteRepository.salvar(new Cliente("Heitor"));
            clienteRepository.salvar(new Cliente("Freitas"));

            List<Cliente>todosCliente = clienteRepository.buscarTodos();
            todosCliente.forEach(System.out::println);

            System.out.println("atuaizando Clientes");
            todosCliente.forEach(c ->{
                c.setNome(c.getNome() + " Atualizado");
                clienteRepository.atualiza(c);
            });
            System.out.println("buscando Clientes");
            clienteRepository.buscarPorNome("W").forEach(System.out::println);
            todosCliente.forEach(System.out::println);
//            clienteRepository.obterTodos();
//            todosCliente.forEach(System.out::println);

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
