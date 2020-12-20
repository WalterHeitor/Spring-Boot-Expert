package com.softWalter;

import com.softWalter.model.Cliente;
import com.softWalter.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
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
            Cliente  cliente = new Cliente();
            cliente.setNome("Walter");
            clienteRepository.salvar(new Cliente("Walter"));
            clienteRepository.salvar(new Cliente("Heitor"));
            clienteRepository.salvar(new Cliente("Freitas"));

            List<Cliente>todosCliente = clienteRepository.obterTodos();
            todosCliente.forEach(System.out::println);

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
