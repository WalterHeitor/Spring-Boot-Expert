package com.softWalter.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import java.util.Locale;

@Configuration
public class InternacionalizacaoConfig {

    /**
     * MessageSource e a fonte de menssagens.
     * ReloadableResourceBundleMessageSource por que um arquivo .properties.
     * messageSource.setBasename("classpath:"); para dizer o arquivo que vai
     * carregar as msgs.
     * messageSource.setDefaultEncoding("ISO-8859-1"); que tipo de codificação Ex: Brasil
     * messageSource.setDefaultLocale(Locale.getDefault()); pega o idioma do
     * sistema operacional
     * @return
     */
    @Bean
    public MessageSource messageSource(){
        ReloadableResourceBundleMessageSource messageSource
                = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("classpath:messages");
        messageSource.setDefaultEncoding("ISO-8859-1");
        messageSource.setDefaultLocale(Locale.getDefault());
        return messageSource;
    }

    /**
     * metodo para pegar pegar as menssagens do messages.properties e substituir pela chave
     * adicionada nos campos de validação.
     * @return
     */
    @Bean
    public LocalValidatorFactoryBean validatorFactoryBean(){
        LocalValidatorFactoryBean bean =
                new LocalValidatorFactoryBean();
        bean.setValidationMessageSource(messageSource());
        return bean;
    }
}
