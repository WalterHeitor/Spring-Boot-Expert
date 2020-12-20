package com.softWalter;


import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Developer
public class MinhaConfiguration {

    @Bean
    public CommandLineRunner executar(){
        return  args -> {
            System.out.println("Rodando a configuração de desenvolvimento");
        };
    }

    /*@Bean(name = "applicationName")
    public String applicationName(){
        return "Sistemas de Vendas";
    }*/
}
